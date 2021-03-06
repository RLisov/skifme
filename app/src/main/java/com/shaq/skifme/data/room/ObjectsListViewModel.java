package com.shaq.skifme.data.room;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class ObjectsListViewModel extends AndroidViewModel {

    private AppRepository mAppRepository;

    private LiveData<List<Objects>> mAllObjects;

    public ObjectsListViewModel(Application application) {
        super(application);
        mAppRepository = new AppRepository(application);
    }

    public LiveData<List<Objects>> getAllGeo() {
        if (mAllObjects == null) {
            mAllObjects = mAppRepository.getAllObjects();
        }
        return mAllObjects;
    }

    public void insertObjects() {
        mAppRepository.insertObjects();
    }

    public void setChoosenObject() {
        //mAppRepository.setObject();
    }



}
