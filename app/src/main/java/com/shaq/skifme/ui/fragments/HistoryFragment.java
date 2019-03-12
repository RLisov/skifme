package com.shaq.skifme.ui.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.shaq.skifme.R;
import com.shaq.skifme.data.adapters.ControlListAdapter;
import com.shaq.skifme.data.managers.DataManager;
import com.shaq.skifme.data.res.DevicesRes;
import com.shaq.skifme.ui.activities.BarcodeActivity;

import java.util.List;

public class HistoryFragment extends Fragment implements View.OnClickListener {

    DataManager mDataManager;
    View rootView;
    RecyclerView recyclerView;
    private  String cookie;
    private List<DevicesRes>  dataDevices;
    private ImageView qr_text_button,add_avatar_iv;
    AlertDialog.Builder mAlertBuilder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, null);

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
        cookie = mDataManager.getPreferencesManager().getCookie();

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.history_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
                //TODO: make dialog confirm for leave screen

            }
        });
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ActionBar actionbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        setHasOptionsMenu(true);

//        recyclerView = (RecyclerView) rootView.findViewById(R.id.object_list_rv);
//        recyclerView.setHasFixedSize(true);
//        final ControlListAdapter adapter = new ControlListAdapter(getContext());
//        recyclerView.setAdapter(adapter);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),1,false);
//        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.qr_iv:
                break;
            case R.id.add_avatar_iv:
                break;
            case R.id.menu_map:
                break;
        }
    }



}
