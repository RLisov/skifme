package com.shaq.skifme.ui.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.shaq.skifme.R;
import com.shaq.skifme.network.APIService;
import com.shaq.skifme.utils.ConstantManager;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends BaseActivity implements View.OnFocusChangeListener {

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

        reg_name_et.setOnFocusChangeListener(this);
        reg_pass_et.setOnFocusChangeListener(this);
        reg_email_et.setOnFocusChangeListener(this);

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
                String email = reg_email_et.getText().toString().trim();
                String name = reg_name_et.getText().toString().trim();
                String pass = reg_pass_et.getText().toString().trim();
//                String email ="karim3vrus2@gmail.com";
//                String name ="Rus32";
//                String pass ="123321";
                String lang_key = RegisterActivity.language;
                registrationCommit(email, name, pass,lang_key);

            }
        });
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.reg_name_et:
                if (!hasFocus) {
                    hideKeyboard(v);
                }
                break;
            case R.id.reg_email_et:
                if (!hasFocus) {
                    hideKeyboard(v);
                }
                break;
            case R.id.reg_pass_et:
                if (!hasFocus) {
                    hideKeyboard(v);
                }
                break;

        }
    }


}
