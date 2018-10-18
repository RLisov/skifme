package com.shaq.skifme.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.shaq.skifme.R;
import com.shaq.skifme.data.Language;
import com.shaq.skifme.data.LoginBody;
import com.shaq.skifme.data.RegisterBody;
import com.shaq.skifme.data.Timezone;
import com.shaq.skifme.network.APIService;
import com.shaq.skifme.utils.ConstantManager;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends BaseActivity {

    private EditText reg_name_et, reg_email_et, reg_pass_et;
    private RadioButton en_radio, kz_radio, ru_radio;
    private Button reg_btn;
    private APIService mAPIService;
    private static final String TAG = ConstantManager.REG_TAG;
    private  static String language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = (Toolbar) findViewById(R.id.reg_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        reg_name_et = (EditText) findViewById(R.id.reg_name_et);
        reg_email_et = (EditText) findViewById(R.id.reg_email_et);
        reg_pass_et = (EditText) findViewById(R.id.reg_pass_et);
        reg_btn = (Button) findViewById(R.id.reg_btn);
        en_radio = (RadioButton) findViewById(R.id.en_radio_btn);
        kz_radio = (RadioButton) findViewById(R.id.kz_radio_btn);
        ru_radio = (RadioButton) findViewById(R.id.ru_radio_btn);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantManager.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mAPIService = retrofit.create(APIService.class);


        //Backbutton listener
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });

        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String email = reg_email_et.getText().toString().trim();
//                String name = reg_name_et.getText().toString().trim();
//                String pass = reg_pass_et.getText().toString().trim();
                String email ="karim3vrus2@gmail.com";
                String name ="Rus32";
                String pass ="123321";
                String lang_key = RegisterActivity.language;
                registrationCommit(email, name, pass,lang_key);
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.en_radio_btn:
                if (checked){
                    RegisterActivity.language = "en_EN";
                    Log.d(TAG,"lang is"+language);
                }
                break;
            case R.id.kz_radio_btn:
                if (checked){
                    RegisterActivity.language = "kz_KZ";
                }
                break;
            case R.id.ru_radio_btn:
                if (checked) {
                    RegisterActivity.language = "ru_RU";
                    Log.d(TAG,"lang is"+RegisterActivity.language);
                }
                break;
        }
    }



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
                showToast(String.valueOf(response.code())+String.valueOf(response.message()));
                Log.d(TAG, String.valueOf(response.code())+String.valueOf(response.message()));
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(TAG,t.toString());
            }
        });


    }


}
