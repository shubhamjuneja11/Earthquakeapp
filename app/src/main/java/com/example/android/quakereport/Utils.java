package com.example.android.quakereport;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by junejaspc on 8/12/16.
 */

public class Utils {
    private Utils(){}

public static ArrayList<Earthquake>extractquakes(String url) throws IOException

{URL url1;
        String jsonrespponse;
        try {
            url1=new URL(url);
        } catch (MalformedURLException e) {
            url1=null;
        }
        jsonrespponse=makerequest(url1);
        ArrayList<Earthquake> quakes=getquakes(jsonrespponse);
    return quakes;
    }

    private static ArrayList<Earthquake> getquakes(String  url){

        ArrayList<Earthquake> al=new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(url);
            JSONArray jsonArray=jsonObject.getJSONArray("features");
            for(int i=0;i<jsonArray.length();i++){

                JSONObject quake=jsonArray.getJSONObject(i);
                JSONObject prop=quake.getJSONObject("properties");
                double mag=prop.getDouble("mag");
                String place=prop.getString("place");
                long time=prop.getLong("time");
                String urls=prop.getString("url");

                Earthquake earthquake=new Earthquake(mag,place,time,urls);
                al.add(earthquake);

            }
        }
        catch(Exception e){
        }
        return al;
    }

    private static String makerequest(URL url1) throws IOException {
        String jsonResponse="";
        InputStream inputstream=null;
        if(url1==null)
            return jsonResponse;
        HttpURLConnection connection=null;
        connection=(HttpURLConnection)url1.openConnection();
        connection.setRequestMethod("GET");
        connection.setReadTimeout(10000);
        connection.setReadTimeout(15000);
        connection.connect();

        inputstream=connection.getInputStream();
        jsonResponse=readfromstream(inputstream);
        return jsonResponse;
    }

    private static String readfromstream(InputStream inputstream) throws IOException {
        StringBuilder string=new StringBuilder();
        if(inputstream!=null) {
            InputStreamReader inputreader = new InputStreamReader(inputstream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputreader);
            String line = reader.readLine();
            while (line != null) {
                string.append(line);
                line = reader.readLine();
            }
        }
        return string.toString();
    }

}
