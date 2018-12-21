package com.shaq.skifme.data.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity( tableName = "objects")
public class Objects {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String mId;


    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "image")
    private String mImage;

    @ColumnInfo(name = "alert")
    private boolean mAlert;

    @ColumnInfo(name = "battery_level")
    private int mBatteryLevel;

    @ColumnInfo(name = "last_online")
    private int mLastOnline;

    @ColumnInfo(name = "current_place")
    private String mCurrentPlace;

    public Objects(@NonNull String id, String name, String image, boolean alert, int batteryLevel, int lastOnline, String currentPlace) {
        mId = id;
        mName = name;
        mImage = image;
        mAlert = alert;
        mBatteryLevel = batteryLevel;
        mLastOnline = lastOnline;
        mCurrentPlace = currentPlace;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public boolean isAlert() {
        return mAlert;
    }

    public void setAlert(boolean alert) {
        mAlert = alert;
    }

    public int getBatteryLevel() {
        return mBatteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        mBatteryLevel = batteryLevel;
    }

    public int getLastOnline() {
        return mLastOnline;
    }

    public void setLastOnline(int lastOnline) {
        mLastOnline = lastOnline;
    }

    public String getCurrentPlace() {
        return mCurrentPlace;
    }

    public void setCurrentPlace(String currentPlace) {
        mCurrentPlace = currentPlace;
    }
}
