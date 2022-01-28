package com.example.android.quakereport;

import java.util.Date;

public class Earthquake
{
    /* Magnitude of the earthquake */
    private double mMagnitude;
    /* Location of the earthquake */
    private String mLocation;
    /* Date of the earthquake */
    private String mDate;
    /*Time in milliseconds */
    private long mTimeInMilliseconds;
    /*Url of the earthquake website*/
    private String mUrl;

    /**
     *
     * @param magnitude    magnitude of earthquake
     * @param place        place where earthquake occurred
     * @param TimeInMilliseconds         time of earthquake
     */
    public Earthquake(double magnitude, String place, long TimeInMilliseconds, String url)
    {
        this.mMagnitude = magnitude;
        this.mLocation = place;
        this.mTimeInMilliseconds = TimeInMilliseconds;
        this.mUrl = url;
    }

    /**
     *    Returns the magnitude of earthquake
     */
    public double getMagnitude()
    {
        return mMagnitude;
    }

    /**
     *    Returns the location of earthquake
     */
    public String getLocation()
    {
        return mLocation;
    }

    /**
     *    Returns the date of earthquake
     */
    public String getDate()
    {
        return mDate;
    }

    /**
     *    Returns the time of earthquake
     */
    public long getTimeInMilliseconds()
    {
        return mTimeInMilliseconds;
    }

    /**
     *    Returns the url of the website of earthquake
     */
    public String getUrl()
    {
        return mUrl;
    }
}
