package com.shaq.skifme.ui.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.shaq.skifme.R;
import com.shaq.skifme.data.adapters.ControlListAdapter;
import com.shaq.skifme.data.managers.DataManager;
import com.shaq.skifme.data.res.DevicesRes;
import com.shaq.skifme.data.room.ControlListViewModel;
import com.shaq.skifme.data.room.Controls;
import com.shaq.skifme.data.room.Objects;
import com.shaq.skifme.utils.MyDividerItemDecoration;

import java.util.List;
import java.util.ResourceBundle;

public class ObjectFragment extends Fragment {

    DataManager mDataManager;
    View rootView;
    RecyclerView recyclerView;
    private  String cookie;
    private String TAG = "Detailed Object";
    private List<DevicesRes>  dataDevices;
    private ControlListViewModel mControlViewModel;

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

            }
        });
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ActionBar actionbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(false);
        setHasOptionsMenu(true);

        rootView = getView();
        recyclerView = (RecyclerView) rootView.findViewById(R.id.object_list_rv);
        recyclerView.setHasFixedSize(true);
        final ControlListAdapter adapter = new ControlListAdapter(getContext());

        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),1,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new MyDividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL, 24));


        mControlViewModel = ViewModelProviders.of(this).get(ControlListViewModel.class);
        mControlViewModel.getAllControls().observe(this, new Observer<List<Controls>>() {
            @Override
            public void onChanged(@Nullable List<Controls> controls) {
                adapter.setControls(controls);
                Log.d(TAG,"data changed on start CONTROL LIST");
            }
        });
        mControlViewModel.insertControls();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.object_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tracks:
                Log.d(TAG,"tracks click");
                return true;


            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }




}
