package com.shaq.skifme.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shaq.skifme.R;
import com.shaq.skifme.data.managers.DataManager;
import com.shaq.skifme.data.res.DevicesRes;

import java.util.List;

public class ObjectFragment extends Fragment {

    DataManager mDataManager;
    View rootView;
    RecyclerView recyclerView;
    private  String cookie;
    private String TAG = "DevicesFr";
    private List<DevicesRes>  dataDevices;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_object, null);

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

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.object_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
                //TODO: make dialog confirm for leave screen

            }
        });

    }




}
