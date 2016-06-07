package com.example.suhail.onmyway;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;


import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void searchBtnMain(View view) {

        Intent intent = new Intent(this, activity_map.class);
        startActivity(intent);
        finish();
    }

    public void searchIconMain(View view) {
        Intent intent = new Intent(this, activity_map.class);
        startActivity(intent);
        finish();
    }
}


