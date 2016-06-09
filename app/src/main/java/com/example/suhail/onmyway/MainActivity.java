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

    public void searchIconMain(View view) {
        Toast.makeText(this, "search not implemented yet!", Toast.LENGTH_SHORT).show();

    }

    public void addItemMain(View view) {
        Toast.makeText(this, "add not implemented yet!", Toast.LENGTH_SHORT).show();
    }

    public void editItemMain(View view) {
        Toast.makeText(this, "edit not implemented yet!", Toast.LENGTH_SHORT).show();
    }

    public void deleteItemMain(View view) {
        Toast.makeText(this, "delete not implemented yet!", Toast.LENGTH_SHORT).show();
    }

    public void myLocationMain(View view) {
        Intent intent = new Intent(this, activity_map.class);
        startActivity(intent);
        finish();
    }
}


