package com.shaq.skifme.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.shaq.skifme.R;
import com.shaq.skifme.data.adapters.GeoAdapter;
import com.shaq.skifme.data.eventbus_data.GeozonesEvent;
import com.shaq.skifme.data.managers.DataManager;
import com.shaq.skifme.data.res.GeozonesRes;
import com.shaq.skifme.network.APIService;
import com.shaq.skifme.ui.activities.TopLevelActivity;
import com.shaq.skifme.utils.ConstantManager;
import com.shaq.skifme.utils.GeoTouchListener;
import com.shaq.skifme.utils.MyDividerItemDecoration;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GeozonesFragment extends Fragment implements View.OnClickListener {


    private DataManager mDataManager;
    private APIService mAPIService;
    Button get_geo_btn;

    private RecyclerView recyclerView;
    private List<GeozonesRes> data;
    private List<List<Float>> geoCoord;
    private GeoAdapter adapter;
    private static final String TAG = "geozone_fragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_geo, null);


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantManager.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mAPIService = retrofit.create(APIService.class);

        mDataManager = DataManager.getInstance();

        setHasOptionsMenu(true);
        getActivity().setTitle("your name");

        //set Title of fragment
        ((TopLevelActivity) getActivity()).getSupportActionBar().setTitle("Геозоны");

        EventBus myEventBus = EventBus.getDefault();
    }

    @Override
    public void onStart() {
        super.onStart();

        recyclerView = (RecyclerView) getActivity().findViewById(R.id.geo_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new MyDividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL, 16));
        getGeoList();
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //setting bottom sheet
        LinearLayout llBottomSheet = (LinearLayout) getActivity().findViewById(R.id.bottom_sheet);
        final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(llBottomSheet);

        recyclerView.addOnItemTouchListener(new GeoTouchListener(getContext(), recyclerView, new GeoTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                //TODO: make startActivity with position
                EventBus.getDefault().postSticky(new GeozonesEvent(data.get(position)));
                startMapWithGeoId(data.get(position).name);

                //Toast.makeText(getContext(), data.get(position).name + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                //TODO: make delete position Recycler view
            }
        }));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.app_menu, menu);
    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case    R.id.get_geo:
//                getGeoList();
//                break;
//        }
    }

    private void getGeoList() {
        mAPIService.getGeozonesList(mDataManager.getPreferencesManager().getCookie()).enqueue(new Callback<List<GeozonesRes>>() {
            @Override
            public void onResponse(Call<List<GeozonesRes>> call, Response<List<GeozonesRes>> response) {

                data = response.body();

                adapter = new GeoAdapter(data);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<GeozonesRes>> call, Throwable t) {
                Log.e(TAG,t.toString());
            }
        });
    }

    private void startMapWithGeoId(String name ) {
        Intent intent = new Intent( getContext(), TopLevelActivity.class);
        intent.putExtra(ConstantManager.IS_OPEN_BOTTOM_SHEET,true);
        startActivity(intent);
        mDataManager.getPreferencesManager().setSelectedGeoName(name);
    }
}
