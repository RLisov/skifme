package com.shaq.skifme.data.managers;

import android.content.SharedPreferences;

import com.shaq.skifme.utils.ConstantManager;
import com.shaq.skifme.utils.SkifApplication;

import java.util.List;

public class PreferencesManager {

    private SharedPreferences mSharedPreferences;


    public PreferencesManager() {
        this.mSharedPreferences = SkifApplication.getSharedPreferences();
    }

    public void saveCookie(String cookie) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString("Set-cookie",cookie);
        editor.apply();
    }

    public String getCookie() {
        return  mSharedPreferences.getString(ConstantManager.COOKIES,"null");
    }

    public void setCachedAuthParams(String email, String pass) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString("CachedEmail",email);
        editor.putString("CachedPass",pass);
        editor.apply();
    }

    public void setSelectedGeoName(String name) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(ConstantManager.GEO_NAME,name);
        editor.apply();
    }

    public String getSelectedGeoName() {
        return mSharedPreferences.getString(ConstantManager.GEO_NAME,"null");
    }

    public String getCachedAuthEmail() {
        return mSharedPreferences.getString("CachedEmail",null);
    }

    public String getCachedAuthPass() {
        return mSharedPreferences.getString("CachedPass",null);
    }
}
