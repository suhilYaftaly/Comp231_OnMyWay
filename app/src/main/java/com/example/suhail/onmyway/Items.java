package com.example.suhail.onmyway;

/**
 * Created by Aisha Naseem on 2016-07-19.
 */
public class Items {private int _id;
    private String _itemname;
    private String description;
    private String store;
    private double range;
    private int enabled;
    public Items(){}

    public Items(String itemname,String storeName,String itemDescription,double reminderRange,int statusEnabled) {
        this._itemname = itemname;
        this.store=storeName;
        this.description=itemDescription;
        this.range=reminderRange;
        this.enabled=statusEnabled;

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public void set_itemname(String _itemname) {
        this._itemname = _itemname;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_itemname() {
        return _itemname;
    }

    public int get_id() {
        return _id;
    }
}
