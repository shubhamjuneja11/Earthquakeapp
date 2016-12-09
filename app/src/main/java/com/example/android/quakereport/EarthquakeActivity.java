/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {
private String url="http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2014-01-01&endtime=2014-01-02&minmagnitude=4.5";
    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    ArrayList<Earthquake> earthquakes=null;
    EarthquakeAdapter adapter;
    ListView earthquakeListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquake locations.


        // Find a reference to the {@link ListView} in the layout
        earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
       new MyAsyncTask().execute(url);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Earthquake earthquake=adapter.getItem(position);
                Uri uri=Uri.parse(earthquake.getUrl());
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });

    }
    private void updateui(){
        adapter=new EarthquakeAdapter(this,R.layout.earthquake_list_item,earthquakes);
        earthquakeListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
    private class MyAsyncTask extends AsyncTask<String,Void,ArrayList<Earthquake>>{

        @Override
        protected ArrayList<Earthquake> doInBackground(String... params) {
            try {
               return Utils.extractquakes(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Earthquake> earthquak) {
            super.onPostExecute(earthquak);
            earthquakes=earthquak;
            updateui();
        }
    }
}
