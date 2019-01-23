package com.shaq.skifme.ui.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.clans.fab.FloatingActionButton;
import com.shaq.skifme.R;
import com.shaq.skifme.data.managers.DataManager;
import com.shaq.skifme.data.adapters.ObjectsListAdapter;
import com.shaq.skifme.data.room.Objects;
import com.shaq.skifme.data.room.ObjectsListViewModel;
import com.shaq.skifme.ui.activities.TopLevelActivity;

import java.util.List;

public class DevicesFragment extends Fragment implements View.OnClickListener {

    DataManager mDataManager;
    View rootView;
    RecyclerView recyclerView;
    private  String cookie;
    private String TAG = "DevicesFr";
    private ObjectsListAdapter adapter;
    private DrawerLayout mDrawerLayout;
    FloatingActionButton fab_add_device;
    private ObjectsListViewModel mObjectsViewModel;
    private ImageView add_button;

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

        rootView = getView();
        recyclerView = (RecyclerView) rootView.findViewById(R.id.devices_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);

        //!!!!
        final ObjectsListAdapter adapter = new ObjectsListAdapter(getContext());

        recyclerView.setAdapter(adapter);
        //recyclerView.addItemDecoration(new MyDividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL, 16));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mDrawerLayout = getActivity().findViewById(R.id.navigation_drawer);

//        fab_add_device = (FloatingActionButton) rootView.findViewById(R.id.fab_add_object);
//        fab_add_device.setOnClickListener(this);


        Toolbar mToolbar = (Toolbar) rootView.findViewById(R.id.device_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
        ActionBar actionbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_dehaze_white_24dp);
        ((TopLevelActivity) getActivity()).getSupportActionBar().setTitle("SKIF.PERSONAL");

        setHasOptionsMenu(true);
        mObjectsViewModel = ViewModelProviders.of(this).get(ObjectsListViewModel.class);
        mObjectsViewModel.getAllGeo().observe(this, new Observer<List<Objects>>() {
            @Override
            public void onChanged(@Nullable List<Objects> geozones) {
                adapter.setObjects(geozones);
                Log.d(TAG,"data changed on start");
            }
        });
        mObjectsViewModel.insertObjects();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.add_object_cross:
//                ((TopLevelActivity) getActivity()).loadFragment(new AddObjectFragment());
//                break;

            case R.id.menu_map:
                break;

        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.device_toolbar_menu, menu);
    }

//    private void inflateDeviceList() {
//
//        mDataManager.getDevicesList(cookie).enqueue(new Callback<List<DevicesRes>>() {
//            @Override
//            public void onResponse(Call<List<DevicesRes>> call, Response<List<DevicesRes>> response) {
//                Log.d(TAG,String.valueOf(response.code()));
//                dataDevices = response.body();
//                adapter = new DevicesAdapter(dataDevices);
//                recyclerView.setAdapter(adapter);
//                adapter.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onFailure(Call<List<DevicesRes>> call, Throwable t) {
//                Log.e(TAG,String.valueOf(t));
//            }
//        });
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_search:
                Log.d(TAG,"search click");
                return true;

            case R.id.menu_map:

                return true;
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }



}
