package com.shaq.skifme.data.room;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.shaq.skifme.utils.GeoDao;

import java.util.List;

public class GeozoneRepository {

    private GeoDao mGeoDao;
    private LiveData<List<Geozones>> mAllGeozones;

    GeozoneRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mGeoDao = db.getGeoDao();
        mAllGeozones = mGeoDao.getAllGeozones();
    }

    LiveData<List<Geozones>> getAllGeozones() {
        return mAllGeozones;
    }

    public void insert (Geozones geozones) {
        new insertAsyncTask(mGeoDao).execute(geozones);
    }

    private static class insertAsyncTask extends AsyncTask<Geozones, Void, Void> {

        private GeoDao mAsyncTaskDao;

        insertAsyncTask(GeoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Geozones... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
