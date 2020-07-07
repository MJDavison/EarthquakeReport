package com.example.android.quakereport;

public class Earthquake {
    private final double mMag;
    private final String mLocation;
    private final long mTimeInMilliseconds;

    public Earthquake(double mag, String location, long timeInMilliseconds) {
        this.mMag = mag;
        this.mLocation = location;
        this.mTimeInMilliseconds = timeInMilliseconds;
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

}
