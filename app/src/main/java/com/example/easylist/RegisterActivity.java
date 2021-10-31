package com.example.easylist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private EditText RgstEmail, RgstPassword;
    private Button RgstBtn;
    private TextView HaveAccLog;
    private Toolbar toolbar;

    private FirebaseAuth mAuth;
    private ProgressDialog loader;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);



        RgstEmail = findViewById(R.id.RegisterEmail);
        RgstPassword = findViewById(R.id.RegisterPassword);
        RgstBtn = findViewById(R.id.RegisterButton);
        HaveAccLog = findViewById(R.id.HaveAccountLog);


        //firebase
        mAuth = FirebaseAuth.getInstance();

        loader = new ProgressDialog(this);


        HaveAccLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, Login.class);
                startActivity(intent);
            }
        });


        RgstBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = RgstEmail.getText().toString().trim();
                String password = RgstPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    RgstEmail.setError("Cannot be Empty");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    RgstPassword.setError("Cannot be Empty");
                    return;
                }
                else{
                    loader.setMessage("Registering...");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(RegisterActivity.this, Home.class);
                                startActivity(intent);
                                finish();
                                loader.dismiss();
                            } else {
                                String error = task.getException().toString();
                                Toast.makeText(RegisterActivity.this, "Failed registration", Toast.LENGTH_SHORT).show();
                                loader.dismiss();
                            }
                        }
                    });
                }





            }
        });




    }
}