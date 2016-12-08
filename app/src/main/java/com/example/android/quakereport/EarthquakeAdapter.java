package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
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
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item, parent, false);
        }
        Earthquake earthquake = getItem(position);
        TextView mag, date, loc,time,prim;
        mag = (TextView) view.findViewById(R.id.magnitude);
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
        double magnitude=earthquake.getMagnitude();
        mag.setText(magnitudeformat(magnitude));
        loc.setText(location);
        prim.setText(primary);
        date.setText(formatDate(new Date(earthquake.getMilli())));
        time.setText(formatTime(new Date(earthquake.getMilli())));

        GradientDrawable gradientDrawable=(GradientDrawable)mag.getBackground();
        Log.d("mag",magnitude+"");
        int color=getcolor(magnitude);
        Log.d("col",color+"");
        if(gradientDrawable==null)Log.d("n","ill");
        else Log.d("ada","sd");
        gradientDrawable.setColor(color);
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
    private String magnitudeformat(double d){
        DecimalFormat decimalFormat=new DecimalFormat("0.0");
        return decimalFormat.format(d);

    }
    private int getcolor(double mag){
        int colorid;
        int color= (int)Math.floor(mag);

        switch (color){
            case 0:
            case 1:
                colorid=R.color.magnitude1;
                break;
            case 2:colorid=R.color.magnitude2;break;
            case 3:colorid=R.color.magnitude3;break;
            case 4:colorid=R.color.magnitude4;break;
            case 5:colorid=R.color.magnitude5;break;
            case 6:colorid=R.color.magnitude6;break;
            case 7:colorid=R.color.magnitude7;break;
            case 8:colorid=R.color.magnitude8;break;
            case 9:colorid=R.color.magnitude9;break;
            default:colorid=R.color.magnitude10plus;break;
        }
        return ContextCompat.getColor(getContext(),colorid);
    }
}
