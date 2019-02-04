package com.shaq.skifme.ui.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shaq.skifme.R;
import com.shaq.skifme.data.managers.DataManager;
import com.shaq.skifme.data.res.DevicesRes;
import com.shaq.skifme.ui.activities.BarcodeActivity;

import java.util.List;

public class AddObjectFragment extends Fragment implements View.OnClickListener {

    DataManager mDataManager;
    View rootView;
    RecyclerView recyclerView;
    private  String cookie;
    private String TAG = "DevicesFr";
    private List<DevicesRes>  dataDevices;
    private ImageView qr_text_button,add_avatar_iv;
    AlertDialog.Builder mAlertBuilder;

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
        qr_text_button= rootView.findViewById(R.id.qr_iv);
        add_avatar_iv = rootView.findViewById(R.id.add_avatar_iv);

        add_avatar_iv.setOnClickListener(this);
        qr_text_button.setOnClickListener(this);

        settingAvatarDialog();

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
            case R.id.qr_iv:
                startQrActivity();
                break;
            case R.id.add_avatar_iv:
                mAlertBuilder.show();
                break;
            case R.id.menu_map:
                break;
        }
    }

    public void settingAvatarDialog() {
        Resources res = getResources();
        final String[] avatars = res.getStringArray(R.array.avatar_methods_array);

        mAlertBuilder = new AlertDialog.Builder(getContext());
        mAlertBuilder.setTitle(R.string.pick_avatar)
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setItems(R.array.avatar_methods_array, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Log.d(TAG,"Choose "+avatars[which]);
                    }
                });
        //TODO: Change UI Language
    }

    private void startQrActivity () {
        Intent intent = new Intent(getContext(), BarcodeActivity.class);
        startActivity(intent);
    }


}
