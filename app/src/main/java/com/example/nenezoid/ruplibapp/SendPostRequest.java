package com.example.nenezoid.ruplibapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Nenezoid on 1/2/2018.
 */

public class SendPostRequest {
        public void SendPost() throws IOException {
            URL reqURL = new URL("http://www.stackoverflow.com/"); //the URL we will send the request to
            HttpURLConnection request = (HttpURLConnection) (reqURL.openConnection());
            String post = "this will be the post data that you will send";
            request.setDoOutput(true);
            request.addRequestProperty("Content-Length", Integer.toString(post.length())); //add the content length of the post data
            request.addRequestProperty("Content-Type", "application/x-www-form-urlencoded"); //add the content type of the request, most post data is of this type
            request.setRequestMethod("POST");
            request.connect();
            OutputStreamWriter writer = new OutputStreamWriter(request.getOutputStream()); //we will write our request data here
            writer.write(post);
            writer.flush();


        }

            public void GetRequst()throws IOException{



            BufferedReader br;
            URL reqURL = new URL("http://www.stackoverflow.com/"); //the URL we will send the request to
            HttpURLConnection request = (HttpURLConnection) (reqURL.openConnection());
            request.setRequestMethod("GET");
            request.connect();
                if (200 <=  request.getResponseCode() &&  request.getResponseCode() <= 299) {
                    br = new BufferedReader(new InputStreamReader( request.getInputStream()));
                } else {
                    br = new BufferedReader(new InputStreamReader(request.getErrorStream()));
                }
            //request.getContent();

        }
    }


