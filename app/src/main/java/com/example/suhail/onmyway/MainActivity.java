package com.example.suhail.onmyway;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {
    String dbString;
    ListView lstview;
    MYDBHandler dbHandler;
    String selectedItem;
    ArrayAdapter<String> adapter;
    ArrayList<String> listItems;
    long mLastClickTime;


    private String[] addresses = {"Ajax ON", "New York", "Toronto ON", "Canada's Wonderland", "CN Tower", "Toronto Zoo", "Rogers Center", "Ontario Science Center", "Toronto Islands", "Toronto Eaton Center", "Tim Hortons"};
    String address;
    public final static String EXTRA_MESSAGE = "Address";
    private Toast toastObject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHandler = new MYDBHandler(this, null, null, 1);


        listItems = new ArrayList<String>();
        listItems = dbHandler.databaseToString();
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listItems);
        lstview = (ListView) findViewById(R.id.listView);
        lstview.setAdapter(adapter);

        lstview.setOnItemClickListener(this);


        //returnSelectedAddress();
    }

   /* private void returnSelectedAddress(){
        ListAdapter myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, addresses);
        ListView addressListView = (ListView) findViewById(R.id.addressListView);
        addressListView.setAdapter(myAdapter);

        addressListView.setOnItemClickListener(
                new OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        address = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(MainActivity.this, address + ": Selected!", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }*/

    public void searchIconMain(View view) {

        /*if(address != null) {
            Intent intent = new Intent(this, activity_map.class);
            intent.putExtra("Address", address);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this, "Please select an item first:", Toast.LENGTH_SHORT).show();
        }*/
        //Need to set search location app first
        // To be done in phase 2
        String currentLocation = "Toronto, ON";
        Intent intent = new Intent(this, activity_map.class);
        intent.putExtra("Address", currentLocation);

        dbHandler = new MYDBHandler(this, null, null, 1);
        String[] getItem = dbHandler.viewItem(selectedItem);

        //insert store name or address to call
        intent.putExtra("storeSearch", getItem[3]);
        startActivity(intent);
        Toast.makeText(this, "Searching " + getItem[3] + "in " + currentLocation + ".....", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void editItemMain(View view) {
        if (selectedItem != null) {
            Intent intent = new Intent(this, Edit_Item_Activity.class);
            intent.putExtra("Key", selectedItem);
            startActivity(intent);
        } else {
            Toast.makeText(this, "First select an item !", Toast.LENGTH_SHORT).show();
        }
        displayList();

    }

    public void deleteItemMain(View view) {
        if (selectedItem != null) {
            dbHandler.deleteitem(selectedItem);
            Toast.makeText(this, "" + selectedItem + " Deleted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "First select an item !", Toast.LENGTH_SHORT).show();
        }
        displayList();


    }

    public void myLocationMain(View view) {
        Intent intent = new Intent(this, activity_map.class);
        startActivity(intent);
        finish();
    }

    public void addButtonClicked(View view) {
        Intent intent = new Intent(this, Edit_Item_Activity.class);
        startActivityForResult(intent, 1);

    }
    @Override
    protected void onActivityResult ( int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Intent refresh = new Intent(this, MainActivity.class);
            startActivity(refresh);
            this.finish();
        }
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView temp = (TextView)view;
        selectedItem=String.valueOf(temp.getText());
        Toast.makeText(this,temp.getText()+" Selected",Toast.LENGTH_SHORT).show();
        temp.setBackgroundColor(Color.CYAN);
        for (int i = 0; i < lstview.getChildCount(); i++) {
            if (position == i) {
                lstview.getChildAt(i).setBackgroundColor(Color.BLUE);
            } else {
                lstview.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
            }
        }

        //Double click watcher
        long currTime = System.currentTimeMillis();
        //if (currTime - mLastClickTime < ViewConfiguration.getDoubleTapTimeout()) {
        if (currTime - mLastClickTime < 2000) {
            onItemDoubleClick(parent, view, position, 1);
        }
        mLastClickTime = currTime;
    }

    public void onItemDoubleClick(AdapterView<?> adapterView, View view, int position, long l) {
        //Toast.makeText(this, "Double click !", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this,View_Item_Activity.class);
        intent.putExtra("Key",selectedItem);
        startActivity(intent);
    }


    public  void displayList(){
       adapter.clear();
        //adapter.add(android.R.layout.simple_list_item_1,dbHandler.databaseToString()));
        listItems = dbHandler.databaseToString();

        if (listItems!= null){

            for (String object : listItems) {

                adapter.insert(object, adapter.getCount());
            }
        }

        adapter.notifyDataSetChanged();
        lstview.refreshDrawableState();


    }


}


