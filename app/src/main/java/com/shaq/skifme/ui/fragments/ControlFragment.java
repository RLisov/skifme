package com.shaq.skifme.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shaq.skifme.R;
import com.shaq.skifme.data.managers.DataManager;
import com.shaq.skifme.data.res.DevicesRes;

import java.util.List;

public class ControlFragment extends Fragment {

    DataManager mDataManager;
    View rootView;
    RecyclerView recyclerView;
    private  String cookie;
    private String TAG = "DevicesFr";
    private List<DevicesRes>  dataDevices;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_control, null);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set Title of fragment
        mDataManager = DataManager.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();

        cookie = mDataManager.getPreferencesManager().getCookie();

    }




}
