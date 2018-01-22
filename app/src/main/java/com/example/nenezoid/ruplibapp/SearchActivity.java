package com.example.nenezoid.ruplibapp;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import java.util.HashMap;


public class SearchActivity extends AppCompatActivity {

    HashMap<String,String> keys=new HashMap<>();
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
        SharedPreferences sp= getSharedPreferences("LibData", 0);
        final String strSrchType = sp.getString("SearchType", null);
        if (strSrchType== null)
            return;
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
        final DatabaseReference myRef = database.getReference("Books");
        progressDialog.show();

        //Some code for fetching the search key and verify by toast
        final Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if(b!=null)
            strSearchKey = (String)b.getString("SearchKey");

        Query query = myRef.orderByChild(strSrchType);
        final Context context = this;
        query.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(final DataSnapshot dataSnapshot) {
                int counter =0;

                for(DataSnapshot snapy :dataSnapshot.getChildren() )
                {
                    snapy.getKey();
                    Book booky = snapy.getValue(Book.class);
                    if(booky!=null)
                    {
                        switch (strSrchType){
                            case "title":
                                if(booky.getTitle().toLowerCase().contains(strSearchKey.toLowerCase()))
                                {
                                    list.add(booky);
                                    keys.put(booky.getTitle(),snapy.getKey());
                                    counter++;
                                }
                            break;
                            case "author":
                                if(booky.getAuthor().toLowerCase().contains(strSearchKey.toLowerCase()))
                                {
                                    list.add(booky);
                                    keys.put(booky.getTitle(),snapy.getKey());
                                }
                            break;
                            case "free":
                                if(booky.contains(strSearchKey))
                                {
                                    list.add(booky);
                                    keys.put(booky.getTitle(),snapy.getKey());
                                }
                                break;
                        }
                    }
                }
                String str = Integer.toString(counter);


                if(counter==0)
                {
                    AlertDialog.Builder alertDialogBuilder= new AlertDialog.Builder(context);
                    alertDialogBuilder.setTitle("Wrong input!");
                    alertDialogBuilder.setMessage("The search key entered wasn't found on database. Try again").setPositiveButton("OK",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            Intent intenty = new Intent(getApplicationContext(),TitleActivity.class);
                            startActivity(intenty);
                            progressDialog.hide();
                        }
                    });
                    AlertDialog alertDialog= alertDialogBuilder.create();
                    alertDialog.show();
                }
                else{
                    adapter = new RecyclerViewAdapter(getApplicationContext(), list,new OnItemClickListener() {
                        @Override
                        public void onItemClick(Book book)
                        {
                            Intent intent = new Intent(SearchActivity.this,ShowBookActivity.class);
                            intent.putExtra("Returned Key",keys.get(book.getTitle()));
                            intent.putExtra("Returned Title",book.getTitle());
                            intent.putExtra("Returned Author",book.getAuthor());
                            intent.putExtra("Returned Id",book.getId());
                            intent.putExtra("Returned Bool",book.getAvailable());
                            intent.putExtra("bookurl",book.getImageUrl());
                            intent.putExtra("Returned Description",book.getDescription());
                            startActivity(intent);
                        }
                    });
                    recyclerView.setAdapter(adapter);
                    // Hiding the progress dialog.
                    progressDialog.dismiss();
                }
                }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });
    }
}
