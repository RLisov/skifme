package com.shaq.skifme.ui.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.shaq.skifme.R;
import com.shaq.skifme.data.req.LoginBody;
import com.shaq.skifme.data.managers.DataManager;
import com.shaq.skifme.utils.ConstantManager;
import com.shaq.skifme.utils.NetworkStatusChecker;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity implements View.OnClickListener, View.OnFocusChangeListener {

    private Button signIn_btn, toggle_pass_btn;
    private EditText login_et, password_et;
    private TextView register_tv, forgot_pass_tv,lang_tv;
    AlertDialog.Builder mAlertBuilder;

    private static final String TAG ="MainActivity";
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
        toggle_pass_btn = (Button) findViewById(R.id.toogle_pass_btn);
        lang_tv = (TextView) findViewById(R.id.lang_tv);
        signIn_btn.setOnClickListener(this);
        register_tv.setOnClickListener(this);
        login_et.setOnFocusChangeListener(this);
        password_et.setOnFocusChangeListener(this);
        lang_tv.setOnClickListener(this);
        mDataManager = DataManager.getInstance();

        settingLangDialog();


        toggle_pass_btn.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {

                switch ( event.getAction() ) {
                    case MotionEvent.ACTION_DOWN:
                        password_et.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                    case MotionEvent.ACTION_UP:
                        password_et.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        break;
                }
                return true;
            }
        });


    }

    public void settingLangDialog () {
        Resources res = getResources();
        final String[] langs = res.getStringArray(R.array.langs_array);

        mAlertBuilder = new AlertDialog.Builder(this);
        mAlertBuilder.setTitle(R.string.pick_language)
                .setItems(R.array.langs_array, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        Log.d(TAG,"Choose "+langs[which]);
                        lang_tv.setText(String.valueOf(langs[which]));
                    }
                });
        //TODO: Change UI Language
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case    R.id.login_btn:
                //String email = login_et.getText().toString().trim();
                //String pass = password_et.getText().toString().trim();
                String email ="karimvrus2@gmail.com";
                String pass = "123321";
                startTopLevelActivity();
                mDataManager.getPreferencesManager().setCachedAuthParams(email,pass);
                if(!TextUtils.isEmpty(email)) {
                    showProgress();
                    //signIn(email,pass);
                    hideProgress();
                    //loginCommit(email, pass);


                } else showToast("Введите данные");
                break;
            case R.id.register_tv:
                startRegisterActivity();

                break;
            case  R.id.lang_tv:
                mAlertBuilder.show();
                Log.d(TAG,"Lang CLicked");
                break;
        }
    }



    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.login_email_et:
                if (!hasFocus) {
                    hideKeyboard(v);
                }
                break;
            case R.id.login_password_et:
                if (!hasFocus) {
                    hideKeyboard(v);
                }
                break;

        }
    }



    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void loginSuccess ( Response<Void> response) {
        Log.d(TAG, response.raw().headers().get(ConstantManager.COOKIES));

        mDataManager.getPreferencesManager().saveCookie(response.raw().headers().get(ConstantManager.COOKIES));
        startTopLevelActivity();
    }

    public void signIn(String email, String pass) {
        if (NetworkStatusChecker.isNetworkAvailible(this)) {

            LoginBody loginBody = new LoginBody()
                    .withUserProviderId(email)
                    .withPassword(pass)
                    .withProviderKey(ConstantManager.PROVIDER_KEY);

            Call<Void> call = mDataManager.loginSubmit(loginBody);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (String.valueOf(response.code()).equals("200")) {
                        loginSuccess(response);
                    } else if (response.code() == 403) {
                        showToast("Неверный логин или пароль");
                    } else {
                        //showToast("Проверьте данные");
                    }

                    Log.d(TAG, String.valueOf(response.code() + response.message()));
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {

                }
            });
        } else {
            hideProgress();
            showToast("Проверьте подключение к сети");
        }
    }

}
