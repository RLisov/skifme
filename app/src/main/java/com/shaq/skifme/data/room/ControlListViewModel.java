package com.shaq.skifme.data.room;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class ControlListViewModel extends AndroidViewModel {

    private AppRepository mRepository;

    private LiveData<List<Controls>> mAllControls;

    public ControlListViewModel(Application application) {
        super(application);
        mRepository = new AppRepository(application);
        mAllControls = mRepository.getAllControls();
    }

    public LiveData<List<Controls>> getAllControls() { return mAllControls; }

    public void insertControls() {
        mRepository.insertControls();
    }



}
