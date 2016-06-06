package com.example.suhail.onmyway;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class activity_map extends AppCompatActivity {

    GoogleMap mMap; //member variable
    private static final int ERROR_DIALOG_REQUEST = 9001; //constant to request certain king of dialog box definition from google play services lib
    private static final double
            AJAX_LAT = 43.851063,
            AJAX_LNG = -79.019737;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_activity_map);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (servicesOK()) {
            setContentView(R.layout.activity_activity_map);

            if(initMap()) {
                Toast.makeText(this, "Ready to map!", Toast.LENGTH_SHORT).show();
                gotoLocation(AJAX_LAT, AJAX_LNG, 13);

                //as of now user must grand permissions to app from phone settings
                try {
                    mMap.setMyLocationEnabled(true);
                } catch (SecurityException e) {
                    Toast.makeText(this, "My Location not enabled!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Map not connected!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Services not OK!", Toast.LENGTH_SHORT).show();
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

    //get a reference to the map object
    public boolean initMap() {
        if (mMap == null) {
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mMap = mapFragment.getMap();
        }
        return (mMap != null);
    }

    public void gotoLocation(double lat, double lng, float zoom) {

        LatLng latLng = new LatLng(lat, lng);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLng, zoom);
        mMap.animateCamera(update);
    }

}
