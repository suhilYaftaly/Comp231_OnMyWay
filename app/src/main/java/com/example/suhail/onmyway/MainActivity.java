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


