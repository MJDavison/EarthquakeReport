package com.example.android.quakereport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
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

        TextView magitudeTextView = listItemView.findViewById(R.id.current_mag);
        TextView locationTextView = listItemView.findViewById(R.id.current_location);
        TextView dateTextView = listItemView.findViewById(R.id.current_date);

        magitudeTextView.setText(currentEarthQuake.getmMag());
        locationTextView.setText(currentEarthQuake.getmLocation());
        dateTextView.setText(currentEarthQuake.getmDate());

        return listItemView;
    }
}
