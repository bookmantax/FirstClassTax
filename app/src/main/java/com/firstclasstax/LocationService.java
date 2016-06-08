package com.firstclasstax;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Brandon on 6/6/2016.
 */
public class LocationService extends Service{
    private static final String TAG = "FIRSTCLASSTAXGPS";
    private LocationManager mLocationManager = null;
    private static final int LOCATION_INTERVAL = 1000;
    private static final float LOCATION_DISTANCE = 0;
    DatabaseHelper db = new DatabaseHelper(this);

    private class LocationListener implements android.location.LocationListener
    {
        Location mLastLocation;

        public LocationListener(String provider)
        {
            Log.e(TAG, "LocationListener " + provider);
            mLastLocation = new Location(provider);
        }

        @Override
        public void onLocationChanged(Location location)
        {
            Log.e(TAG, "onLocationChanged: " + location);
            mLastLocation.set(location);
        }

        @Override
        public void onProviderDisabled(String provider)
        {
            Log.e(TAG, "onProviderDisabled: " + provider);
        }

        @Override
        public void onProviderEnabled(String provider)
        {
            Log.e(TAG, "onProviderEnabled: " + provider);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras)
        {
            Log.e(TAG, "onStatusChanged: " + provider);
        }
    }

    LocationListener[] mLocationListeners = new LocationListener[] {
            new LocationListener(LocationManager.GPS_PROVIDER),
            new LocationListener(LocationManager.NETWORK_PROVIDER)
    };

    @Override
    public IBinder onBind(Intent arg0)
    {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Log.e(TAG, "onStartCommand");
        super.onStartCommand(intent, flags, startId);
        try {
            Log.i(TAG, "I'm getting your location");
            mLocationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                    mLocationListeners[1]);
            Location l = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if(l != null) {
                Log.i(TAG, "" + l.getLatitude() + " - " + l.getLongitude());
                Date sDate = Calendar.getInstance().getTime();
//                int year = sDate.get(Calendar.YEAR);
//                int month = sDate.get(Calendar.MONTH);      // 0 to 11
//                int day = sDate.get(Calendar.DAY_OF_MONTH);
//                int hour = sDate.get(Calendar.HOUR_OF_DAY);
//                int minute = sDate.get(Calendar.MINUTE);
                boolean isInserted = db.insertTrip(
                        (String.valueOf(l.getLatitude()) + String.valueOf(l.getLongitude())),
//                        month + day + year + " - " + hour + " : " + minute,
//                        month + day + year + " - " + hour + " : " + minute,
                        sDate.toString(), sDate.toString(),
                        String.valueOf(100));
            }
        } catch (java.lang.SecurityException ex) {
            Log.i(TAG, "fail to request location update, ignore", ex);
        } catch (IllegalArgumentException ex) {
            Log.d(TAG, "network provider does not exist, " + ex.getMessage());
        }
        try {
            Log.i(TAG, "I'm getting your location");
            mLocationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                    mLocationListeners[0]);
            Location l = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if(l != null) {
                Log.i(TAG, "" + l.getLatitude() + " - " + l.getLongitude());
                Date sDate = Calendar.getInstance().getTime();
//                int year = sDate.get(Calendar.YEAR);
//                int month = sDate.get(Calendar.MONTH);      // 0 to 11
//                int day = sDate.get(Calendar.DAY_OF_MONTH);
//                int hour = sDate.get(Calendar.HOUR_OF_DAY);
//                int minute = sDate.get(Calendar.MINUTE);
                boolean isInserted = db.insertTrip(
                        (String.valueOf(l.getLatitude()) + String.valueOf(l.getLongitude())),
//                        month + day + year + " - " + hour + " : " + minute,
//                        month + day + year + " - " + hour + " : " + minute,
                        sDate.toString(), sDate.toString(),
                        String.valueOf(100));
            }
        } catch (java.lang.SecurityException ex) {
            Log.i(TAG, "fail to request location update, ignore", ex);
        } catch (IllegalArgumentException ex) {
            Log.d(TAG, "gps provider does not exist " + ex.getMessage());
        }
        return START_STICKY;
    }

    @Override
    public void onCreate()
    {
        Log.e(TAG, "onCreate");
        initializeLocationManager();
    }

    @Override
    public void onDestroy()
    {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
        if (mLocationManager != null) {
            for (int i = 0; i < mLocationListeners.length; i++) {
                try {
                    mLocationManager.removeUpdates(mLocationListeners[i]);
                } catch (Exception ex) {
                    Log.i(TAG, "fail to remove location listners, ignore", ex);
                }
            }
        }
    }

    private void initializeLocationManager() {
        Log.e(TAG, "initializeLocationManager");
        if (mLocationManager == null) {
            mLocationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        }
    }
}
