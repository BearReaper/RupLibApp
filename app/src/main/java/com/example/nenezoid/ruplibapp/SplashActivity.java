package com.example.nenezoid.ruplibapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setTitle("Splashing your library!");
        //Splash initiaion

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    synchronized (this){
                        wait(1000);
                        Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
