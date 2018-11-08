package com.shaq.skifme.data.managers;

import android.content.Context;
import android.preference.PreferenceManager;

import com.shaq.skifme.data.LoginBody;
import com.shaq.skifme.data.res.DevicesRes;
import com.shaq.skifme.network.APIService;
import com.shaq.skifme.network.ServiceGenerator;
import com.shaq.skifme.utils.ConstantManager;
import com.shaq.skifme.utils.SkifApplication;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataManager {

    private Context mContext;
    private PreferencesManager mPreferencesManager;
    private static DataManager INSTANCE = null;
    private APIService mAPIService;
    private static EventBus sBus;

    public DataManager() {

        this.mPreferencesManager = new PreferencesManager();
        this.mAPIService = ServiceGenerator.createService(APIService.class);

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

    // ===============Network=============

    public Call<Void> loginSubmit(LoginBody loginBody) {
        return mAPIService.loginSubmit(loginBody);
    }

    public Call<List<DevicesRes>> getDevicesList(String cookie) {
        return  mAPIService.getAllDevicesList(cookie);
    }
}
