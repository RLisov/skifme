package com.shaq.skifme.data.room;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class GeozonesViewModel extends AndroidViewModel {

    private GeozoneRepository mGeozoneRepository;

    private LiveData<List<Geozones>> mAllGeo;

    public GeozonesViewModel (Application application) {
        super(application);
        mGeozoneRepository = new GeozoneRepository(application);
        mAllGeo = mGeozoneRepository.getAllGeozones();
    }

    public LiveData<List<Geozones>> getAllGeo() { return mAllGeo; }

    public void insert() {
        mGeozoneRepository.insert();
    }



}
