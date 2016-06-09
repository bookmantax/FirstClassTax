package com.firstclasstax;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Brandon on 5/23/2016.
 */

// TODO: Leave autocomplete for city and state, get data and return a string to address field in add/edit user

public class UserAddress extends AppCompatActivity {

    private static Button button_sbm;
    EditText user_address_line1, user_address_line2, user_address_city, user_address_state, user_address_zip;
    String line1 = " ", line2 = " ", city = " ", state = " ", zip = " ";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_address);
//
        OnClickButtonListenerDone();
//
        //Initialize EditText Variables
        user_address_line1 = (EditText) findViewById(R.id.address_line1_editText);
        user_address_line2 = (EditText) findViewById(R.id.address_line2_editText);
        user_address_city = (EditText) findViewById(R.id.address_city_editText);
        user_address_state = (EditText) findViewById(R.id.address_state_editText);
        user_address_zip = (EditText) findViewById(R.id.address_zipcode_editText);


    }

//        /////////////////// Validate ///////////////////
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_main);
////
////        address_city_EditText = (EditText) findViewById(R.id.address_city_editText);
////        address_state_EditText = (EditText) findViewById(R.id.address_state_editText);
////
////        findViewById(R.id.address_btn_done).setOnClickListener(new View.OnClickListener() {
////
////            @Override
////            public void onClick(View arg0) {
////
////                final String email = address_city_EditText.getText().toString();
////                if (!isValidEmail(email)) {
////                    address_city_EditText.setError("Invalid Email");
////                }
////
////                final String pass = address_state_EditText.getText().toString();
////                if (!isValidPassword(pass)) {
////                    address_state_EditText.setError("Invalid Password");
////                }
////
////            }
////        });
//        ////////////////////////////////////////////////
//    }
//
    public void OnClickButtonListenerDone() {
        button_sbm = (Button) findViewById(R.id.address_btn_done);
        button_sbm.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        line1 = user_address_line1.toString();
                        line2 = user_address_line2.toString();
                        city = user_address_city.toString();
                        state = user_address_state.toString();
                        zip = user_address_zip.toString();
                        String address = line1 + ", " + line2 + ", " + city + ", " + state + ", " + zip ;

                        Intent intentBundle = new Intent(UserAddress.this, EditUserInfo.class);
                        Bundle bundle = new Bundle();
// TODO: create method in database helper to update address in user info table (add mydb here and call db method passing string)
//                        bundle.putString(address, null);
//                        intentBundle.putExtras(bundle);
                        startActivity(intentBundle);
                    }
                }
        );
    }

//
//    public static String userAddress(String line1, String line2, String city, String state, String zip) {
//        String address = line1 + ", " + line2 + ", " + city + ", " + state + ", " + zip ;
//        return address;
//    }

////////////////// VALIDATE //////////////////
//// TODO: Finish and test Validation
//    private EditText address_city_EditText;
//    private EditText address_state_EditText;
//
//    // validating email id
//    private boolean isValidEmail(String email) {
//        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
//                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
//
//        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
//        Matcher matcher = pattern.matcher(email);
//        return matcher.matches();
//    }
//
//    // validating password with retype password
//    private boolean isValidPassword(String pass) {
//        if (pass != null && pass.length() > 6) {
//            return true;
//        }
//        return false;
    }

