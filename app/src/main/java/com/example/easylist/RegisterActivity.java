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

public class RegisterActivity extends AppCompatActivity {
    private EditText RegisterEmail, RegisterPassword;
    private Button RegisterButton;
    private TextView HaveAccLog;
    private Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);

        toolbar = findViewById(R.id.RegisterTb);
        getSupportActionBar().setTitle("Register");

        RegisterEmail = findViewById(R.id.RegisterEmail);
        RegisterPassword = findViewById(R.id.RegisterPassword);
        RegisterButton = findViewById(R.id.RegisterButton);
        HaveAccLog = findViewById(R.id.HaveAccountLog);

        HaveAccLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, Login.class);
                startActivity(intent);
            }
        });




    }
}