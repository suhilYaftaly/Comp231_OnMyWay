package com.example.suhail.onmyway;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class activity_map extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    GoogleMap mMap; //member variable
    private GoogleApiClient mLocationClient;
    private Marker addressMarker, myLocationMarker;
//    private LocationListener mListener; //will be used for later
    private EditText searchEditText;
    String storeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_map);

        if(initMap()) {
            Intent intent = getIntent();
            String addressMessage = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
            storeName = intent.getStringExtra("storeSearch");

            mLocationClient = new GoogleApiClient.Builder(this).addApi(LocationServices.API)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();
                    
           // To display store name in search bar
            if (storeName!= null) {
                try {
                    TextView tv = (TextView) findViewById(R.id.editTextSearch);
                    tv.setText(this.storeName);
                    geoLocate(this.storeName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else  if (addressMessage == null) {
                mLocationClient.connect();
            } else {
                try {
                    geoLocate(addressMessage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            Toast.makeText(this, "Map not connected!", Toast.LENGTH_SHORT).show();
        }

        //view and handle search icon on keyboard
        searchEditText = (EditText)findViewById(R.id.editTextSearch);
        searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String searchString = searchEditText.getText().toString();
                    try {
                        hideSoftKeyboard(v);
                        geoLocate(searchString);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
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

    public void hideSoftKeyboard(View v) {
        InputMethodManager imm =(InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    public void geoLocate(String searchString) throws IOException {
        Geocoder gc = new Geocoder(this);
        List<Address> list = gc.getFromLocationName(searchString, 3);

        if(list.size() > 0) {
            android.location.Address add = list.get(0);
            String locality = add.getLocality();
            Toast.makeText(this, "Found: "+ locality, Toast.LENGTH_SHORT).show();

            double lat = add.getLatitude();
            double lng = add.getLongitude();
            gotoLocation(lat, lng, 17);

            if(addressMarker != null){
                addressMarker.remove();
            }
            MarkerOptions options = new MarkerOptions().title(locality).position(new LatLng(lat, lng));
            addressMarker = mMap.addMarker(options);

        } else {
            Toast.makeText(this, "No results found for: "+ searchString, Toast.LENGTH_LONG).show();
        }
    }

    public void searchBtnMap(View view) throws IOException {
        hideSoftKeyboard(view);

        TextView tv = (TextView) findViewById(R.id.editTextSearch);
        String searchString = tv.getText().toString();

        geoLocate(searchString);
    }


    //set initial state to current location
    public void setCurrentLocation(){
        try {
            Location currentLocation = LocationServices.FusedLocationApi.getLastLocation(mLocationClient);
            if(currentLocation == null) {
                mLocationClient.connect();
            } else {
                LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
                CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLng, 15);
                mMap.animateCamera(update);

                if(myLocationMarker != null){
                    myLocationMarker.remove();
                }
                MarkerOptions options = new MarkerOptions().title(currentLocation.getLatitude() +
                        ", " + currentLocation.getLongitude()).position(latLng)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_my_location_marker));
                myLocationMarker = mMap.addMarker(options);
            }
        } catch (SecurityException e) {
            e.printStackTrace();
            Toast.makeText(this, "My Location not enabled!", Toast.LENGTH_SHORT).show();
        }
    }


    public void showCurrentLocation(View view) {
        setCurrentLocation();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        setCurrentLocation();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onPause() {
        super.onPause();
//        LocationServices.FusedLocationApi.removeLocationUpdates(mLocationClient, mListener);
    }

    public void normalMapView(View view) {
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    public void satelliteMapView(View view) {
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
    }

    public void terrainMapView(View view) {
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
    }

    public void hybridMapView(View view) {
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    }
//    //use this function to do recurringLocationChecks
//    public void recurringLocationCheck() {
//        mListener = new LocationListener() {
//
//            //called automatically each time you send out a request and a new location comes back
//            @Override
//            public void onLocationChanged(Location location) {
//
//                //execute when location changes
//                Toast.makeText(activity_map.this, "Location changed: " + location.getLatitude() +
//                        ", " + location.getLongitude(), Toast.LENGTH_SHORT).show();
//                gotoLocation(location.getLatitude(), location.getLongitude(), 15);
//
//            }
//        };
//
//        LocationRequest request = LocationRequest.create();
//        request.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
//        request.setInterval(10000);
//        request.setFastestInterval(5000);
//
//        try{
//            LocationServices.FusedLocationApi.requestLocationUpdates(mLocationClient, request, mListener);
//        } catch (SecurityException e) {
//            e.printStackTrace();
//        }
//    }
}

