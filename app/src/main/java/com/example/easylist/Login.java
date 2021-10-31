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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private EditText LoginEmail, LoginPassword;
    private Button LoginButton;
    private TextView HaveAcc;
    private Toolbar toolbar;

    private FirebaseAuth mAuth;
    private ProgressDialog loader;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);



        LoginEmail = findViewById(R.id.LoginEmail);
        LoginPassword = findViewById(R.id.LoginPassword);
        LoginButton = findViewById(R.id.LoginButton);
        HaveAcc = findViewById(R.id.HaveAccount);



        mAuth = FirebaseAuth.getInstance();
        loader = new ProgressDialog(this);



        HaveAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = LoginEmail.getText().toString().trim();
                String password = LoginPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    LoginEmail.setError("Cannot Be Empty");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    LoginPassword.setError("Cannot Be Empty");
                    return;

                }
                else{
                    loader.setMessage("Logging in...");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();

                    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Intent intent = new Intent(Login.this, allTasks.class);
                                startActivity(intent);
                                finish();
                                loader.dismiss();

                            }
                            else{
                                String error = task.getException().toString();
                                Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                loader.dismiss();
                            }
                        }
                    });

                }


            }
        });


    }
}