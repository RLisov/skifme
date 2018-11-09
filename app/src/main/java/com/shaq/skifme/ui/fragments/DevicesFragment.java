package com.shaq.skifme.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shaq.skifme.R;
import com.shaq.skifme.data.adapters.DevicesAdapter;
import com.shaq.skifme.data.managers.DataManager;
import com.shaq.skifme.data.res.DevicesRes;
import com.shaq.skifme.ui.activities.TopLevelActivity;
import com.shaq.skifme.utils.MyDividerItemDecoration;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DevicesFragment extends Fragment {

    DataManager mDataManager;
    View rootView;
    RecyclerView recyclerView;
    private  String cookie;
    private String TAG = "DevicesFr";
    private List<DevicesRes>  dataDevices;
    private DevicesAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_devices, null);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set Title of fragment
        //((TopLevelActivity) getActivity()).getSupportActionBar().setTitle("Устройства");
        mDataManager = DataManager.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();

        cookie = mDataManager.getPreferencesManager().getCookie();

        inflateDeviceList();

        rootView = getView();
        recyclerView = (RecyclerView) rootView.findViewById(R.id.devices_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new MyDividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL, 16));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    private void inflateDeviceList() {

        mDataManager.getDevicesList(cookie).enqueue(new Callback<List<DevicesRes>>() {
            @Override
            public void onResponse(Call<List<DevicesRes>> call, Response<List<DevicesRes>> response) {
                Log.d(TAG,String.valueOf(response.code()));
                dataDevices = response.body();
                adapter = new DevicesAdapter(dataDevices);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<DevicesRes>> call, Throwable t) {
                Log.e(TAG,String.valueOf(t));
            }
        });
    }


}
