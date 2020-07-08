package com.example.android.quakereport;

public class Earthquake {
    private final double mMag;
    private final String mLocation;
    private final long mTimeInMilliseconds;
    private String mURL;


    public Earthquake(double mag, String location, long timeInMilliseconds, String url) {
        this.mMag = mag;
        this.mLocation = location;
        this.mTimeInMilliseconds = timeInMilliseconds;
        this.mURL = url;
    }

    public double getmMag() {
        return mMag;
    }

    public String getmLocation() {
        return mLocation;
    }

    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    public String getmURL() {
        return mURL;
    }

}
