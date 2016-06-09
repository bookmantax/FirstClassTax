/*
 * First Class Tax app
 * Main activity class, used as the main menu of the application.
 */
package com.firstclasstax;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Calendar;

// TODO: Move EditUser Info to Options Menu

public class MainActivity extends AppCompatActivity {

    // Variables
    private static Button button_sbm;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        Calendar cur_cal = Calendar.getInstance();
        cur_cal.setTimeInMillis(System.currentTimeMillis());
        Intent intent = new Intent(this, LocationService.class);
        PendingIntent pi = PendingIntent.getService(this, 0, intent, 0);
        AlarmManager alarm_manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarm_manager.setRepeating(AlarmManager.RTC, cur_cal.getTimeInMillis(), (1000 * 60 * 60 * 2),  pi);

        OnClickButtonListenerCurrentTrip();
        OnClickButtonListenerViewTripHistory();
        OnClickButtonListenerTaxDeductionsToDate();
        OnClickButtonListenerPerDiemSearch();
        OnClickButtonListenerEditUserInfo();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    // Current Trip Listener
    public void OnClickButtonListenerCurrentTrip() {
        button_sbm = (Button) findViewById(R.id.main_Menu_Current_Trip_Btn);
        button_sbm.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.firstclasstax.CurrentTrip");
                        startActivity(intent);
                    }
                }
        );
    }

    // View Trip History Listener
    public void OnClickButtonListenerViewTripHistory() {
        button_sbm = (Button) findViewById(R.id.main_Menu_History_Btn);
        button_sbm.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.firstclasstax.ViewTripHistory");
                        startActivity(intent);
                    }
                }
        );
    }

    // Tax Deductions To Date Listener
    public void OnClickButtonListenerTaxDeductionsToDate() {
        button_sbm = (Button) findViewById(R.id.main_Menu_Deductions_Btn);
        button_sbm.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.firstclasstax.TaxDeductionsToDate");
                        startActivity(intent);
                    }
                }
        );
    }

    // Per Diem Search Listener
    public void OnClickButtonListenerPerDiemSearch() {
        button_sbm = (Button) findViewById(R.id.main_Menu_Search_Btn);
        button_sbm.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.firstclasstax.PerDiemSearch");
                        startActivity(intent);
                    }
                }
        );
    }

    // View Trip History Listener
    public void OnClickButtonListenerEditUserInfo() {
        button_sbm = (Button) findViewById(R.id.main_Menu_Edit_Btn);
        button_sbm.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.firstclasstax.EditUserInfo");
                        startActivity(intent);
                    }
                }
        );
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.firstclasstax/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.firstclasstax/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
