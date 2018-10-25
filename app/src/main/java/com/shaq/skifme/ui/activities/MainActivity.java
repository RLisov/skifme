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
import com.shaq.skifme.data.managers.DataManager;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button signIn_btn;
    private EditText login_et, password_et;
    private TextView register_tv, forgot_pass_tv;

    public static final String TAG ="MainActivity";
    private DataManager mDataManager;


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
        mDataManager = DataManager.getInstance();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case    R.id.login_btn:
                //String email = login_et.getText().toString().trim();
                //String pass = password_et.getText().toString().trim();
                String email ="karimvrus2@gmail.com";
                String pass = "123321";
                mDataManager.getPreferencesManager().setCachedAuthParams(email,pass);

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

}
