package com.example.nenezoid.ruplibapp;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    ArrayList<Book> list = new ArrayList<>();
    ListView listy;
    String strSearchKey ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search2);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Books");

        //Some code for fetching the search key and verify by toast
        final Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if(b!=null)
            strSearchKey = (String)b.getString("Title");
        Toast.makeText(SearchActivity.this,strSearchKey,Toast.LENGTH_LONG).show(); // find out if the intent went OK
        //final TextView resText = (TextView) findViewById(R.id.resposeText); // instance an object for the http resonse

        listy = findViewById(R.id.myList);
        final ArrayAdapter<Book> itemsAdapter = new ArrayAdapter<Book>(this, android.R.layout.simple_list_item_1,list);
        listy.setAdapter(itemsAdapter);

        Query query = myRef.orderByChild("title");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot sanpy :dataSnapshot.getChildren() )
                {
                    Book booky = sanpy.getValue(Book.class);
                    if(booky!=null)
                    {
                        if(booky.getTitle().contains(strSearchKey))
                        {
                            list.add(booky);
                            itemsAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        listy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Some code for fetching and using the gridview possition and etc.

                Intent intent = new Intent(SearchActivity.this,ShowBookActivity.class);
                intent.putExtra("Key",position);
                // Need to figure a way to pass the book to next activity
                //
                int idg = (int)id;
                Book book =list.get(idg);
                intent.putExtra("Returned Title",book.getTitle());
                intent.putExtra("Returned Author",book.getAuthor());
                intent.putExtra("Returned Id",book.getId());
                intent.putExtra("Returned Bool",book.getAvailable());
                //Toast.makeText(getApplicationContext(),str,Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });



    }
}
// junk code for the request thingy
// Instantiate the RequestQueue.
        /*RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://ruppin-primo.hosted.exlibrisgroup.com/primo_library/libweb/webservices/rest/primo-explore/v1/pnxs?addfields=vertitle,title,collection,creator,contributor,subject,ispartof,description,relation&getMore=0&inst=972RCO_INST&lang=iw_IL&limit=10&offset=0&pcAvailability=true&q=any,contains,"+strSearchKey+"&qExclude=&qInclude=&rtaLinks=true&scope=default_scope&skipDelivery=Y&sort=rank&tab=default_tab&vid=972RCO_INST_V1"; //networking is the search key here

        // Request a string response from the provided URL.

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //Toast.makeText(SearchActivity.this,"Response is: "+ response.substring(0,500),Toast.LENGTH_LONG).show();
                        resText.setText("Response is: "+ response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resText.setText("That didn't work!");
                //Toast.makeText(SearchActivity.this,"That didn't work!",Toast.LENGTH_LONG).show();
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);*/



     /*listy =(ListView)findViewById(R.id.myList);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,list);
        listy.setAdapter(adapter);
        myRef.orderByChild("Books").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                list.add(dataSnapshot.getValue(String.class));
                adapter.notifyDataSetChanged();
                String value = dataSnapshot.getValue(String.class);
                //resText.setText(value);
                Toast.makeText(getApplicationContext(),value,Toast.LENGTH_SHORT).show();
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