package com.example.android.quakereport;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by junejaspc on 8/12/16.
 */

public class Utils {
    private static final String url="{\"type\":\"FeatureCollection\",\"metadata\":{\"generated\":1481142680000,\"url\":\"http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2014-01-01&endtime=2014-01-02&minmagnitude=5\",\"title\":\"USGS Earthquakes\",\"status\":200,\"api\":\"1.5.2\",\"count\":2},\"features\":[{\"type\":\"Feature\",\"properties\":{\"mag\":6.5,\"place\":\"32km W of Sola, Vanuatu\",\"time\":1388592209000,\"updated\":1394151955000,\"tz\":660,\"url\":\"http://earthquake.usgs.gov/earthquakes/eventpage/usc000lvb5\",\"detail\":\"http://earthquake.usgs.gov/fdsnws/event/1/query?eventid=usc000lvb5&format=geojson\",\"felt\":null,\"cdi\":null,\"mmi\":4.84,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":1,\"sig\":650,\"net\":\"us\",\"code\":\"c000lvb5\",\"ids\":\",at00myqcls,pt14001000,usc000lvb5,\",\"sources\":\",at,pt,us,\",\"types\":\",cap,geoserve,impact-link,losspager,moment-tensor,moment-tensor,moment-tensor,moment-tensor,nearby-cities,origin,phase-data,shakemap,tectonic-summary,\",\"nst\":null,\"dmin\":3.997,\"rms\":0.76,\"gap\":14,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 6.5 - 32km W of Sola, Vanuatu\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[167.249,-13.8633,187]},\"id\":\"usc000lvb5\"},\n" +
            "{\"type\":\"Feature\",\"properties\":{\"mag\":5.1,\"place\":\"76km NNW of Davila, Philippines\",\"time\":1388534476610,\"updated\":1394151953000,\"tz\":480,\"url\":\"http://earthquake.usgs.gov/earthquakes/eventpage/usc000lv5e\",\"detail\":\"http://earthquake.usgs.gov/fdsnws/event/1/query?eventid=usc000lv5e&format=geojson\",\"felt\":null,\"cdi\":null,\"mmi\":null,\"alert\":null,\"status\":\"reviewed\",\"tsunami\":0,\"sig\":400,\"net\":\"us\",\"code\":\"c000lv5e\",\"ids\":\",usc000lv5e,\",\"sources\":\",us,\",\"types\":\",cap,general-link,geoserve,nearby-cities,origin,phase-data,tectonic-summary,\",\"nst\":null,\"dmin\":3.794,\"rms\":0.85,\"gap\":29,\"magType\":\"mb\",\"type\":\"earthquake\",\"title\":\"M 5.1 - 76km NNW of Davila, Philippines\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[120.2389,19.0868,10.07]},\"id\":\"usc000lv5e\"}],\"bbox\":[120.2389,-13.8633,10.07,167.249,19.0868,187]}";

    private Utils(){}

public static ArrayList<Earthquake>extractquakes()

    {ArrayList<Earthquake> al=new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(url);
            JSONArray jsonArray=jsonObject.getJSONArray("features");
Log.d("here","hi");
            for(int i=0;i<jsonArray.length();i++){
                Log.d("here","hellllllllllll");

                JSONObject quake=jsonArray.getJSONObject(i);
                JSONObject prop=quake.getJSONObject("properties");
                String mag=prop.getString("mag");
                Log.d("mag",mag);
                String place=prop.getString("place");
                long time=prop.getLong("time");

               /* Date date=new Date(Long.valueOf(time));
                SimpleDateFormat format=new SimpleDateFormat("DD MM YYYY");
                String mydate=format.format(date);*/
                Earthquake earthquake=new Earthquake(mag,place,time);
                al.add(earthquake);

            }
        }
        catch(Exception e){
            Log.d("er",e.getMessage());
        }
        return al;
    }

}
