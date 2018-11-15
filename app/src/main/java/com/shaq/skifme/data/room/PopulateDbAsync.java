package com.shaq.skifme.data.room;

import android.os.AsyncTask;

import com.shaq.skifme.utils.GeoDao;

public class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

    private final GeoDao mDao;

    PopulateDbAsync(AppDatabase db) {
        mDao = db.getGeoDao();
    }

    @Override
    protected Void doInBackground(final Void... params) {
        mDao.deleteAll();
        Geozones geo = new Geozones();
        geo.setId("1");
        geo.setName("erere");
        geo.setColor("#FFF000");
        geo.setGeotype("circle");
        mDao.insert(geo);

        return null;
    }
}