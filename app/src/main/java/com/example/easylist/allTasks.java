package com.example.easylist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;
import java.util.zip.Inflater;

public class allTasks extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;

    private DatabaseReference reference;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private String onlineUserID;

    private String key = "";
    private String task;
    private String description;

    private ProgressDialog loader;

    private FloatingActionButton profileBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_alltasks);


        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mAuth = FirebaseAuth.getInstance();

        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);


        loader = new ProgressDialog(this);


        mUser = mAuth.getCurrentUser();
        onlineUserID = mUser.getUid();
        reference = FirebaseDatabase.getInstance().getReference().child("tasks").child(onlineUserID);



        floatingActionButton = findViewById(R.id.FloatingAb);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateTask();
            }
        });



        profileBtn =  findViewById(R.id.FloatingProfile);
        profileBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(allTasks.this, Profile.class);
                startActivity(intent);

            }
        });

    }

    private void CreateTask() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);

        View viewinf = inflater.inflate(R.layout.input_file,null);
        dialog.setView(viewinf);

        final AlertDialog dialogs = dialog.create();
        dialog.setCancelable(false);

        final EditText task = viewinf.findViewById(R.id.task);
        final EditText description = viewinf.findViewById(R.id.description);
        Button save = viewinf.findViewById(R.id.saveBtn);
        Button cancel = viewinf.findViewById(R.id.cancelBtn);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogs.dismiss();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mTask = task.getText().toString().trim();
                String mDescription = description.getText().toString().trim();
                String id = reference.push().getKey();
                String date = DateFormat.getDateInstance().format(new Date());


                if (TextUtils.isEmpty(mTask)) {
                    task.setError("Cannot Be Empty");
                    return;
                }
                if (TextUtils.isEmpty(mDescription)) {
                    description.setError("Cannot Be Empty");
                    return;
                }
                else{
                    loader.setMessage("Assigning Date");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();

                    Model model = new Model(mTask,mDescription,id,date);
                    reference.child(id).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(allTasks.this, "Task Has been Created", Toast.LENGTH_SHORT).show();
                                loader.dismiss();
                            }else{
                                String error = task.getException().toString();
                                Toast.makeText(allTasks.this, "Could Not add Task", Toast.LENGTH_SHORT).show();
                                loader.dismiss();
                            }

                        }
                    });

                    }
                dialogs.dismiss();
            }
            });

        dialogs.show();
    }
}