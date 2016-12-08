package com.example.android.quakereport;

/**
 * Created by junejaspc on 8/12/16.
 */

public class Earthquake {
    private double magnitude;
    private long milli;
    private String location;

    public Earthquake(double magnitude,String location,long date){
        this.magnitude=magnitude;
        this.location=location;
        this.milli=date;
    }

    public double getMagnitude(){return magnitude;}
    public long getMilli(){return milli;}
    public String getLocation(){return location;}


}
