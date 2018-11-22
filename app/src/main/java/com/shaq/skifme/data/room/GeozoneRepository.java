package com.shaq.skifme.data.room;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.shaq.skifme.data.managers.DataManager;
import com.shaq.skifme.data.res.GeozonesRes;
import com.shaq.skifme.utils.GeoDao;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class GeozoneRepository {

    private GeoDao mGeoDao;
    private LiveData<List<Geozones>> mAllGeozones;
    private DataManager mDataManager;

    GeozoneRepository(Context context) {
        AppDatabase db = AppDatabase.getDatabase(context);
        mGeoDao = db.getGeoDao();
        mAllGeozones = mGeoDao.getAllGeozones();
        mDataManager = DataManager.getInstance();
    }

    LiveData<List<Geozones>> getAllGeozones() {
        updateGeoList();
        return mAllGeozones;
    }

    public void insert () {

        Call<List<GeozonesRes>> call = mDataManager.getGeozonesList(mDataManager.getPreferencesManager().getCookie());
        call.enqueue(new Callback<List<GeozonesRes>>() {
            @Override
            public void onResponse(Call<List<GeozonesRes>> call, Response<List<GeozonesRes>> response) {
                Log.d("network call",String.valueOf(response.code()));
                Geozones geozones = new Geozones(response.body().get(1).name);
                new insertAsyncTask(mGeoDao).execute(geozones);
            }

            @Override
            public void onFailure(Call<List<GeozonesRes>> call, Throwable t) {

            }
        });




    }

    public void deleteAll () {

        new deleteAllAsyncTask(mGeoDao).execute();
    }

    public void updateGeoList() {
        Call<List<GeozonesRes>> call = mDataManager.getGeozonesList(mDataManager.getPreferencesManager().getCookie());
        call.enqueue(new Callback<List<GeozonesRes>>() {
            @Override
            public void onResponse(Call<List<GeozonesRes>> call, Response<List<GeozonesRes>> response) {
                Log.d("network call",String.valueOf(response.code()));

            }

            @Override
            public void onFailure(Call<List<GeozonesRes>> call, Throwable t) {

            }
        });


    }

    private static class insertAsyncTask extends AsyncTask<Geozones, Void, Void> {

        private GeoDao mAsyncTaskDao;

        insertAsyncTask(GeoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Geozones... geozones) {
            mAsyncTaskDao.insert(geozones[0]);

            return null;
        }
    }

    private static class deleteAllAsyncTask extends AsyncTask<Geozones, Void, Void> {

        private GeoDao mAsyncTaskDao;

        deleteAllAsyncTask(GeoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Geozones... geozones) {
            mAsyncTaskDao.insert(geozones[0]);

            return null;
        }
    }
}
