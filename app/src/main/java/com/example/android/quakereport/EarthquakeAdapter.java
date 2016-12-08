package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by junejaspc on 8/12/16.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    ArrayList<Earthquake> earthquakes;
    public EarthquakeAdapter(Context context, int earthquake_list_item, ArrayList<Earthquake> earthquakes) {
        super(context,earthquake_list_item,earthquakes);
        this.earthquakes=earthquakes;
        Log.d("in", "super");
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("here", "b");
        View view = convertView;
        if (view == null) {
            Log.d("in", "null");
            view = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item, parent, false);
        }
        Log.d("creating", "view");
        Earthquake earthquake = getItem(position);
        TextView mag, date, loc,time,prim;
        mag = (TextView) view.findViewById(R.id.magntiude);
        loc = (TextView) view.findViewById(R.id.location_offset);
        prim=(TextView)view.findViewById(R.id.primary_location);
        date = (TextView) view.findViewById(R.id.date);
        time=(TextView)view.findViewById(R.id.time);

        String location,primary,original;
        original=earthquake.getLocation();

        if(original.contains("of"))
        {
            String [] parts=original.split("of");
            location=parts[0]+" of";
            primary=parts[1];
        }
        else{
            location="from";
            primary=original;
        }

        mag.setText(earthquake.getMagnitude());
        loc.setText(location);
        prim.setText(primary);
        date.setText(formatDate(new Date(earthquake.getMilli())));
        time.setText(formatTime(new Date(earthquake.getMilli())));
        return view;
    }

    @Override
    public int getCount() {
        Log.d("counting","f");
        return earthquakes.size();

    }
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
}
