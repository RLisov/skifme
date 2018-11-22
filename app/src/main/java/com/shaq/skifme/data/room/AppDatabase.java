package com.shaq.skifme.data.room;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.shaq.skifme.data.LoginBody;
import com.shaq.skifme.data.managers.DataManager;
import com.shaq.skifme.utils.ConstantManager;
import com.shaq.skifme.utils.GeoDao;
import com.shaq.skifme.utils.NetworkStatusChecker;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Database(entities = {Geozones.class /*, AnotherEntityType.class, AThirdEntityType.class */}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract GeoDao getGeoDao();

    private static volatile AppDatabase INSTANCE;


    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "geozones")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final GeoDao mDao;

        PopulateDbAsync(AppDatabase db) {
            mDao = db.getGeoDao();

        }


        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.

            mDao.deleteAll();

//            Geozones geo = new Geozones("Hello");
//            mDao.insert(geo);
//            geo = new Geozones("World1");
//            mDao.insert(geo);
            return null;
        }
    }


}