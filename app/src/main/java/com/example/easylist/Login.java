package com.example.easylist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    private EditText LoginEmail, LoginPassword;
    private Button LoginButton;
    private TextView HaveAcc;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);



        LoginEmail = findViewById(R.id.LoginEmail);
        LoginPassword = findViewById(R.id.LoginPassword);
        LoginButton = findViewById(R.id.LoginButton);
        HaveAcc = findViewById(R.id.HaveAccount);

        toolbar = findViewById(R.id.LoginTb);

        getSupportActionBar().setTitle("Login");



        HaveAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


    }
}