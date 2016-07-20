package com.example.suhail.onmyway;

/**
 * Created by aisha on 2016-07-19.
 */


import android.content.ClipData;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.widget.Toast;

import java.util.ArrayList;

public class MYDBHandler extends SQLiteOpenHelper {
        private static final int DATABASE_VERSION=4;
        private static final String DATABASE_NAME="items.db";
        public static  final String TABLE_ITEMS="items";
        public static final String COLUMN_ID="_id";
        public static final String COLUMN_ITEMNAME="_itemname";
        public static final String COLUMN_ITEMDESC="itemDescription";
        public static final String COLUMN_ITEMSTORE="itemStore";
        public static final String COLUMN_ITEMRANGE="itemRange";
        public static final String COLUMN_ITEMENABLED="itemEnabled";



        public MYDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String query="CREATE TABLE " + TABLE_ITEMS + "(" +
                    COLUMN_ID + "  INTEGER PRIMARY KEY AUTOINCREMENT ," +
                    COLUMN_ITEMNAME + " TEXT , " + COLUMN_ITEMDESC + " TEXT , " + COLUMN_ITEMSTORE + " TEXT ," +
                    COLUMN_ITEMRANGE + " REAL , " + COLUMN_ITEMENABLED + " INTEGER " +
                    ");";
            db.execSQL(query);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS );
            onCreate(db);
        }

        public String[] viewItem(String itemName){
            int i=0;
            SQLiteDatabase db = getWritableDatabase();

            Cursor cursor = null;
            String[] str = new String[10];
            try{

                cursor = db.rawQuery("SELECT * FROM " + TABLE_ITEMS + " WHERE " + COLUMN_ITEMNAME + "=\"" + itemName + "\";",null);
                cursor.moveToFirst();
                if(cursor.getCount() > 0) {
                    do {

                        str[i]=cursor.getString(cursor.getColumnIndex(cursor.getColumnName(i)));
                        i=i+1;
                    }while(i<=5);
                }

                return str;
            }finally {

                cursor.close();
                db.close();
            }
        }
        // add new row to the database
        public void addItem(Items item){

            ContentValues values = new ContentValues();

            values.put(COLUMN_ITEMNAME,item.get_itemname());
            values.put(COLUMN_ITEMDESC,item.getDescription());
            values.put(COLUMN_ITEMSTORE,item.getStore());
            values.put(COLUMN_ITEMRANGE,item.getRange());
            values.put(COLUMN_ITEMENABLED,item.getEnabled());
            SQLiteDatabase db = getWritableDatabase();
            db.insert(TABLE_ITEMS,null,values);
            db.close();

        }
        //delete an iteem from database
        public void deleteitem(String itemName){

            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("DELETE FROM " + TABLE_ITEMS + " WHERE " + COLUMN_ITEMNAME + "=\"" + itemName + "\";");
        }

    public void updateItem(Items item){
        SQLiteDatabase db = getWritableDatabase();
        String query="UPDATE "+ TABLE_ITEMS+ " SET "+ COLUMN_ITEMDESC +"=\""+ item.getDescription()+ "\", " + COLUMN_ITEMSTORE +
                "=\"" + item.getStore()+ "\", "+ COLUMN_ITEMRANGE + "="+item.getRange()+", "+COLUMN_ITEMENABLED+"="+item.getEnabled()+ " WHERE "+ COLUMN_ID +"=" + item.get_id()+ ";";

    db.execSQL(query);
    }
        //print out the database as string
        public ArrayList<String> databaseToString(){
            int i=0;
            ArrayList<String> dbString=new ArrayList<String>();
            SQLiteDatabase db = getWritableDatabase();
            String query ="SELECT * FROM " + TABLE_ITEMS + " ;";
            //Cursor point to a location in your results
            Cursor c=db.rawQuery(query,null);
            // move to the first row in the results
            c.moveToFirst();
            if ((c != null)&& (c.getCount() > 0) ){
                do{

                    dbString.add(i, c.getString(c.getColumnIndex("_itemname")));
                    i=i+1;

                }while (c.moveToNext());

            }

            c.close();
            db.close();
            return dbString;


        }

}
