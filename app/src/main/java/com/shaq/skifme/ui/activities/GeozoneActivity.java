package com.shaq.skifme.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.LatLng;


import com.shaq.skifme.R;


public class GeozoneActivity extends BaseActivity implements OnMapReadyCallback, View.OnClickListener, GoogleMap.OnMapClickListener {

    private Toolbar mToolbar;

    private static final String TAG = "GeoActivity";


    FloatingActionMenu fab_geo_menu;
    FloatingActionButton fab_circle, fab_polygon;
    private GoogleMap mMap;
    private Circle mCircle;;
    Projection projection;
    public double latitude;
    public double longitude;
    static boolean Is_MAP_Moveable = false;

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMapClickListener(this);



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geozone);

        mToolbar = (Toolbar) findViewById(R.id.geozone_toolbar);

        ImageView add_geo_btn = (ImageView) findViewById(R.id.add_circle_iv);

        fab_geo_menu = (FloatingActionMenu) findViewById(R.id.fab_geo_menu);
        fab_circle = (FloatingActionButton) findViewById(R.id.fab_geo_circle);
        fab_polygon = (FloatingActionButton) findViewById(R.id.fab_geo_polygon);

        fab_circle.setOnClickListener(this);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.geozone_map);
        mapFragment.getMapAsync(this);



        add_geo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });




    }





    @Override
    public void onMapClick(LatLng latLng) {
//        mMap.clear();
//        LatLng point = new LatLng(latLng.latitude,latLng.longitude);
//        mMap.addMarker(new MarkerOptions().position(point).title("Center"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(point));;
//
//        Circle mCircle = mMap.addCircle(new CircleOptions()
//                .center(new LatLng(latLng.latitude,latLng.longitude))
//                .radius(10000)
//                .strokeColor(Color.RED));

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case    R.id.add_circle_iv:
                Log.d(TAG,"add clicked");
                                //drawCircle();
                break;
            case  R.id.fab_add_geozone:

                break;
            case R.id.fab_add_device:
                break;

        }
    }




}
