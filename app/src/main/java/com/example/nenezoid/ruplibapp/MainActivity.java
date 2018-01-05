package com.example.nenezoid.ruplibapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Ruppin Library App");

//        findViewById(R.id.freeButton).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent inetent = new Intent (MainActivity.this,TitleActivity.class);
//                startActivity(inetent);
//
//            }
//        });

        findViewById(R.id.titleButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent inetent = new Intent (MainActivity.this,TitleActivity.class);
                startActivity(inetent);

            }
        });

        findViewById(R.id.loginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent inetent = new Intent (MainActivity.this,LoginActivity.class);
                startActivity(inetent);

            }
        });
    }
}
