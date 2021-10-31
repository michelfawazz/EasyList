package com.example.easylist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final int SPLASH =3300;

    Animation LogoAnim, TextAnim;
    ImageView imageView;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        LogoAnim = AnimationUtils.loadAnimation( this, R.anim.splash_animation);
        TextAnim = AnimationUtils.loadAnimation(  this, R.anim.splashanimationtext);
        imageView = findViewById(R.id.imgview);
        textView = findViewById(R.id.maintxt);

        imageView.setAnimation(LogoAnim);
        textView.setAnimation(TextAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class );
                startActivity(intent);
                finish();
            }
        }, SPLASH);


    }



}