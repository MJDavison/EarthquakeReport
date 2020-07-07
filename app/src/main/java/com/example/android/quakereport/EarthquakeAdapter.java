package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOCATION_SEPERATOR = " of ";

    public EarthquakeAdapter(@NonNull Context context, int resource, ArrayList<Earthquake> quakes) {
        super(context, resource, quakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Earthquake currentEarthQuake = getItem(position);

        TextView magnitudeTextView = listItemView.findViewById(R.id.current_mag);
        TextView primaryLocationTextView = listItemView.findViewById(R.id.current_primary_location);
        TextView offsetLocationTextView = listItemView.findViewById(R.id.current_offset_location);
        // TextView locationTextView = listItemView.findViewById(R.id.current_location);
        TextView dateTextView = listItemView.findViewById(R.id.current_date);
        TextView timeTextView = listItemView.findViewById(R.id.current_time);


        magnitudeTextView.setText(formatMagnitude(currentEarthQuake.getmMag()));
        //locationTextView.setText(currentEarthQuake.getmLocation());
        String primaryLocation;
        String offsetLocation;

        String originalLocation = currentEarthQuake.getmLocation();

        if (originalLocation.contains(LOCATION_SEPERATOR)) {
            String[] locationArray = originalLocation.split(LOCATION_SEPERATOR);
            offsetLocation = locationArray[0] + LOCATION_SEPERATOR;
            primaryLocation = locationArray[1];
        } else {
            offsetLocation = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        primaryLocationTextView.setText(primaryLocation);
        offsetLocationTextView.setText(offsetLocation);


        Date date = new Date(currentEarthQuake.getTimeInMilliseconds());

        dateTextView.setText(formatDate(date));
        timeTextView.setText(formatTime(date));

        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();
        int magnitudeColor = getMagnitudeColor(currentEarthQuake.getmMag());
        magnitudeCircle.setColor(magnitudeColor);

        return listItemView;
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceID;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceID = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceID = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceID = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceID = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceID = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceID = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceID = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceID = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceID = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceID = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceID);
    }

    private String formatMagnitude(double mag) {
        DecimalFormat magnitudeFormatter = new DecimalFormat("0.0");
        return magnitudeFormatter.format(mag);
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM DD, yyyy");
        return dateFormatter.format(dateObject);
    }


    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm aa");
        return timeFormatter.format(dateObject);
    }
}
