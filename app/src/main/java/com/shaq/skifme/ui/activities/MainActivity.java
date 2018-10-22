package com.shaq.skifme.ui.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.shaq.skifme.R;


import com.shaq.skifme.data.LoginBody;

import com.shaq.skifme.network.APIService;
import com.shaq.skifme.utils.ConstantManager;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button signIn_btn;
    private EditText login_et, password_et;
    private TextView register_tv, forgot_pass_tv;

    public static final String TAG ="MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signIn_btn = (Button) findViewById(R.id.login_btn);
        login_et = (EditText) findViewById(R.id.login_email_et);
        password_et = (EditText) findViewById(R.id.login_password_et);
        register_tv = (TextView) findViewById(R.id.register_tv);
        forgot_pass_tv = (TextView) findViewById(R.id.forgot_pass_tv);

        signIn_btn.setOnClickListener(this);
        register_tv.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case    R.id.login_btn:
                //String email = login_et.getText().toString().trim();
                //String pass = password_et.getText().toString().trim();
                String email ="karimvrus2@gmail.com";
                String pass = "123321";

                if(!TextUtils.isEmpty(email)) {
                    loginCommit(email, pass);
                    showProgress();
                } else showToast("Введите данные");
                break;
            case R.id.register_tv:
                startRegisterActivity();

                break;
        }
    }









    //Create session
//    public void loginSubmit (String email, String salt, String pass, String language){
//
//        LoginParams loginBody = new LoginParams();
//        loginBody.setEmail(email);
//        String hash = Md5Convert.calcMd5(salt,pass);
//        loginBody.setHash(hash);
//
//        loginBody.setLanguage(language);
//
//        mAPIService.login(loginBody).enqueue(new Callback<Void>() {
//            @Override
//            public void onResponse(Call<Void> call, Response<Void> response) {
//                showToast(String.valueOf(response.code())+" Session ok");
//                Log.v(TAG,response.toString());
//
//
//            }
//
//            @Override
//            public void onFailure(Call<Void> call, Throwable t) {
//                Log.e("Fail",t.toString());
//            }
//        });
//    }










}
