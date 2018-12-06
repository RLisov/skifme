package com.shaq.skifme.data.room;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.shaq.skifme.data.LoginBody;
import com.shaq.skifme.data.managers.DataManager;
import com.shaq.skifme.utils.ConstantManager;
import com.shaq.skifme.utils.GeoDao;
import com.shaq.skifme.utils.NetworkStatusChecker;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Database(entities = {Geozones.class /*, AnotherEntityType.class, AThirdEntityType.class */}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    public abstract GeoDao getGeoDao();

    private static volatile AppDatabase INSTANCE;


    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "geozones")
                            .addMigrations(AppDatabase.MIGRATION_1_2)
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(final SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE geozones ADD COLUMN controlName TEXT DEFAULT 0");
        }
    };

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