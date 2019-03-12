package com.shaq.skifme.utils;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


import com.shaq.skifme.data.room.Controls;
import com.shaq.skifme.data.room.Objects;

import java.util.List;

@Dao
public interface AppDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Objects... geozones);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertObject(Objects... objects);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertControls(Controls... controls);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void  setObject(Objects object);

    @Query("DELETE FROM Objects")
    void deleteAll();

    @Delete
    void delete(Objects objects);

    @Query("SELECT * FROM Objects")
    LiveData<List<Objects>> getAllObjects();

    @Query("SELECT * FROM Controls")
    LiveData<List<Controls>> getAllControls();


    @Query("SELECT * FROM Objects WHERE name = :name")
    LiveData<List<Objects>> getGeoName(String name);


//
//    // Получение всех Person из бд с условием
//    @Query("SELECT * FROM geozonesres WHERE name LIKE :name")
//    List<GeozonesRes> getAllPeopleWithFavoriteColor(String name);

}