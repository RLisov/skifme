package com.shaq.skifme.data.room;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.shaq.skifme.utils.AppDao;

@Database(entities = {Objects.class , Controls.class /*, AnotherEntityType.class, AThirdEntityType.class */}, version = 4, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract AppDao getAppDao();

    private static volatile AppDatabase INSTANCE;


    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "appDb")
                            .addMigrations(AppDatabase.MIGRATION_2_3)
                            .fallbackToDestructiveMigration()
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

    public static final Migration MIGRATION_2_3 = new Migration(1, 2) {
        @Override
        public void migrate(final SupportSQLiteDatabase database) {


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

        private final AppDao mDao;


        PopulateDbAsync(AppDatabase db) {
            mDao = db.getAppDao();

        }


        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.

            mDao.deleteAll();

//            Objects geo = new Objects("Катя");
//            geo.setControlName("Школа");
//            mDao.insertObjects(geo);
//            Objects geo1 = new Objects("Бобик");
//            geo1.setControlName("Бассейн");
//            mDao.insertObjects(geo1);
//            Objects geo2 = new Objects("Петя");
//            geo2.setControlName("Английский язык");
//            mDao.insertObjects(geo2);

            return null;
        }
    }


}