package com.example.android.quakereport;

public class Earthquake {
    private final String mMag;
    private final String mLocation;
    private final String mDate;

    public Earthquake(String mag, String location, String date) {
        this.mMag = mag;
        this.mLocation = location;
        this.mDate = date;
    }

    public String getmMag() {
        return mMag;
    }

    public String getmLocation() {
        return mLocation;
    }

    public String getmDate() {
        return mDate;
    }

}
