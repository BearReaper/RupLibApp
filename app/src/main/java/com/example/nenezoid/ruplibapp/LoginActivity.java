package com.example.nenezoid.ruplibapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");

        TextView signup = findViewById(R.id.signUpTextView);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });

        final TextView user = findViewById(R.id.userEditText);
        final  TextView pass = findViewById(R.id.passEditText);

        findViewById(R.id.loginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_user = new StringBuilder(user.getText().toString()).toString();
                String str_pass = new StringBuilder(pass.getText().toString()).toString();
                Intent intuk = new Intent(); // figure out where it goes - firebase?




            }
        });


    }
}
