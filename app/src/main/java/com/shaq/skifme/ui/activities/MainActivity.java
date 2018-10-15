package com.shaq.skifme.ui.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;

import com.google.gson.Gson;
import com.shaq.skifme.R;
import com.shaq.skifme.data.AuthPost;
import com.shaq.skifme.data.AuthSaltResponse;
import com.shaq.skifme.data.LoginParams;
import com.shaq.skifme.data.LoginResponse;
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
    public static SharedPreferences sSharedPrefences;
    private APIService mAPIService;
    public static final String TAG ="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signIn_btn = (Button) findViewById(R.id.login_btn);
        login_et = (EditText) findViewById(R.id.login_email_et);
        password_et = (EditText) findViewById(R.id.login_password_et);

        signIn_btn.setOnClickListener(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://skif.me/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mAPIService = retrofit.create(APIService.class);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case    R.id.login_btn:
                String email = login_et.getText().toString().trim();
                String language = ConstantManager.POST_LANGUAGE;
                String pass = password_et.getText().toString().trim();

                String MD5_Hash_String = Md5Convert.calcMd5("fbf932e1-338c-4e28-aecd-d67295ce46a8",pass);

                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(language)) {
                    sendPost(email, language);
                    loginSubmit(email,MD5_Hash_String,language);
                }


                break;
        }
    }

    public void sendPost(String email, String language) {

        AuthPost post = new AuthPost();
        post.setEmail(email);
        post.setLanguage(language);

        mAPIService.savePost(post).enqueue(new Callback<AuthSaltResponse>() {
            @Override
            public void onResponse(Call<AuthSaltResponse> call, Response<AuthSaltResponse> response) {
                showToast("200 OK");
                String salt = response.body().getSalt();
                Log.v(TAG,salt );

            }

            @Override
            public void onFailure(Call<AuthSaltResponse> call, Throwable t) {
                showToast("error");
                Log.e(TAG,"cannot resolve");
            }
        });

    }

    public void loginSubmit (String email, String hash, String language){

        LoginParams loginBody = new LoginParams();
        loginBody.setEmail(email);
        loginBody.setHash(hash);
        loginBody.setLanguage(language);

        mAPIService.login(loginBody).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.v(TAG,response.toString());

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e(TAG,t.toString());
            }
        });
    }



    public void loginSuccesfull(AuthPost response) {


    }
}
