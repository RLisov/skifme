package com.shaq.skifme.utils;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.shaq.skifme.data.room.AppDatabase;

public class SkifApplication extends Application {

    public static SharedPreferences sSharedPreferences;
    public static AppDatabase mDatabase;
    public static SkifApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        sSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mDatabase = Room.databaseBuilder(this, AppDatabase.class, "database")
                .build();
    }

    public static SharedPreferences getSharedPreferences() {
        return sSharedPreferences;
    }

    public AppDatabase getDatabase() {
        return mDatabase;
    }

    public static SkifApplication getInstance() {
        return instance;
    }


}
