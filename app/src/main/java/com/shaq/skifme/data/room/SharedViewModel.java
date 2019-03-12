package com.shaq.skifme.data.room;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.List;

public class SharedViewModel extends AndroidViewModel {

    private AppRepository mAppRepository;

    private LiveData<List<Objects>> mAllObjects;
    private MutableLiveData<Objects> mObjectsMutableLiveData = new MutableLiveData<>();

    public SharedViewModel(Application application) {
        super(application);
        mAppRepository = new AppRepository(application);
    }

    public void setObject(Objects inputObject) {
        mObjectsMutableLiveData.setValue(inputObject);
    }

    public LiveData<Objects> getObject() {
        return mObjectsMutableLiveData;
    }


}
