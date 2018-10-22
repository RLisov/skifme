package com.shaq.skifme.ui.activities;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.shaq.skifme.R;

import com.shaq.skifme.data.Tracks.Response.TracksResponseModel;
import com.shaq.skifme.data.Tracks.Send.Auto;
import com.shaq.skifme.data.Tracks.Send.NorthEast;
import com.shaq.skifme.data.Tracks.Send.PostTracksBody;
import com.shaq.skifme.data.Tracks.Send.SouthWest;
import com.shaq.skifme.network.APIService;
import com.shaq.skifme.utils.ConstantManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapsActivity extends BaseActivity implements OnMapReadyCallback {

    private static final String TAG = "MapsAct";
    private GoogleMap mMap;
    private APIService mAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantManager.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mAPIService = retrofit.create(APIService.class);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        drawTracks();
//        Polyline line = mMap.addPolyline(new PolylineOptions()
//                .add(new LatLng(51.5, -0.1))
//                .add(new LatLng(72.7671966666667, -54.1274716666667))
//                .add(new LatLng(72.767235,54.127585))
//                .width(5)
//                .color(Color.RED));
    }

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

        mAPIService.getTracksPost(postTracksBody).enqueue(new Callback<List<TracksResponseModel>>() {
            @Override
            public void onResponse(Call<List<TracksResponseModel>> call, Response<List<TracksResponseModel>> response) {

                Log.d(TAG,String.valueOf(response.body().size()));
                PolylineOptions options = new PolylineOptions().width(5).color(Color.RED);
//
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
