/*
 * First Class Tax app
 * Main activity class, used as the main menu of the application.
 */
package com.firstclasstax;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Brandon on 5/21/16.
 */
public class DepartingDate extends AppCompatActivity implements View.OnClickListener{


    // Variables
    private static Button button_sbm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departing_date);

    }

    @Override
    public void onClick(View v) {

    }
}


