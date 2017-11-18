package com.example.android.saufdeckel.ui.activities;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.android.saufdeckel.R;


public class SplashActivity extends AppCompatActivity {

    public static final int SPLASH_SCREEN_DELAY = 2500; //in ms

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                hideSplashScreen();
            }
        }, SPLASH_SCREEN_DELAY);
    }

    private void hideSplashScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
