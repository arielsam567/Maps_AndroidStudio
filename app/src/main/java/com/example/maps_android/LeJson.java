package com.example.maps_android;

import android.os.StrictMode;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LeJson {

    public static InputStream leJson(String urljson) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        HttpURLConnection urlConnection;
        InputStream in = null;
        try {
            // the url we wish to connect to
            URL url = new URL(urljson);// open the connection to the specified URL
            urlConnection = (HttpURLConnection) url.openConnection();// get the response from the server in an input stream
            in = new BufferedInputStream(urlConnection.getInputStream());
            return in;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String convertStreamToString(InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
