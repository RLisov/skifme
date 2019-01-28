package com.shaq.skifme.data.room;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.shaq.skifme.data.managers.DataManager;
import com.shaq.skifme.data.res.ControlRes;
import com.shaq.skifme.data.res.ObjectsRes;
import com.shaq.skifme.utils.AppDao;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class AppRepository {

    private AppDao mAppDao;
    private LiveData<List<Objects>> mAllObjects;
    private LiveData<List<Controls>> mAllControls;
    private DataManager mDataManager;

    AppRepository(Context context) {
        AppDatabase db = AppDatabase.getDatabase(context);
        mAppDao = db.getAppDao();
        mAllObjects = mAppDao.getAllObjects();
        mAllControls = mAppDao.getAllControls();
        mDataManager = DataManager.getInstance();
    }

    LiveData<List<Objects>> getAllObjects() {
        return mAllObjects;
    }

    LiveData<List<Controls>> getAllControls() { return mAllControls; }

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

                            data.get(i).getLastOnline(),
                            data.get(i).getControl().getTitle());

                    new insertObjectsAsyncTask(mAppDao).execute(objects);
                }
            }

            @Override
            public void onFailure(Call<List<ObjectsRes>> call, Throwable t) {
                Log.e("Repo",String.valueOf(t));
            }
        });
    }

    public void insertControls() {
        Call<List<ControlRes>> call = mDataManager.getControls();
        call.enqueue(new Callback<List<ControlRes>>() {
            @Override
            public void onResponse(Call<List<ControlRes>> call, Response<List<ControlRes>> response) {
                Log.d("RepoControl", String.valueOf(response.code()));
                for (int i = 0; i < response.body().size(); i++) {
                    List<ControlRes> data = response.body();
                    Controls controls = new Controls(
                            data.get(i).getId(),
                            data.get(i).getTitle());


                    new insertControlsTask(mAppDao).execute(controls);
                }
            }
            @Override
            public void onFailure(Call<List<ControlRes>> call, Throwable t) {
                Log.d("RepoControl", String.valueOf(t));
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