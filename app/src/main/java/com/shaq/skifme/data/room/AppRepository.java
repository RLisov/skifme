package com.shaq.skifme.data.room;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.shaq.skifme.data.managers.DataManager;
import com.shaq.skifme.data.res.ControlRes;
import com.shaq.skifme.data.res.ObjectsRes;
import com.shaq.skifme.utils.AppDao;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class AppRepository {

    private AppDao mAppDao;
    private LiveData<List<Objects>> mAllObjects;
    private DataManager mDataManager;

    AppRepository(Context context) {
        AppDatabase db = AppDatabase.getDatabase(context);
        mAppDao = db.getAppDao();
        mAllObjects = mAppDao.getAllObjects();
        mDataManager = DataManager.getInstance();
    }

    LiveData<List<Objects>> getAllObjects() {
        return mAllObjects;
    }

    public void insertObjects() {
        Call<List<ObjectsRes>> call = mDataManager.getObjects();
        call.enqueue(new Callback<List<ObjectsRes>>() {
            @Override
            public void onResponse(Call<List<ObjectsRes>> call, Response<List<ObjectsRes>> response) {
               Log.d("Repo", String.valueOf(response.body().size()));
                for (int i = 0; i<response.body().size(); i++) {
                    List<ObjectsRes> data = response.body();
                    Objects objects = new Objects(data.get(i).getId(),
                            data.get(i).getName(),
                            data.get(i).getImage(),
                            data.get(i).isAlert(),
                            data.get(i).getBatteryLevel(),
                            data.get(i).getLastOnline());

                    new insertObjectsAsyncTask(mAppDao).execute(objects);
                }
            }

            @Override
            public void onFailure(Call<List<ObjectsRes>> call, Throwable t) {

            }
        });
    }

    public void deleteAll () {

        new deleteAllAsyncTask(mAppDao).execute();
    }


    private static class insertObjectsAsyncTask extends AsyncTask<Objects, Void, Void> {

        private AppDao mAsyncTaskDao;

        insertObjectsAsyncTask(AppDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Objects... objects) {
            mAsyncTaskDao.insertObject(objects[0]);

            return null;
        }

    }


    private static class insertAsyncTask extends AsyncTask<Objects, Void, Void> {

        private AppDao mAsyncTaskDao;

        insertAsyncTask(AppDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Objects... objects) {
            mAsyncTaskDao.insertObject(objects[0]);

            return null;
        }

    }

    private static class insertControlsTask extends AsyncTask<Controls, Void, Void> {

        private AppDao mAsyncTaskDao;

        insertControlsTask(AppDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Controls... controls) {
            mAsyncTaskDao.insertControls(controls[0]);
            return null;
        }
    }

    private static class deleteAllAsyncTask extends AsyncTask<Objects, Void, Void> {

        private AppDao mAsyncTaskDao;

        deleteAllAsyncTask(AppDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Objects... geozones) {
            mAsyncTaskDao.delete(geozones[0]);

            return null;
        }
    }
}