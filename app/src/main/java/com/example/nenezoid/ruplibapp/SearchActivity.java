package com.example.nenezoid.ruplibapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search2);


        //Some code for fetching the search key and verify by toast
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        String strSearchKey ="";
        if(b!=null)
            strSearchKey = (String)b.getString("Title");
        Toast.makeText(SearchActivity.this,strSearchKey,Toast.LENGTH_LONG).show();

        final TextView resText = (TextView) findViewById(R.id.resposeText); // instance an object for the http resonse

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
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
        queue.add(stringRequest);
    }
}
