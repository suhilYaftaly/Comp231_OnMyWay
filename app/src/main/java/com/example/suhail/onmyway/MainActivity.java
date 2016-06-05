package com.example.suhail.onmyway;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
        setContentView(R.layout.activity_main);
    }
}
