package com.shaq.skifme.data.room;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class ControlListViewModel extends AndroidViewModel {

    private AppRepository mRepository;

    private LiveData<List<Objects>> mAllGeo;

    public ControlListViewModel(Application application) {
        super(application);
        mRepository = new AppRepository(application);
        mAllGeo = mRepository.getAllObjects();
    }

    public LiveData<List<Objects>> getAllGeo() { return mAllGeo; }

    public void insert() {
        //mRepository.insert();
    }



}
