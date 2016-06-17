/*
 * First Class Tax app
 * Main activity class, used as the main menu of the application.
 */
package com.firstclasstax;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class LandingDate extends Activity {


    // Variables
    private static Button button_sbm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_date);

    }
}


