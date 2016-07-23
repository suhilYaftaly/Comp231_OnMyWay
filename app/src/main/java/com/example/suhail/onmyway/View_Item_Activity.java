package com.example.suhail.onmyway;

/**
 * Created by Anuja on 7/22/2016.
 */
        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.CheckBox;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

public class View_Item_Activity extends AppCompatActivity {
    EditText itemTitle;
    EditText itemDesc;
    EditText itemStore;
    EditText itemRange;
    CheckBox itemEnabled;
    MYDBHandler dbHandler;
    String value;
    String []  dbValue=new String[10];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        Intent intent = getIntent();
        value = intent.getStringExtra("Key");

        itemTitle=(EditText) findViewById(R.id.itemTitle);
        itemDesc=(EditText)findViewById(R.id.txtDesc);
        itemStore=(EditText)findViewById(R.id.txtStore);
        itemRange=(EditText)findViewById(R.id.txtRange);
        itemEnabled=(CheckBox)findViewById(R.id.chkEnabled);
        dbHandler=new MYDBHandler(this,null,null,1);

        if (value != null) {
            //load data to edit

            dbValue=dbHandler.viewItem(value);
            itemTitle.setText(dbValue[1]);
            itemTitle.setFocusable(false);
            itemDesc.setText(dbValue[2]);
            itemStore.setText(dbValue[3]);
            itemRange.setText(dbValue[4]);
            if (Integer.valueOf(dbValue[5])==1)
            {itemEnabled.setChecked(true);}
            else {itemEnabled.setChecked(false);}
        }
        // Disableing editble property
        itemTitle.setEnabled(false);
        itemDesc.setEnabled(false);
        itemStore.setEnabled(false);
        itemRange.setEnabled(false);
        itemEnabled.setEnabled(false);
        itemTitle.setClickable(false);
        itemDesc.setClickable(false);
        itemStore.setClickable(false);
        itemRange.setClickable(false);
        itemEnabled.setClickable(false);
    }

    public void okButtonClicked(View view){
        finish();
    }
}

