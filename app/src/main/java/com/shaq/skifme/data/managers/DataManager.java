package com.shaq.skifme.data.managers;

import android.content.Context;

import com.shaq.skifme.data.req.LoginBody;
import com.shaq.skifme.data.res.ControlRes;
import com.shaq.skifme.data.res.ObjectsRes;
import com.shaq.skifme.data.room.AppDatabase;
import com.shaq.skifme.network.APIService;
import com.shaq.skifme.network.ServiceGenerator;

import java.util.List;

import retrofit2.Call;

public class DataManager {

    private Context mContext;
    private PreferencesManager mPreferencesManager;
    private static DataManager INSTANCE = null;
    private APIService mAPIService;
    private AppDatabase mDatabase;

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


    public PreferencesManager getPreferencesManager() {
        return  mPreferencesManager;
    }

    // ===============Network=============

    public Call<Void> loginSubmit(LoginBody loginBody) {
        return mAPIService.loginSubmit(loginBody);
    }

    public Call<List<ObjectsRes>> getObjects() {
        return mAPIService.getAllObjects();
    }

    public Call<List<ControlRes>> getControls() { return  mAPIService.getAllControls(); }


}
