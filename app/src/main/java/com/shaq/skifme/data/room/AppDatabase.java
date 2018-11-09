package com.shaq.skifme.data.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.shaq.skifme.utils.GeoDao;

@Database(entities = {Geozones.class /*, AnotherEntityType.class, AThirdEntityType.class */}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract GeoDao getGeoDao();
}