package com.shaq.skifme.data.managers;

import android.content.Context;
import android.preference.PreferenceManager;

import com.shaq.skifme.network.APIService;
import com.shaq.skifme.utils.ConstantManager;
import com.shaq.skifme.utils.SkifApplication;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataManager {

    private Context mContext;
    private PreferencesManager mPreferencesManager;
    private static DataManager INSTANCE = null;
    private APIService mAPIService;

    public DataManager() {
        this.mPreferencesManager = new PreferencesManager();
    }

    public static DataManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DataManager();
        }
        return INSTANCE;
    }



    //TO DO: add network service

    public PreferencesManager getPreferencesManager() {
        return  mPreferencesManager;
    }


}
