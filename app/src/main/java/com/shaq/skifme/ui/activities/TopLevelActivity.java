package com.shaq.skifme.ui.activities;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.shaq.skifme.R;
import com.shaq.skifme.data.Tracks.Send.Auto;

import com.shaq.skifme.data.Tracks.Response.TracksResponseModel;
import com.shaq.skifme.data.Tracks.Send.NorthEast;
import com.shaq.skifme.data.Tracks.Send.PostTracksBody;
import com.shaq.skifme.data.Tracks.Send.SouthWest;
import com.shaq.skifme.data.managers.DataManager;
import com.shaq.skifme.network.APIService;
import com.shaq.skifme.ui.fragments.DevicesFragment;
import com.shaq.skifme.ui.fragments.GeozonesFragment;
import com.shaq.skifme.ui.fragments.MapFragment;
import com.shaq.skifme.utils.ConstantManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TopLevelActivity extends BaseActivity implements OnMapReadyCallback, View.OnClickListener,ViewPager.OnPageChangeListener, BottomNavigationView.OnNavigationItemSelectedListener {



    private Toolbar mToolbar;
    private final String TAG = "TopLevelAct";
    private ArrayList<TracksResponseModel> dataTracks;
    private DataManager mDataManager;
    private APIService mAPIService;
    private CoordinatorLayout mCoordinatorLayout;
    private DrawerLayout mNavigationDrawer;
    ViewPager viewPager;
    FloatingActionMenu materialDesignFAM;
    FloatingActionButton fab_geozone, fab_device, fab_tracks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_level);

        BottomNavigationView navigation =  findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        mDataManager = DataManager.getInstance();

        ConstraintLayout llBottomSheet = (ConstraintLayout) findViewById(R.id.bottom_sheet);
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(llBottomSheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        mToolbar = (Toolbar) findViewById(R.id.top_toolbar);
        mNavigationDrawer = (DrawerLayout) findViewById(R.id.navigation_drawer);
        //setupToolbar();
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.constraint_layout_toplvl);
        //inflate activity on 1st start
        loadFragment(new MapFragment());

        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        fab_geozone = (FloatingActionButton) findViewById(R.id.fab_add_geozone);
        fab_device = (FloatingActionButton) findViewById(R.id.fab_add_device);
        fab_tracks = (FloatingActionButton) findViewById(R.id.fab_draw_tracks);

        fab_tracks.setOnClickListener(this);
        fab_geozone.setOnClickListener(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantManager.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mAPIService = retrofit.create(APIService.class);

        mDataManager.getPreferencesManager().setActionMode(false);
    }

    @Override
    public void onBackPressed() {
        //Disable back button
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,fragment)
                    .commit();
            return true;
        }

        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case    R.id.fab_draw_tracks:
                drawTracks();
                materialDesignFAM.toggle(true);
                break;
            case  R.id.fab_add_geozone:
                startGeozoneActivity();
                materialDesignFAM.toggle(true);
                break;
            case R.id.fab_add_device:
                break;

        }
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;

        switch (item.getItemId()) {

            case R.id.nav_menu_map:
                fragment = new MapFragment();

                break;
            case R.id.nav_menu_devices:
                fragment = new DevicesFragment();
                break;
            case R.id.nav_menu_geo:
                fragment = new GeozonesFragment();

                break;
        }

        return loadFragment(fragment);
    }



    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

        Log.d("page", "onPageSelected: " + i);

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }



//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.search_menu, menu);
//
//        MenuItem mSearch = menu.findItem(R.id.search);
//
//        return true ;
//    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.geozones:
                Toast.makeText(getApplicationContext(),"Item 1 Selected",Toast.LENGTH_LONG).show();
                return true;
            case android.R.id.home:
                mNavigationDrawer.openDrawer(GravityCompat.START);

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onStart() {
        super.onStart();

        getUserInfoMe();
        Log.d(TAG,mDataManager.getPreferencesManager().getSelectedGeoName());
    }

//    public void setupToolbar() {
//        setSupportActionBar(mToolbar);
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setHomeAsUpIndicator(R.drawable.ic_dehaze_white_24dp);
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }
//    }





    public void drawTracks () {

        Auto auto = new Auto();
        auto.setId("7659eaa7-8bb1-47c3-8435-577712371920");

        NorthEast nEast = new NorthEast();
        nEast.setLat(66.66f);
        nEast.setLon(77.77f);

        SouthWest sWest = new SouthWest();
        sWest.setLat(33.3f);
        sWest.setLon(55.5f);

        PostTracksBody postTracksBody = new PostTracksBody();
        postTracksBody.setAuto(auto);
        postTracksBody.setNorthEast(nEast);
        postTracksBody.setSouthWest(sWest);
        postTracksBody.setTimeFrom("2018-10-21 09:00:00");
        postTracksBody.setTimeTo("2018-10-22 17:00:00");
        postTracksBody.setType("mobile");
        postTracksBody.setZoom(13);

        Log.d(TAG,postTracksBody.toString());

        String cookie = mDataManager.getPreferencesManager().getCookie();


        mAPIService.getTracksPost(cookie,postTracksBody).enqueue(new Callback<List<TracksResponseModel>>() {
            @Override
            public void onResponse(Call<List<TracksResponseModel>> call, Response<List<TracksResponseModel>> response) {

                Log.d(TAG,String.valueOf(response.body().size()));
                PolylineOptions options = new PolylineOptions().width(5).color(Color.RED);

                for (int i = 0; i < response.body().size() -1; i++) {

                    Double xPoint = response.body().get(i).getY();
                    Double yPoint = response.body().get(i).getX();
                    options.add(new LatLng(xPoint, yPoint));
                    Log.d(TAG,response.body().get(i).getX().toString() +" "+response.body().get(i).getY().toString()+" date "+response.body().get(i).getDatepoint());
                }

                Polyline line = mMap.addPolyline(options);

                CameraPosition cameraPosition;
                cameraPosition = new CameraPosition.Builder().target(new LatLng(response.body().get(0).getY(), response.body().get(0).getX())).zoom(12).build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            }

            @Override
            public void onFailure(Call<List<TracksResponseModel>> call, Throwable t) {

                Log.d(TAG+"error",t.toString());
            }
        });

    }


}

