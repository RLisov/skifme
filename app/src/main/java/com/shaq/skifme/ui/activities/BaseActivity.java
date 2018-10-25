package com.shaq.skifme.ui.activities;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.shaq.skifme.R;
import com.shaq.skifme.data.Language;
import com.shaq.skifme.data.LoginBody;
import com.shaq.skifme.data.RegisterBody;
import com.shaq.skifme.data.Timezone;
import com.shaq.skifme.data.Tracks.Response.TracksResponseModel;
import com.shaq.skifme.data.Tracks.Send.PostTracksBody;
import com.shaq.skifme.data.managers.DataManager;
import com.shaq.skifme.data.res.UserInfoMe;
import com.shaq.skifme.network.APIService;
import com.shaq.skifme.utils.ConstantManager;
import com.shaq.skifme.utils.SkifApplication;

import java.util.HashSet;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class BaseActivity extends AppCompatActivity {

    private static final String TAG = ConstantManager.TAG_PREFIX+"BaseActivity";
    protected ProgressDialog mProgressDialog;
    private APIService mAPIService;
    public GoogleMap mMap;
    private Toolbar mToolbar;
    private DataManager mDataManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantManager.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mAPIService = retrofit.create(APIService.class);
        mDataManager = DataManager.getInstance();
    }

    public void showError(String message, Exception error){
        showToast(message);
        Log.e(TAG, String.valueOf(error));
    }

    public void showToast(String message) {

        Toast.makeText(this,message, Toast.LENGTH_LONG).show();
    }

    public void startRegisterActivity () {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void startTopLevelActivity () {
        Intent intent = new Intent(this, TopLevelActivity.class);
        startActivity(intent);
    }

    public void startMapsActivity () {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void startGeozoneActivity() {
        Intent intent = new Intent(this, GeozoneActivity.class);
        startActivity(intent);
    }



    public void showProgress() {
        if (mProgressDialog==null) {
            mProgressDialog = new ProgressDialog(this, R.style.custom_dialog);
            mProgressDialog.setCancelable(false);
            mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            mProgressDialog.show();
            mProgressDialog.setContentView(R.layout.progress_splash);
        } else {
            mProgressDialog.show();
            mProgressDialog.setContentView(R.layout.progress_splash);
        }
    }

    public void hideProgress() {

        if(mProgressDialog != null) {
            if(mProgressDialog.isShowing()) {
                mProgressDialog.hide();
            }
        }

    }

    public void getUserInfoMe () {
        mAPIService.getUserInfo(mDataManager.getPreferencesManager().getCookie()).enqueue(new Callback<UserInfoMe>() {
            @Override
            public void onResponse(Call<UserInfoMe> call, Response<UserInfoMe> response) {
                if(String.valueOf(response.code()).equals("200")) {
                    Log.d(TAG, response.body().toString());
                } else {
                    loginCommit(mDataManager.getPreferencesManager().getCachedAuthEmail(),mDataManager.getPreferencesManager().getCachedAuthPass());
                }

                Log.d(TAG, String.valueOf(response.code()+response.message()));
            }

            @Override
            public void onFailure(Call<UserInfoMe> call, Throwable t) {

            }
        });
    }

    public void loginSuccess ( Response<Void> response) {
        showToast("Логин ок");
        Log.d(TAG, response.raw().headers().get(ConstantManager.COOKIES));

        mDataManager.getPreferencesManager().saveCookie(response.raw().headers().get(ConstantManager.COOKIES));
        startTopLevelActivity();
    }

    //Login submit
    public void loginCommit (String email, String pass) {
        LoginBody loginBody = new LoginBody()
                .withUserProviderId(email)
                .withPassword(pass)
                .withProviderKey(ConstantManager.PROVIDER_KEY);

        Log.d(TAG,loginBody.toString());

        mAPIService.loginSubmit(loginBody).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if(String.valueOf(response.code()).equals("200")) {
                    loginSuccess(response);
                } else if (response.code() == 403) {
                    showToast("Неверный логин или пароль");
                } else {
                    showToast("Проверьте данные");
                }

                Log.d(TAG, String.valueOf(response.code()+response.message()));
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

    }

//    public void getTracks () {
//
//        Auto auto = new Auto();
//        auto.setId("7659eaa7-8bb1-47c3-8435-577712371920");
//
//        NorthEast nEast = new NorthEast();
//        nEast.setLat(66.66f);
//        nEast.setLon(77.77f);
//
//        SouthWest sWest = new SouthWest();
//        sWest.setLat(33.3f);
//        sWest.setLon(55.5f);
//
//        PostTracksBody postTracksBody = new PostTracksBody();
//        postTracksBody.setAuto(auto);
//        postTracksBody.setNorthEast(nEast);
//        postTracksBody.setSouthWest(sWest);
//        postTracksBody.setTimeFrom("2018-10-16 21:00:00");
//        postTracksBody.setTimeTo("2018-10-17 21:00:00");
//        postTracksBody.setType("mobile");
//        postTracksBody.setZoom(13);
//
//
//        Log.d(TAG,postTracksBody.toString());
//
//        mAPIService.getTracksPost(postTracksBody).enqueue(new Callback<List<TracksResponseModel>>() {
//            @Override
//            public void onResponse(Call<List<TracksResponseModel>> call, Response<List<TracksResponseModel>> response) {
//
//                Log.d(TAG, response.body().get(6).getX().toString());
//                //dataTracks = new ArrayList<>(Arrays.asList(jsonResponse.getTracksModel()));
//                //Log.d(TAG,dataTracks.get(0).toString());
//            }
//
//            @Override
//            public void onFailure(Call<List<TracksResponseModel>> call, Throwable t) {
//
//                Log.d(TAG+"error",t.toString());
//            }
//        });
//
//    }

    //Registration
    public void registrationCommit (String email, String name, String pass, String lang_key ) {

        Language lang = new Language();
        lang.setKey(lang_key);

        Timezone tzone = new Timezone();
        tzone.setKey("UTC+3");

        RegisterBody registrationBody = new RegisterBody();
        registrationBody.setName(name);
        registrationBody.setUserProviderId(email);
        registrationBody.setProviderKey(ConstantManager.PROVIDER_KEY);
        registrationBody.setType(ConstantManager.REG_TYPE);
        registrationBody.setPassword(pass);
        registrationBody.setLanguage(lang);
        registrationBody.setTimezone(tzone);
        Log.d(TAG, registrationBody.toString());

        mAPIService.registrationSubmit(registrationBody).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(String.valueOf(response.code()).equals("200")) {
                    startTopLevelActivity();
                } else showToast("Проверьте данные");

                showToast(String.valueOf(response.code())+String.valueOf(response.message()));

                Log.d(TAG, String.valueOf(response.code())+String.valueOf(response.message()));
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(TAG,t.toString());
            }
        });


    }



    public void fillTrackOptions(List<TracksResponseModel> list) {



    }

}
