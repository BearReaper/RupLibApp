package com.example.nenezoid.ruplibapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TitleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        SharedPreferences sp= getSharedPreferences("LibData", 0);
        String restoredText= sp.getString("SearchType", null);
        if (restoredText!= null)
            Toast.makeText(getApplicationContext(),"Hello "+restoredText,Toast.LENGTH_LONG).show() ;

        setTitle("Search by "+restoredText);
        final EditText title = (EditText)findViewById(R.id.TitleInEditText);
        title.setHint("Enter name to search by "+restoredText);

        findViewById(R.id.searchButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Get the title input string from user and save it on intent first
                //Send intent to SearchActivity hopefully

                String strSrchKey = new StringBuilder(title.getText().toString()).toString();

                Intent intush = new Intent(TitleActivity.this,SearchActivity.class);
                intush.putExtra("SearchKey" , strSrchKey);
                startActivity(intush);
            }
        });
    }
}
