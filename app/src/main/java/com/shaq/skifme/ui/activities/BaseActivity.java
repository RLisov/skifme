package com.shaq.skifme.ui.activities;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.shaq.skifme.R;
import com.shaq.skifme.utils.ConstantManager;


public class BaseActivity extends AppCompatActivity {

    private static final String TAG = ConstantManager.TAG_PREFIX+"BaseActivity";
    protected ProgressDialog mProgressDialog;

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



}
