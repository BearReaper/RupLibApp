package com.example.nenezoid.ruplibapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ShowBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_book);

        final Intent intent = getIntent();
        Bundle b = intent.getExtras();
        int res,id=0;
        String strAuthor = "";
        String strTitle = "";
        Boolean avi=false;
        if(b!=null) {
            res = b.getInt("Key");
            strTitle = b.getString("Returned Title");
            strAuthor = b.getString("Returned Author");
            id = b.getInt("Returned Id");
            avi=b.getBoolean("Returned Bool");


        }
        //Toast.makeText(getApplicationContext(),Integer.toString(res),Toast.LENGTH_SHORT).show(); // find out if the intent went OK
        //Toast.makeText(getApplicationContext(),str,Toast.LENGTH_LONG).show();
        //ImageView iv = findViewById(R.id.resImageView);

        TextView title_tv = findViewById(R.id.titleTextView);
        title_tv.setText("Title: "+strTitle);
        TextView author_tv = findViewById(R.id.authorTextView);
        author_tv.setText("Author: "+strAuthor);
        TextView avi_tv = findViewById(R.id.aviTextView);
        avi_tv.setText("Is Available: "+avi);



        // Some function to fetch the info from firebase to page should go here
        // Example for link for pic https://firebasestorage.googleapis.com/v0/b/ruplibapp.appspot.com/o/net.jpg?alt=media&token=c837d082-f8c0-46f3-a18b-8877a57dcdc0


    }
}
