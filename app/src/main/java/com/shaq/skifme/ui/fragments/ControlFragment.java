package com.shaq.skifme.ui.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.shaq.skifme.R;
import com.shaq.skifme.data.managers.DataManager;
import com.shaq.skifme.data.res.DevicesRes;

import java.util.List;

public class ControlFragment extends Fragment {

    DataManager mDataManager;
    View rootView;
    RecyclerView recyclerView;
    private Button object_name_btn;
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

        rootView = getView();

        object_name_btn = (Button) rootView.findViewById(R.id.control_object_add);
        object_name_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDialogObject();
            }
        });

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.control_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
                //TODO: make dialog confirm for leave screen

            }
        });


    }

    public void createDialogObject () {
        final String[] listItems = getResources().getStringArray(R.array.objects_item);
        final TextView choosen_object_tv = rootView.findViewById(R.id.choosen_object_tv);

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
        mBuilder.setTitle("Объект");
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d(TAG,"poisition: " + i);
                choosen_object_tv.setText(listItems[i]);
                dialogInterface.dismiss();

            }
        });
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }




}
