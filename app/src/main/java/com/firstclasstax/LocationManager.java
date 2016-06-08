package com.firstclasstax;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Rodrigo on 5/14/2016.
 */
public class LocationManager extends Activity {


    protected Context context;
    TextView txtLat;
    String lat, baseLat;
    String longitude, baseLongitude;


    EditText base_EditText;
    DatabaseHelper db = new DatabaseHelper(this);
    Cursor baseNameCursor, baseCoordinatesCursor;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        txtLat = (TextView) findViewById(R.id.textview1);

        // Get user base airport.
        baseNameCursor = db.getAllData();
        baseNameCursor.moveToFirst();
        if (baseNameCursor != null) {
            String base = baseNameCursor.getString(4);
            Toast.makeText(LocationManager.this, base, Toast.LENGTH_SHORT).show();

            baseCoordinatesCursor = db.getAirportsInfo(base);
            baseCoordinatesCursor.moveToFirst();
            if (baseCoordinatesCursor != null) {
                baseLat = baseCoordinatesCursor.getString(6);
                baseLongitude = baseCoordinatesCursor.getString(7);
                Toast.makeText(LocationManager.this, baseLat + baseLongitude, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(LocationManager.this, "Base Coordinates Cursor is NULL", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(LocationManager.this, "Base Name Cursor is NULL", Toast.LENGTH_SHORT).show();
        }
/*
 * Create a GeoFence for the home airport
 * https://developer.android.com/training/location/geofencing.html
 */



        GPSManager gps = new GPSManager(this);
        //check GPS active
        if (gps.canGetLocation()) {
            lat = String.valueOf(gps.getLatitude());
            longitude = String.valueOf(gps.getLongitude());
        } else {
            gps.showSettingsAlert();
        }
        txtLat.setText(lat + longitude);
    }
}