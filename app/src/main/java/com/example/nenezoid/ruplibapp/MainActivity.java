package com.example.nenezoid.ruplibapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Ruppin Library App");
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            String instanceId = FirebaseInstanceId.getInstance().getToken();
            FirebaseDatabase.getInstance().getReference()
                    .child("users")
                    .child(firebaseUser.getUid())
                    .child("instanceId")
                    .setValue(instanceId);

        }

        findViewById(R.id.titleButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //some code for the sherdPref
                SharedPreferences sp = getSharedPreferences("LibData", 0) ;
                SharedPreferences.Editor sedt= sp.edit();
                sedt.putString("SearchType", "title");
                sedt.apply(); //maybe comit?

                Intent inetent = new Intent (MainActivity.this,TitleActivity.class);
                //getIntent().putExtra("SearchType","title");
                startActivity(inetent);

            }
        });
        findViewById(R.id.upload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent inetent = new Intent (MainActivity.this,UploadImageActivity.class);
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

        findViewById(R.id.authourButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //some code for the sherdPref
                SharedPreferences sp = getSharedPreferences("LibData", 0) ;
                SharedPreferences.Editor sedt= sp.edit();
                sedt.putString("SearchType", "author");
                sedt.apply(); //maybe comit?

                Intent inetent = new Intent (MainActivity.this,TitleActivity.class);
                //getIntent().putExtra("SearchType","author");
                startActivity(inetent);
            }
        });
        findViewById(R.id.freeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //some code for the sherdPref
                SharedPreferences sp = getSharedPreferences("LibData", 0) ;
                SharedPreferences.Editor sedt= sp.edit();
                sedt.putString("SearchType", "free");
                sedt.apply(); //maybe comit?

                Intent inetent = new Intent (MainActivity.this,TitleActivity.class);
                //getIntent().putExtra("SearchType","author");
                startActivity(inetent);
            }
        });
    }
}
