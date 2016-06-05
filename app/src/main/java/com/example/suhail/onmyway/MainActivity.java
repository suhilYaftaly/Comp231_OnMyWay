package com.example.suhail.onmyway;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.GoogleMap;

public class MainActivity extends AppCompatActivity {

    GoogleMap mMap; //member variable
    private static final int ERROR_DIALOG_REQUEST = 9001; //constant to request certain king of dialog box definition from google play services lib
    private static final double
            AJAX_LAT = 43.851063,
            AJAX_LNG = -79.019737;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (servicesOK()) {
            setContentView(R.layout.content_activity_map);
            Toast.makeText(this, "Ready to map!", Toast.LENGTH_SHORT).show();
        } else {
            setContentView(R.layout.activity_main);
        }
    }

    //check to see if the google services are ok
    public boolean servicesOK() {

        int isAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        // handle 3 different possibility
        if (isAvailable == ConnectionResult.SUCCESS) { //everything ok, user can make mapping request
            return true;
        } else if (GooglePlayServicesUtil.isUserRecoverableError(isAvailable)) { //error user can do something about
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(isAvailable, this, ERROR_DIALOG_REQUEST);
            dialog.show(); //google play services lib delivers correct dialog box telling user what to do
        } else { //something wrong, user cant do anything about
            Toast.makeText(this, "Can't connect to mapping service", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
