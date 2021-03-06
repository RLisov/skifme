package com.shaq.skifme.ui.fragments;


import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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

import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.shaq.skifme.R;
import com.shaq.skifme.data.adapters.ControlListAdapter;
import com.shaq.skifme.data.managers.DataManager;
import com.shaq.skifme.data.room.ControlListViewModel;
import com.shaq.skifme.data.room.Controls;
import com.shaq.skifme.network.APIService;
import com.shaq.skifme.utils.ConstantManager;
import com.shaq.skifme.utils.MyDividerItemDecoration;


import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;




public class MapFragment extends Fragment implements OnMapReadyCallback, View.OnClickListener, GoogleMap.OnMarkerClickListener {

    SupportMapFragment mMapFragment;
    FloatingActionButton fab_location,fab_history,fab_call;

    public GoogleMap mMap;
    private CameraPosition mCameraPosition;

    private FusedLocationProviderClient mFusedLocationClient;

    private final LatLng mDefaultLocation = new LatLng(-33.8523341, 151.2106085);
    private static final int DEFAULT_ZOOM = 15;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean mLocationPermissionGranted;

    private Location mLastKnownLocation;
    private LocationManager lm;
    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";
    private DataManager mDataManager;
    private APIService mAPIService;
    public static int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION =1;
    private static final String TAG = "MapFragment";
    private BottomSheetBehavior bottomSheetBehavior;
    private CoordinatorLayout mCoordinatorLayout;
    private TextView bs_title, bs_subtitle;
    private List<String> lastSearches;
    private MaterialSearchBar searchBar;
    View rootView;
    RecyclerView recyclerView;
    private ControlListViewModel mControlViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set Title of fragment
        //((TopLevelActivity) getActivity()).getSupportActionBar().setTitle("Карта");
        // Retrieve location and camera position from saved instance state.
        if (savedInstanceState != null) {
            mLastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION);
            mCameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
        }

        // Construct a FusedLocationProviderClient.
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
        return inflater.inflate(R.layout.fragment_map, null);


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

        searchBar = (MaterialSearchBar) getActivity().findViewById(R.id.searchBar);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.map_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);

        rootView = getView();
        recyclerView = (RecyclerView) rootView.findViewById(R.id.object_list_rv);
        recyclerView.setHasFixedSize(true);
        final ControlListAdapter adapter = new ControlListAdapter(getContext());
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),1,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new MyDividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL, 24));


        mControlViewModel = ViewModelProviders.of(this).get(ControlListViewModel.class);
        mControlViewModel.getAllControls().observe(this, new Observer<List<Controls>>() {
            @Override
            public void onChanged(@Nullable List<Controls> controls) {
                adapter.setControls(controls);
                Log.d(TAG,"data changed on start CONTROL LIST");
            }
        });
        mControlViewModel.insertControls();

        ConstraintLayout llBottomSheet = (ConstraintLayout) getActivity().findViewById(R.id.bottom_sheet);
        mCoordinatorLayout = getActivity().findViewById(R.id.coordinatorLayout);
        bottomSheetBehavior = BottomSheetBehavior.from(llBottomSheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        bottomSheetBehavior.setPeekHeight(380);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (BottomSheetBehavior.STATE_DRAGGING == newState) {
                    fab_location.animate().scaleX(0).scaleY(0).setDuration(50).start();
                } else if (BottomSheetBehavior.STATE_COLLAPSED  == newState || BottomSheetBehavior.STATE_HIDDEN == newState) {
                    fab_location.animate().scaleX(1).scaleY(1).setDuration(50).start();
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });

        checkLocationPermission(getActivity());
        getDeviceLocation();


        fab_call = getActivity().findViewById(R.id.fab_call);
        fab_history = getActivity().findViewById(R.id.fab_history);
        fab_location = getActivity().findViewById(R.id.fab_location);
        fab_location.setOnClickListener(this);
        fab_history.setOnClickListener(this);
        fab_call.setOnClickListener(this);
        bs_title = getActivity().findViewById(R.id.bs_title);
        bs_subtitle = getActivity().findViewById(R.id.bs_subhead_tv);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();

            }
        });


    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Log.d(TAG,String.valueOf(marker.getTag()));
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        return false;
    }

    //    @Override
//    public void onDetach() {
//        GeozonesEvent event = EventBus.getDefault().getStickyEvent(GeozonesEvent.class);
//        if (event != null) {
//            EventBus.getDefault().removeStickyEvent(event);
//        }
//
//        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
//        super.onDetach();
//    }


    //Event bus subscribe
//    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
//    public void onMessageEvent(GeozonesEvent event) {
//
//        Toast.makeText(getActivity(), event.getGeodata().name, Toast.LENGTH_SHORT).show();
//        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
//        bs_title.setText(String.valueOf(event.getGeodata().name));
//        bs_subtitle.setText(String.valueOf(event.getGeodata().type.getValueRu()));
//
//    }

//    public void drawGeo() {
//
//            PolygonOptions options = new PolygonOptions().strokeColor(Color.DKGRAY).fillColor(Color.LTGRAY);
//
//            options.add(new LatLng(55.70731817593897, 37.5894320011139));
//            options.add(new LatLng(55.708396445635806, 37.5913417339325));
//            options.add(new LatLng(55.70676691415676, 37.594774961471565));
//            options.add(new LatLng(55.70560984479542, 37.593755722045906));
//
//            Polygon polygon = mMap.addPolygon(options);
//
//            CameraPosition cameraPosition;
//            cameraPosition = new CameraPosition.Builder().target(new LatLng(55.70731817593897, 37.5894320011139)).zoom(12).build();
//            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
//
//    }


    public static boolean checkLocationPermission(Activity activity){
        if(ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(activity, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            return false;
        }
        return true;
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMarkerClickListener(this);
        mMap.setPadding(20, 150, 20, 20);
        mMap.getUiSettings().setMapToolbarEnabled(false);
        // Prompt the user for permission.
        getLocationPermission();

        // Turn on the My Location layer and the related control on the map.
        updateLocationUI();

        // Get the current location of the device and set the position of the map.
        getDeviceLocation();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_location:
                getDeviceLocation();
                getLocationPermission();
                //drawGeo();
                break;
            case R.id.fab_add_geozone:

                break;
            case R.id.fab_add_device:
                break;
        }
    }


    private void getDeviceLocation() {

        try {
            if (mLocationPermissionGranted) {
                Task<Location> locationResult = mFusedLocationClient.getLastLocation();
                locationResult.addOnCompleteListener(getActivity(), new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            // Set the map's camera position to the current location of the device.
                            mLastKnownLocation = task.getResult();
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                    new LatLng(mLastKnownLocation.getLatitude(),
                                            mLastKnownLocation.getLongitude()), DEFAULT_ZOOM));
                            MarkerOptions mp = new MarkerOptions();
                            mp.position(new LatLng(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude()));
                            mp.icon(BitmapDescriptorFactory.fromResource(R.mipmap.avatar_cv));
                            Log.d(TAG,(String.valueOf(mLastKnownLocation.getLatitude() + " "+ String.valueOf(mLastKnownLocation.getLongitude()))));

                            mMap.clear();
                            mMap.addMarker(mp);
                            fillMapWithObjects();

                        } else {
                            Log.d(TAG, "Current location is null. Using defaults.");
                            Log.e(TAG, "Exception: %s", task.getException());
                            mMap.moveCamera(CameraUpdateFactory
                                    .newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));
                            //mMap.getUiSettings().setMyLocationButtonEnabled(false);
                        }
                    }
                });
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */

        if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
        updateLocationUI();
    }

    private void updateLocationUI() {
        if (mMap == null) {
            return;
        }
        try {
            if (mLocationPermissionGranted) {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                mLastKnownLocation = null;
                getLocationPermission();
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    private void fillMapWithObjects() {
        LatLng valya = new LatLng(51.13823845680599, 71.42486572265626);
        mMap.addMarker(new MarkerOptions().position(valya).title("Валя")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.avatar_cv)));

        LatLng anton = new LatLng(54.13823845680599, 71.42486572265626);
        mMap.addMarker(new MarkerOptions().position(anton).title("Антон")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.avatar_a)));

        LatLng misha = new LatLng(32.1823845680599, 55.42486572265626);
        mMap.addMarker(new MarkerOptions().position(misha).title("Миша")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.avatar_a)));

        LatLng vasya = new LatLng(52.13823845680599, 71.42486572265626);
        mMap.addMarker(new MarkerOptions().position(vasya).title("Вася")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.avatar_cv)));

        LatLngBounds ADELAIDE = new LatLngBounds(
                new LatLng(51.13823845680599, 71.42486572265626), new LatLng(54.13823845680599, 71.42486572265626));

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(new LatLng(51.13823845680599, 71.42486572265626));
        builder.include(new LatLng(54.13823845680599, 71.42486572265626));
        builder.include(new LatLng(32.1823845680599, 55.42486572265626));
        builder.include(new LatLng(52.13823845680599, 71.42486582265626));
        LatLngBounds tmpBounds = builder.build();

        //mMap.setLatLngBoundsForCameraTarget(tmpBounds);

        //mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(tmpBounds, 15));

 //Zoom in, animating the camera.
        //mMap.animateCamera(CameraUpdateFactory.zoomIn());

// Zoom out to zoom level 10, animating with a duration of 2 seconds.
       // mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(tmpBounds, 0));

//        CameraPosition cameraPosition = new CameraPosition.Builder()
//                .target(vasya)      // Sets the center of the map to Mountain View
//                .zoom(10)// Sets the zoom
//                // Sets the orientation of the camera to east
//                .tilt(30)                   // Sets the tilt of the camera to 30 degrees
//                .build();                   // Creates a CameraPosition from the builder
//        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(tmpBounds));



    }

//    private void drawGeozone(GeozonesEvent geozoneBody) {
//
//        PolylineOptions options = new PolylineOptions().width(5).color(Color.RED);
//        Log.d(TAG, String.valueOf(geozoneBody.getGeodata().geometry.get(0)) );
//        for (int i = 0; i < geozoneBody.getGeodata().geometry.size() -1; i++) {
//
//                Double xPoint = geozoneBody.getGeodata().geometry.get(i).get(0);
//                Double yPoint = geozoneBody.getGeodata().geometry.get(i).get(1);
//                options.add(new LatLng(xPoint, yPoint));
//
//        }
//
//        Polyline line = mMap.addPolyline(options);
//        CameraPosition cameraPosition;
//        cameraPosition = new CameraPosition.Builder().target(new LatLng(geozoneBody.getGeodata().geometry.get(0).get(0), geozoneBody.getGeodata().geometry.get(0).get(1))).zoom(12).build();
//        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
//    }


}
