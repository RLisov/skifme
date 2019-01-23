package com.shaq.skifme.ui.fragments;


import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.shaq.skifme.R;
import com.shaq.skifme.data.managers.DataManager;
import com.shaq.skifme.data.req.PostTracksBody;
import com.shaq.skifme.data.res.TracksRes;
import com.shaq.skifme.data.res.TracksResponseModel;
import com.shaq.skifme.network.APIService;
import com.shaq.skifme.utils.ConstantManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TracksFragment extends Fragment implements OnMapReadyCallback, View.OnClickListener {

    SupportMapFragment mMapFragment;
    public GoogleMap mMap;
    private CameraPosition mCameraPosition;

    private FusedLocationProviderClient mFusedLocationClient;


    private Location mLastKnownLocation;
    private LocationManager lm;
    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";
    private DataManager mDataManager;
    private APIService mAPIService;
    private static final String TAG = "MapFragment";



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (savedInstanceState != null) {
            mLastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION);
            mCameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
        }


        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantManager.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mAPIService = retrofit.create(APIService.class);


    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if (mMap != null) {
            outState.putParcelable(KEY_CAMERA_POSITION, mMap.getCameraPosition());
            outState.putParcelable(KEY_LOCATION, mLastKnownLocation);
            super.onSaveInstanceState(outState);
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tracks, null);


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


    }

    @Override
    public void onStart() {
        super.onStart();

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.tracks_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

    }






    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //drawTracks();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_location:

                break;
            case R.id.fab_add_geozone:

                break;
            case R.id.fab_add_device:
                break;
        }
    }

//    public void drawTracks () {
//
//
//
//
//        mAPIService.getTracks().enqueue(new Callback<List<TracksRes>>() {
//            @Override
//            public void onResponse(Call<List<TracksRes>> call, Response<List<TracksRes>> response) {
//
//                Log.d(TAG,String.valueOf(response.body().size()));
//                PolylineOptions options = new PolylineOptions().width(5).color(Color.RED);
//
//                for (int i = 0; i < response.body().size() -1; i++) {
//
//                    Double xPoint = Double.parseDouble(response.body().get(i).getY());
//                    Double yPoint = Double.parseDouble(response.body().get(i).getX());
//                    options.add(new LatLng(xPoint, yPoint));
//                    Log.d(TAG,response.body().get(i).getX().toString() +" "+response.body().get(i).getY().toString()+" date "+response.body().get(i).getDatepoint());
//                }
//
//                Polyline line = mMap.addPolyline(options);
//                Double xStart = Double.parseDouble(response.body().get(0).getY());
//                Double yStart = Double.parseDouble(response.body().get(0).getX());
//                CameraPosition cameraPosition;
//                cameraPosition = new CameraPosition.Builder().target(new LatLng(xStart,yStart).
//                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
//
//            }
//
//            @Override
//            public void onFailure(Call<List<TracksRes>> call, Throwable t) {
//
//                Log.d(TAG+"error",t.toString());
//            }
//        });
//
//    }







}
