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
    private static final String url="{\"type\":\"FeatureCollection\",\"metadata\":{\"generated\":1481232404000,\"url\":\"http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2014-01-11&endtime=2014-01-12&minmagnitude=5\",\"title\":\"USGS Earthquakes\",\"status\":200,\"api\":\"1.5.2\",\"count\":5},\"features\":[{\"type\":\"Feature\",\"properties\":{\"mag\":5,\"place\":\"129km NE of Hirara, Japan\",\"time\":1389465346450,\"updated\":1394854752000,\"tz\":480,\"url\":\"http://earthquake.usgs.gov/earthquakes/eventpage/usc000m12p\",\"detail\":\"http://earthquake.usgs.gov/fdsnws/event/1/query?eventid=usc000m12p&format=geojson\",\"felt\":null,\"cdi\":null,\"mmi\":null,\"alert\":null,\"status\":\"reviewed\",\"tsunami\":0,\"sig\":385,\"net\":\"us\",\"code\":\"c000m12p\",\"ids\":\",usc000m12p,\",\"sources\":\",us,\",\"types\":\",cap,general-link,geoserve,nearby-cities,origin,phase-data,tectonic-summary,\",\"nst\":null,\"dmin\":2.209,\"rms\":0.73,\"gap\":96,\"magType\":\"mb\",\"type\":\"earthquake\",\"title\":\"M 5.0 - 129km NE of Hirara, Japan\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[126.3187,25.489,41.63]},\"id\":\"usc000m12p\"},\n" +
            "{\"type\":\"Feature\",\"properties\":{\"mag\":5,\"place\":\"Northern Mid-Atlantic Ridge\",\"time\":1389451859790,\"updated\":1394854749000,\"tz\":-180,\"url\":\"http://earthquake.usgs.gov/earthquakes/eventpage/usc000m0yj\",\"detail\":\"http://earthquake.usgs.gov/fdsnws/event/1/query?eventid=usc000m0yj&format=geojson\",\"felt\":null,\"cdi\":null,\"mmi\":null,\"alert\":null,\"status\":\"reviewed\",\"tsunami\":0,\"sig\":385,\"net\":\"us\",\"code\":\"c000m0yj\",\"ids\":\",usc000m0yj,\",\"sources\":\",us,\",\"types\":\",cap,general-link,geoserve,nearby-cities,origin,phase-data,\",\"nst\":null,\"dmin\":18.132,\"rms\":0.92,\"gap\":87,\"magType\":\"mb\",\"type\":\"earthquake\",\"title\":\"M 5.0 - Northern Mid-Atlantic Ridge\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[-44.517,26.2382,10]},\"id\":\"usc000m0yj\"},\n" +
            "{\"type\":\"Feature\",\"properties\":{\"mag\":5.5,\"place\":\"8km SSW of Pajapita, Guatemala\",\"time\":1389445851110,\"updated\":1422554498527,\"tz\":-360,\"url\":\"http://earthquake.usgs.gov/earthquakes/eventpage/usc000m0xb\",\"detail\":\"http://earthquake.usgs.gov/fdsnws/event/1/query?eventid=usc000m0xb&format=geojson\",\"felt\":25,\"cdi\":4.4,\"mmi\":4.75,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":0,\"sig\":476,\"net\":\"us\",\"code\":\"c000m0xb\",\"ids\":\",usc000m0xb,\",\"sources\":\",us,\",\"types\":\",cap,dyfi,geoserve,losspager,moment-tensor,moment-tensor,nearby-cities,origin,phase-data,shakemap,tectonic-summary,\",\"nst\":null,\"dmin\":0.329,\"rms\":1.37,\"gap\":68,\"magType\":\"mwb\",\"type\":\"earthquake\",\"title\":\"M 5.5 - 8km SSW of Pajapita, Guatemala\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[-92.0592,14.6437,78]},\"id\":\"usc000m0xb\"},\n" +
            "{\"type\":\"Feature\",\"properties\":{\"mag\":5,\"place\":\"80km S of Lakatoro, Vanuatu\",\"time\":1389444704910,\"updated\":1394854748000,\"tz\":660,\"url\":\"http://earthquake.usgs.gov/earthquakes/eventpage/usc000m0x5\",\"detail\":\"http://earthquake.usgs.gov/fdsnws/event/1/query?eventid=usc000m0x5&format=geojson\",\"felt\":null,\"cdi\":null,\"mmi\":null,\"alert\":null,\"status\":\"reviewed\",\"tsunami\":0,\"sig\":385,\"net\":\"us\",\"code\":\"c000m0x5\",\"ids\":\",usc000m0x5,\",\"sources\":\",us,\",\"types\":\",cap,geoserve,nearby-cities,origin,phase-data,tectonic-summary,\",\"nst\":null,\"dmin\":1.204,\"rms\":0.91,\"gap\":42,\"magType\":\"mb\",\"type\":\"earthquake\",\"title\":\"M 5.0 - 80km S of Lakatoro, Vanuatu\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[167.4812,-16.841,10]},\"id\":\"usc000m0x5\"},\n" +
            "{\"type\":\"Feature\",\"properties\":{\"mag\":5,\"place\":\"6km NE of Zakynthos, Greece\",\"time\":1389413576200,\"updated\":1394854745000,\"tz\":120,\"url\":\"http://earthquake.usgs.gov/earthquakes/eventpage/usc000m0t5\",\"detail\":\"http://earthquake.usgs.gov/fdsnws/event/1/query?eventid=usc000m0t5&format=geojson\",\"felt\":null,\"cdi\":null,\"mmi\":null,\"alert\":null,\"status\":\"reviewed\",\"tsunami\":0,\"sig\":385,\"net\":\"us\",\"code\":\"c000m0t5\",\"ids\":\",usc000m0t5,\",\"sources\":\",us,\",\"types\":\",cap,general-link,geoserve,moment-tensor,nearby-cities,origin,phase-data,tectonic-summary,\",\"nst\":null,\"dmin\":null,\"rms\":0.91,\"gap\":null,\"magType\":\"mwr\",\"type\":\"earthquake\",\"title\":\"M 5.0 - 6km NE of Zakynthos, Greece\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[20.95,37.827,0]},\"id\":\"usc000m0t5\"}],\"bbox\":[-92.0592,-16.841,0,167.4812,37.827,78]}";
    private Utils(){}

public static ArrayList<Earthquake>extractquakes()

    {ArrayList<Earthquake> al=new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(url);
            JSONArray jsonArray=jsonObject.getJSONArray("features");
Log.d("here","hi");
            for(int i=0;i<jsonArray.length();i++){

                JSONObject quake=jsonArray.getJSONObject(i);
                JSONObject prop=quake.getJSONObject("properties");
                double mag=prop.getDouble("mag");
                String place=prop.getString("place");
                long time=prop.getLong("time");
                String url=prop.getString("url");
               /* Date date=new Date(Long.valueOf(time));
                SimpleDateFormat format=new SimpleDateFormat("DD MM YYYY");
                String mydate=format.format(date);*/
                Earthquake earthquake=new Earthquake(mag,place,time,url);
                al.add(earthquake);

            }
        }
        catch(Exception e){
            Log.d("er",e.getMessage());
        }
        return al;
    }

}
