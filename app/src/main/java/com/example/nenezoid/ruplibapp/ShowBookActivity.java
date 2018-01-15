package com.example.nenezoid.ruplibapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ShowBookActivity extends AppCompatActivity {

    String bookKey="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_book);
        setTitle("Search Results");

        final Intent intent = getIntent();
        final Bundle b = intent.getExtras();

        String strAuthor = "";
        String strTitle = "";
        String strLoc = "";
        Boolean avi=false;
        String bookurl="";
        if(b!=null) {
            bookKey = b.getString("Returned Key");
            strTitle = b.getString("Returned Title");
            strAuthor = b.getString("Returned Author");
            strLoc= b.getString("Returned Id");
            avi=b.getBoolean("Returned Bool");
            bookurl=b.getString("bookurl");


        }
        Toast.makeText(getApplicationContext(),bookKey,Toast.LENGTH_SHORT).show();
        // find out if the intent went OK
        //Toast.makeText(getApplicationContext(),str,Toast.LENGTH_LONG).show();
        //ImageView iv = findViewById(R.id.resImageView);

        TextView title_tv = findViewById(R.id.titleTextView);
        title_tv.setText("Title: "+strTitle);
        TextView author_tv = findViewById(R.id.authorTextView);
        author_tv.setText("Author: "+strAuthor);
        TextView avi_tv = findViewById(R.id.aviTextView);
        avi_tv.setText("Is Available: "+avi);
        TextView loc_tv = findViewById(R.id.idTextView);
        loc_tv.setText("Book Location: "+strLoc);

        ImageView iv = findViewById(R.id.imageView);
        Glide.with(getApplicationContext()).load(bookurl).into(iv);
        Button resetButton=(Button)findViewById(R.id.orderButton);
        FirebaseAuth mAuth= FirebaseAuth.getInstance();
        resetButton.setVisibility(View.GONE);
        if(mAuth.getCurrentUser()!=null&&avi)
        {
            resetButton.setVisibility(View.VISIBLE);
        }

        final String strKeyy = bookKey;
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference myRef = database.getReference("Books");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        myRef.child(strKeyy).child("available").setValue(false);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                //Intent intent = new Intent(getApplicationContext(),ShowBookActivity.class);
                intent.putExtra("Returned Bool",false);
                startActivity(intent);
            }
        });

        // Some function to fetch the info from firebase to page should go here
        // Example for link for pic https://firebasestorage.googleapis.com/v0/b/ruplibapp.appspot.com/o/net.jpg?alt=media&token=c837d082-f8c0-46f3-a18b-8877a57dcdc0


    }
}
