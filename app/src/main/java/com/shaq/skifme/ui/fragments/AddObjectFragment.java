package com.shaq.skifme.ui.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shaq.skifme.R;
import com.shaq.skifme.data.adapters.DevicesAdapter;
import com.shaq.skifme.data.managers.DataManager;
import com.shaq.skifme.data.res.DevicesRes;
import com.shaq.skifme.ui.activities.BarcodeActivity;
import com.shaq.skifme.ui.activities.TopLevelActivity;

import java.util.List;

public class AddObjectFragment extends Fragment implements View.OnClickListener {

    DataManager mDataManager;
    View rootView;
    RecyclerView recyclerView;
    private  String cookie;
    private String TAG = "DevicesFr";
    private List<DevicesRes>  dataDevices;
    private DevicesAdapter adapter;
    private TextView qr_text_button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_object, null);

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

        rootView = getView();
        qr_text_button= rootView.findViewById(R.id.qr_text);
        qr_text_button.setOnClickListener(this);

        cookie = mDataManager.getPreferencesManager().getCookie();

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.add_object_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
                //TODO: make dialog confirm for leave screen

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.qr_text:
                startQrActivity();
                break;

            case R.id.menu_map:
                break;

        }
    }

    private void startQrActivity () {
        Intent intent = new Intent(getContext(), BarcodeActivity.class);
        startActivity(intent);
    }


}
