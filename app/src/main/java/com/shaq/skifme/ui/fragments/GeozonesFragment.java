package com.shaq.skifme.ui.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
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
import android.widget.CheckBox;

import com.mancj.materialsearchbar.MaterialSearchBar;
import com.shaq.skifme.R;
import com.shaq.skifme.data.managers.DataManager;
import com.shaq.skifme.data.res.GeozonesRes;
import com.shaq.skifme.data.room.AppDatabase;
import com.shaq.skifme.data.room.GeoListAdapter;
import com.shaq.skifme.data.room.Geozones;
import com.shaq.skifme.data.room.GeozonesViewModel;
import com.shaq.skifme.network.APIService;
import com.shaq.skifme.ui.activities.TopLevelActivity;
import com.shaq.skifme.utils.ConstantManager;
import com.shaq.skifme.utils.GeoTouchListener;
import com.shaq.skifme.utils.MyDividerItemDecoration;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GeozonesFragment extends Fragment implements View.OnClickListener {


    private DataManager mDataManager;
    private APIService mAPIService;
    Button get_geo_btn;
    CheckBox geo_checkbox;
    private RecyclerView recyclerView;
    private List<GeozonesRes> data;
    private List<List<Float>> geoCoord;
    private GeoListAdapter adapter;
    private static final String TAG = "geozone_fragment";
    boolean is_in_action_mode = false;
    private AppDatabase db;
    private GeozonesViewModel mGeozonesViewModel;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private BottomSheetBehavior mBottomSheetBehavior;
    private MaterialSearchBar mSearchBar;

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

        //set Title of fragment
        //((TopLevelActivity) getActivity()).getSupportActionBar().setTitle("Геозоны");

        EventBus myEventBus = EventBus.getDefault();
    }

    @Override
    public void onStart() {
        super.onStart();

        mSearchBar = (MaterialSearchBar) getActivity().findViewById(R.id.searchBar);


        recyclerView = (RecyclerView) getActivity().findViewById(R.id.geo_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        final GeoListAdapter adapter = new GeoListAdapter(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new MyDividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL, 16));
        //getGeoList();
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        geo_checkbox = (CheckBox) getActivity().findViewById(R.id.geo_check_list_item);

        //Bottom sheet control

//        ConstraintLayout llBottomSheet = (ConstraintLayout) getActivity().findViewById(R.id.bottom_sheet_geo);
//        mBottomSheetBehavior = BottomSheetBehavior.from(llBottomSheet);
//
//        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
//            @Override
//            public void onStateChanged(@NonNull View bottomSheet, int newState) {
//                if (BottomSheetBehavior.STATE_EXPANDED == newState) {
//                    mSearchBar.animate().scaleX(0).scaleY(0).setDuration(150).start();
//                } else if (BottomSheetBehavior.STATE_COLLAPSED  == newState || BottomSheetBehavior.STATE_HIDDEN == newState) {
//                    mSearchBar.animate().scaleX(1).scaleY(1).setDuration(150).start();
//                }
//            }
//
//            @Override
//            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
//            }
//        });



        mGeozonesViewModel = ViewModelProviders.of(this).get(GeozonesViewModel.class);
        mGeozonesViewModel.getAllGeo().observe(this, new Observer<List<Geozones>>() {
            @Override
            public void onChanged(@Nullable List<Geozones> geozones) {
                adapter.setGeozones(geozones);
                Log.d(TAG,"data changed");
                }
        });
        mGeozonesViewModel.insert(); //inflate db from net

        recyclerView.addOnItemTouchListener(new GeoTouchListener(getContext(), recyclerView, new GeoTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                if (!mDataManager.getPreferencesManager().getActionMode()) {
                    //EventBus.getDefault().postSticky(new GeozonesEvent(data.get(position)));
                    //startMapWithGeoId(data.get(position).name);
                }
            }

            @Override
            public void onLongClick(View view, int position) {
                if (mDataManager.getPreferencesManager().getActionMode()) {

                } else {
                mDataManager.getPreferencesManager().setActionMode(true);
                adapter.notifyDataSetChanged();
                Log.d(TAG,"long tap");
                }
            }
        }));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        //inflater.inflate(R.menu.device_toolbar_menu, menu);
    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case    R.id.get_geo:
//                getGeoList();
//                break;
//        }
    }


}
