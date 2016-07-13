package com.example.suhail.onmyway;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView;

public class MainActivity extends AppCompatActivity {

    private String[] addresses = {"Ajax ON", "New York", "Toronto ON", "Canada's Wonderland", "CN Tower", "Toronto Zoo", "Rogers Center", "Ontario Science Center", "Toronto Islands", "Toronto Eaton Center", "Tim Hortons"};
    String address;
    public final static String EXTRA_MESSAGE = "Address";
    private Toast toastObject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        returnSelectedAddress();
    }

    private void returnSelectedAddress(){
        ListAdapter myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, addresses);
        ListView addressListView = (ListView) findViewById(R.id.addressListView);
        addressListView.setAdapter(myAdapter);

        addressListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        address = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(MainActivity.this, address + ": Selected!", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    public void searchIconMain(View view) {

        if(address != null) {
            Intent intent = new Intent(this, activity_map.class);
            intent.putExtra("Address", address);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this, "Please select an item first:", Toast.LENGTH_SHORT).show();
        }
    }

    public void addItemMain(View view) {
        Toast.makeText(this, "Add not implemented yet!", Toast.LENGTH_SHORT).show();
    }

    public void editItemMain(View view) {
        Toast.makeText(this, "Edit not implemented yet!", Toast.LENGTH_SHORT).show();
    }

    public void deleteItemMain(View view) {
        Toast.makeText(this, "Delete not implemented yet!", Toast.LENGTH_SHORT).show();
    }

    public void myLocationMain(View view) {
        Intent intent = new Intent(this, activity_map.class);
        startActivity(intent);
        finish();
    }
}


