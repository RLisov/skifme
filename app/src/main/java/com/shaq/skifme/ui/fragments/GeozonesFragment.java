package com.shaq.skifme.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.shaq.skifme.R;
import com.shaq.skifme.data.managers.DataManager;
import com.shaq.skifme.data.res.GeozonesRes;
import com.shaq.skifme.network.APIService;
import com.shaq.skifme.utils.ConstantManager;

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
    private static final String TAG = "geozone_fragment";

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

    }

    @Override
    public void onStart() {
        super.onStart();
        getGeoList();
//        get_geo_btn = (Button) getActivity().findViewById(R.id.get_geo);
//        get_geo_btn.setOnClickListener(this);
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
                Log.d(TAG,response.body().get(0).name + " "+ response.body().get(1).name + " "+ response.body().get(2).name);
            }

            @Override
            public void onFailure(Call<List<GeozonesRes>> call, Throwable t) {
                Log.e(TAG,t.toString());
            }
        });
    }
}
