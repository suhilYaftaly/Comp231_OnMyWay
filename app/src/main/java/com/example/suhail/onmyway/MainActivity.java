package com.example.suhail.onmyway;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        if (servicesOK()) {
//            setContentView(R.layout.activity_main);
//
//            if(initMap()) {
//                Toast.makeText(this, "Ready to map!", Toast.LENGTH_SHORT).show();
//                gotoLocation(AJAX_LAT, AJAX_LNG, 13);
//
//                //as of now user must grand permissions to app from phone settings
//                try {
//                    mMap.setMyLocationEnabled(true);
//                } catch (SecurityException e) {
//                    Toast.makeText(this, "My Location not enabled!", Toast.LENGTH_SHORT).show();
//                }
//            } else {
//                Toast.makeText(this, "Map not connected!", Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            Toast.makeText(this, "Services not OK!", Toast.LENGTH_SHORT).show();
//        }
    }



    public void searchBtnMain(View view) throws IOException {

//        TextView tv = (TextView) findViewById(R.id.textViewStaples);
//        String searchString = tv.getText().toString();

//        setContentView(R.layout.content_activity_map);
//        geoLocate(searchString);

        Intent intent = new Intent(this, activity_map.class);
        startActivity(intent);
        finish();
    }
}


