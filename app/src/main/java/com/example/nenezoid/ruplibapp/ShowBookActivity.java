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
        String bookDescripation="";
        if(b!=null) {
            bookKey = b.getString("Returned Key");
            strTitle = b.getString("Returned Title");
            strAuthor = b.getString("Returned Author");
            strLoc= b.getString("Returned Id");
            bookDescripation=b.getString("Returned Description");
            avi=b.getBoolean("Returned Bool");
            bookurl=b.getString("bookurl");

        }



        TextView title_tv = findViewById(R.id.titleTextView);
        title_tv.setText("Title: "+strTitle);
        TextView author_tv = findViewById(R.id.authorTextView);
        author_tv.setText("Author: "+strAuthor);
        TextView avi_tv = findViewById(R.id.aviTextView);
        avi_tv.setText("Is Available: "+avi);
        TextView loc_tv = findViewById(R.id.idTextView);
        loc_tv.setText("Book Location: "+strLoc);
        TextView descriptionView= findViewById(R.id.descriptionView);
        descriptionView.setText("Description: "+bookDescripation);
        ImageView iv = findViewById(R.id.imageView);
        Glide.with(getApplicationContext()).load(bookurl).into(iv);
        Button resetButton=(Button)findViewById(R.id.orderButton);
        final FirebaseAuth mAuth= FirebaseAuth.getInstance();
        resetButton.setVisibility(View.GONE);
        if(mAuth.getCurrentUser()!=null)
        {
            resetButton.setVisibility(View.VISIBLE);
        }
        final boolean availble=avi;
        final String id=strTitle;
        final String strKeyy = bookKey;
        final String userId=mAuth.getCurrentUser().getUid();

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    final FirebaseDatabase database = FirebaseDatabase.getInstance();
                    final DatabaseReference myRef = database.getReference("Books");
                    final DatabaseReference userBookRef = database.getReference("usersbook");
                    if(availble) {
                        myRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                myRef.child(strKeyy).child("available").setValue(false);
                                userBookRef.child(id).child(userId).setValue("got it");

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                    else {
                        userBookRef.child(id).child(userId).setValue("order");


                    }



                //Intent intent = new Intent(getApplicationContext(),ShowBookActivity.class);
                intent.putExtra("Returned Bool",false);
                startActivity(intent);
            }
        });

        // Some function to fetch the info from firebase to page should go here
        // Example for link for pic https://firebasestorage.googleapis.com/v0/b/ruplibapp.appspot.com/o/net.jpg?alt=media&token=c837d082-f8c0-46f3-a18b-8877a57dcdc0


    }
}
