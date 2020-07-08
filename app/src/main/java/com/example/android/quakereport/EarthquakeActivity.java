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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private EarthquakeAdapter mAdapter;
    private static final String USGS_REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        EarthquakeAsycTask asycTask = new EarthquakeAsycTask();
        asycTask.execute(USGS_REQUEST_URL);
        //earthquakes.add(new Earthquake(0.1, "Home", 200L, "www.google.com"));
        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        // Old
        //mAdapter = new EarthquakeAdapter(this, android.R.layout.simple_list_item_1, (ArrayList<Earthquake>) earthquakes);
        // New
        mAdapter = new EarthquakeAdapter(this, android.R.layout.simple_list_item_1, new ArrayList<Earthquake>());
        // Failure to change this will cause a nullpointerexception, as the EarthquakeAdapter is trying to find the size of the array, when it doesn't actually exist yet! Alternatively, you could
        // do List<Earthquake> = new ArrayList<Earthquake>(); as a global variable, but that's a waste of memory, so no.
        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(mAdapter);
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Earthquake currentEarthquake = (Earthquake) parent.getItemAtPosition(position);
                Uri earthquakeUri = Uri.parse(currentEarthquake.getmURL());
                Intent webIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);
                startActivity(webIntent);
            }
        });
    }

    private class EarthquakeAsycTask extends AsyncTask<String, Void, List<Earthquake>> {
        @Override
        protected List<Earthquake> doInBackground(String... urls) {
            return QueryUtils.fetchEarthquakeData(urls[0]);
        }

        @Override
        protected void onPostExecute(List<Earthquake> earthquakeList) {
            mAdapter.clear();
            if (earthquakeList != null && !earthquakeList.isEmpty()) {
                mAdapter.addAll(earthquakeList);
            }

        }
    }
}
