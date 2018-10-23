package com.shaq.skifme.ui.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Point;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSeekBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;
import android.location.Location;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.LatLng;


import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.shaq.skifme.R;



public class GeozoneActivity extends BaseActivity implements OnMapReadyCallback, View.OnClickListener, GoogleMap.OnMapClickListener, SeekBar.OnSeekBarChangeListener {

    private Toolbar mToolbar;

    private static final String TAG = "GeoActivity";


    FloatingActionMenu fab_geo_menu;
    FloatingActionButton fab_circle, fab_polygon;
    private GoogleMap mMap;
    private Circle mCircle;

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Circle mCircle = mMap.addCircle(new CircleOptions()
                .center(new LatLng(0.0f,0.0f))
                .radius(10000)
                .strokeColor(Color.RED));
        mMap.setOnMapClickListener(this);



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geozone);

        mToolbar = (Toolbar) findViewById(R.id.geozone_toolbar);


        fab_geo_menu = (FloatingActionMenu) findViewById(R.id.fab_geo_menu);
        fab_circle = (FloatingActionButton) findViewById(R.id.fab_geo_circle);
        fab_polygon = (FloatingActionButton) findViewById(R.id.fab_geo_polygon);

        fab_circle.setOnClickListener(this);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar_radius);
        seekBar.setOnSeekBarChangeListener(this);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.geozone_map);
        mapFragment.getMapAsync(this);



    }



    @Override
    public void onMapClick(LatLng latLng) {
        mMap.clear();
        LatLng point = new LatLng(latLng.latitude,latLng.longitude);
        mMap.addMarker(new MarkerOptions().position(point).title("Center"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(point));;

        Circle mCircle = mMap.addCircle(new CircleOptions()
                .center(new LatLng(latLng.latitude,latLng.longitude))
                .radius(10000)
                .strokeColor(Color.RED));

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        Log.d(TAG,String.valueOf(seekBar.getProgress()));

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
       
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case    R.id.fab_geo_circle:
                //drawCircle();
                break;
            case  R.id.fab_add_geozone:

                break;
            case R.id.fab_add_device:
                break;

        }
    }




}
