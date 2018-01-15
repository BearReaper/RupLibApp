package com.example.nenezoid.ruplibapp;


import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class SearchActivity extends AppCompatActivity {

    ArrayList<Book> list = new ArrayList<>();
    //ListView listy;
    String strSearchKey ="";
    private FirebaseAuth mAuth;
    // Creating RecyclerView.
    RecyclerView recyclerView;

    // Creating RecyclerView.Adapter.
    RecyclerView.Adapter adapter ;

    // Creating Progress dialog
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search2);
        progressDialog = new ProgressDialog(SearchActivity.this);

        // Setting up message in Progress dialog.
        progressDialog.setMessage("Loading books ");
        mAuth = FirebaseAuth.getInstance();
        // Assign id to RecyclerView.
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // Setting RecyclerView size true.
        recyclerView.setHasFixedSize(true);

        // Setting RecyclerView layout as LinearLayout.
        recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Books");
        progressDialog.show();
        //Some code for fetching the search key and verify by toast
        final Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if(b!=null)
            strSearchKey = (String)b.getString("Title");
        Toast.makeText(SearchActivity.this,strSearchKey,Toast.LENGTH_LONG).show(); // find out if the intent went OK




        Query query = myRef.orderByChild("title");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot sanpy :dataSnapshot.getChildren() )
                {
                    Book booky = sanpy.getValue(Book.class);
                    if(booky!=null)
                    {
                        if(booky.getTitle().toLowerCase().contains(strSearchKey.toLowerCase()))
                        {
                            list.add(booky);

                        }

                    }
                }
                adapter = new RecyclerViewAdapter(getApplicationContext(), list,new OnItemClickListener() {
                    @Override
                    public void onItemClick(Book book)
                    {
                        Intent intent = new Intent(SearchActivity.this,ShowBookActivity.class);


                        intent.putExtra("Returned Title",book.getTitle());
                        intent.putExtra("Returned Author",book.getAuthor());
                        intent.putExtra("Returned Id",book.getId());
                        intent.putExtra("Returned Bool",book.getAvailable());

                        startActivity(intent);
                    }
                    });

                recyclerView.setAdapter(adapter);
                // Hiding the progress dialog.
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });






    }
}




//Now set some values into the db

        /*myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                //Log.d(TAG, "Value is: " + value);
                resText.setText(value);
                Toast.makeText(getApplicationContext(),value,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
                //Toast.makeText(getApplicationContext(),"this is error",Toast.LENGTH_SHORT).show();

            }
        });*/

//        Book booky = new Book(13,"Networking","NezDog",true);
//        myRef.child("Books").child("Book1");
//        myRef.setValue(booky);
//
//        myRef = database.getReference("Books").child("Book2");
//        Book booky2 = new Book(14,"Networking basics - advance mode","NezDog",true);
//        myRef.child("Books").child("Book2");
//        myRef.setValue(booky2);
//
//        myRef = database.getReference("Books").child("Book3");
//        Book booky3 = new Book(15,"Networking & algo","NezDog",true);
//        myRef.child("Books").child("Book3");
//        myRef.setValue(booky3);




// Uncommet to get all db in one item on listView
        /*myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                Book booky = dataSnapshot.getValue(Book.class);
//                String resnik = booky.getTitle();
//                list.add(resnik);
                String res = dataSnapshot.getValue().toString();
                Toast.makeText(getApplicationContext(),res,Toast.LENGTH_LONG).show();
                list.add(res);
                itemsAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

// Better way - get all matches on different list items
        /*myRef.orderByChild("title").equalTo(strSearchKey).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String res = dataSnapshot.getValue().toString();
                //resText.setText(res);
                list.add(res);
//                Book booky = dataSnapshot.getValue(Book.class);
//                String resnik = booky.getTitle();
//                list.add(resnik);
                itemsAdapter.notifyDataSetChanged();
                //Toast.makeText(getApplicationContext(),res,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/