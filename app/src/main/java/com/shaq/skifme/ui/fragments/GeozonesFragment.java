package com.shaq.skifme.ui.fragments;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

import com.shaq.skifme.R;
import com.shaq.skifme.data.adapters.GeoAdapter;
import com.shaq.skifme.data.eventbus_data.GeozonesEvent;
import com.shaq.skifme.data.managers.DataManager;
import com.shaq.skifme.data.res.GeozonesRes;
import com.shaq.skifme.data.room.AppDatabase;
import com.shaq.skifme.data.room.Geozones;
import com.shaq.skifme.network.APIService;
import com.shaq.skifme.ui.activities.TopLevelActivity;
import com.shaq.skifme.utils.ConstantManager;
import com.shaq.skifme.utils.GeoDao;
import com.shaq.skifme.utils.GeoTouchListener;
import com.shaq.skifme.utils.MyDividerItemDecoration;
import com.shaq.skifme.utils.SkifApplication;

import org.greenrobot.eventbus.EventBus;

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
    CheckBox geo_checkbox;
    private RecyclerView recyclerView;
    private List<GeozonesRes> data;
    private List<List<Float>> geoCoord;
    private GeoAdapter adapter;
    private static final String TAG = "geozone_fragment";
    boolean is_in_action_mode = false;
    private AppDatabase db;

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

        recyclerView = (RecyclerView) getActivity().findViewById(R.id.geo_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new MyDividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL, 16));
        getGeoList();
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        geo_checkbox = (CheckBox) getActivity().findViewById(R.id.geo_check_list_item);
        //db = SkifApplication.getInstance().getDatabase();



        recyclerView.addOnItemTouchListener(new GeoTouchListener(getContext(), recyclerView, new GeoTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                if (!mDataManager.getPreferencesManager().getActionMode()) {
                    EventBus.getDefault().postSticky(new GeozonesEvent(data.get(position)));
                    startMapWithGeoId(data.get(position).name);
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
                adapter = new GeoAdapter(data, getContext());
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
        startActivity(intent);
        mDataManager.getPreferencesManager().setSelectedGeoName(name);
    }
}
