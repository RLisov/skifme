package com.shaq.skifme.ui.activities;

import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.shaq.skifme.R;
import com.shaq.skifme.data.Tracks.Send.Auto;
import com.shaq.skifme.data.LoginBody;

import com.shaq.skifme.data.Tracks.Response.TracksResponseModel;
import com.shaq.skifme.data.Tracks.Send.PostTracksBody;
import com.shaq.skifme.network.APIService;
import com.shaq.skifme.utils.ConstantManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TopLevelActivity extends BaseActivity implements View.OnClickListener {


    private Button map_btn, get_tracks;
    private CoordinatorLayout mCoordinatorLayout;
    private Toolbar mToolbar;
    private DrawerLayout mNavigationDrawer;
    private final String TAG = "TopLevelAct";
    private ArrayList<TracksResponseModel> dataTracks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_level);
        mToolbar = (Toolbar) findViewById(R.id.top_level_toolbar);
        mNavigationDrawer = (DrawerLayout) findViewById(R.id.navigation_drawer);
        map_btn = (Button) findViewById(R.id.button_map);
        get_tracks = (Button) findViewById(R.id.get_tracks);
        get_tracks.setOnClickListener(this);
        map_btn.setOnClickListener(this);

        setupToolbar();

        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.main_coordinator_container);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case    R.id.button_map:
                startMapsActivity();
                break;
            case R.id.get_tracks:

                //drawTracks();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.new_device:
                Toast.makeText(getApplicationContext(),"Item 1 Selected",Toast.LENGTH_LONG).show();
                return true;
            case android.R.id.home:
                mNavigationDrawer.openDrawer(GravityCompat.START);

            default:
                return super.onOptionsItemSelected(item);
        }
    }



    private void showSnackbar(String message) {
        Snackbar.make(mCoordinatorLayout, message, Snackbar.LENGTH_LONG).show();
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_dehaze_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }





}

