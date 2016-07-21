package com.example.suhail.onmyway;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Edit_Item_Activity extends AppCompatActivity {
    EditText itemTitle;
    EditText itemDesc;
    EditText itemStore;
    EditText itemRange;
    CheckBox itemEnabled;
    MYDBHandler dbHandler;
    TextView er;
    String value;
    String []  str1=new String[10];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__item_);
        Intent intent = getIntent();
         value = intent.getStringExtra("Key");

        itemTitle=(EditText) findViewById(R.id.itemTitle);
        itemDesc=(EditText)findViewById(R.id.txtDesc);
        itemStore=(EditText)findViewById(R.id.txtStore);
        itemRange=(EditText)findViewById(R.id.txtRange);
        itemEnabled=(CheckBox)findViewById(R.id.chkEnabled);
        er=(TextView)findViewById(R.id.txtviewerror);
        er.setText("");
        er.setFocusable(false);
        dbHandler=new MYDBHandler(this,null,null,1);
        itemTitle.setFocusable(true);

        if (value != null) {
        //load data to edit

            str1=dbHandler.viewItem(value);
            itemTitle.setText(str1[1]);
            itemTitle.setFocusable(false);
            itemDesc.setText(str1[2]);
            itemStore.setText(str1[3]);
            itemRange.setText(str1[4]);
            if (Integer.valueOf(str1[5])==1)
            {itemEnabled.setChecked(true);}
            else {itemEnabled.setChecked(false);}
        }
    }
    public void SaveButtonClicked(View view) {
        boolean errorcheck=false;
        double rng;
        int en=0;
        String str=new String();
        String itm=new String();
        String desc= new String();
        if (itemTitle.getText().length()>0) {
             itm = itemTitle.getText().toString();
        }
        else { errorcheck=true;}
        if(itemDesc.getText().length()>0){
            desc=itemDesc.getText().toString();
        }
        else{errorcheck=true;}
        if (itemStore.getText().length()>0){
             str=itemStore.getText().toString();
        }
        else{errorcheck=true;}

        if (itemRange.getText().length()>0) {
            rng = Double.parseDouble(itemRange.getText().toString());
        }
        else{ rng=1.0;}

        if (itemEnabled.isChecked()){ en=1;}
        if (errorcheck==false) {
            Items item = new Items(itm, str, desc, rng, en);

            if (value != null) {
                item.set_id(Integer.valueOf(str1[0]));
                dbHandler.updateItem(item);
                Toast.makeText(this," Item updated Successfully ",Toast.LENGTH_SHORT).show();
            } else {
                dbHandler.addItem(item);
                Toast.makeText(this," Item saved Successfully ",Toast.LENGTH_SHORT).show();
            }


            finish();
        }
        else
        {er.setText("Please input required fields(*)");}
    }



    public void CancelButtonClicked(View view){
        finish();
    }
}
