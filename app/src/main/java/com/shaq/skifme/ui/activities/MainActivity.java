package com.shaq.skifme.ui.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.shaq.skifme.R;
import com.shaq.skifme.data.AuthPost;
import com.shaq.skifme.data.AuthSaltResponse;
import com.shaq.skifme.data.LoginParams;
import com.shaq.skifme.data.Sessions;
import com.shaq.skifme.network.APIService;
import com.shaq.skifme.utils.ConstantManager;
import com.shaq.skifme.utils.Md5Convert;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button signIn_btn;
    private EditText login_et, password_et;
    private APIService mAPIService;
    public static final String TAG ="MainActivity";
    public static final String TAG_R = ConstantManager.RETROFIT_TAG;

    SharedPreferences mSettings;

    public static String salt;

    public static final String USER_PREFERENCES = "user_params";







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signIn_btn = (Button) findViewById(R.id.login_btn);
        login_et = (EditText) findViewById(R.id.login_email_et);
        password_et = (EditText) findViewById(R.id.login_password_et);

        signIn_btn.setOnClickListener(this);



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantManager.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mAPIService = retrofit.create(APIService.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case    R.id.login_btn:
                //String email = login_et.getText().toString().trim();
                String language = ConstantManager.POST_LANGUAGE;
                //String pass = password_et.getText().toString().trim();
                //String salt = "fbf932e1-338c-4e28-aecd-d67295ce46a8";
                String email = "karimvrus2@gmail.com";
                String pass = "12mn17bh";
                if(!TextUtils.isEmpty(email)) {
                    sendPost(email, language);
                    if (MainActivity.salt !=null) {
                        loginSubmit(email, MainActivity.salt, pass, language);
                        startMapActivity();
                    }
                } else showToast("Введите e-mail");

                break;
        }
    }


    //Get salt
    public void sendPost(String email, String language) {

        AuthPost postBody = new AuthPost();
        postBody.setEmail(email);
        postBody.setLanguage(language);

        mAPIService.savePost(postBody).enqueue(new Callback<AuthSaltResponse>() {
            @Override
            public void onResponse(Call<AuthSaltResponse> call, Response<AuthSaltResponse> response) {

                MainActivity.salt = response.body().getSalt();
                Log.v(TAG,MainActivity.salt);
            }

            @Override
            public void onFailure(Call<AuthSaltResponse> call, Throwable t) {
                showToast("error");
                Log.e(TAG,"cannot resolve");
            }
        });

    }


    //Create session
    public void loginSubmit (String email, String salt, String pass, String language){

        LoginParams loginBody = new LoginParams();
        loginBody.setEmail(email);
        String hash = Md5Convert.calcMd5(salt,pass);
        loginBody.setHash(hash);

        loginBody.setLanguage(language);

        mAPIService.login(loginBody).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast(String.valueOf(response.code())+" Session ok");
                Log.v(TAG,response.toString());


            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("Fail",t.toString());
            }
        });
    }

    public void startMapActivity () {
        Intent intent = new Intent(this, MapsActivity.class);
               startActivity(intent);
        Log.v(TAG,"Started Map");
    }

    // Get sessions body
    public void getSessionResponse () {
        mAPIService.getSessions().enqueue(new Callback<Sessions>() {
            @Override
            public void onResponse(Call<Sessions> call, Response<Sessions> response) {
                Log.d(TAG_R+"get",response.toString());
            }

            @Override
            public void onFailure(Call<Sessions> call, Throwable t) {
                Log.d(TAG_R, t.toString());

            }
        });
    }




}
