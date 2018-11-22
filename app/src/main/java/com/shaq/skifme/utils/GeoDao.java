package com.shaq.skifme.utils;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


import com.shaq.skifme.data.room.Geozones;

import java.util.List;

@Dao
public interface GeoDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Geozones... geozones);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Geozones geozones);

    @Update
    void update(Geozones geozones);

    @Query("DELETE FROM geozones")
    void deleteAll();

    @Delete
    void delete(Geozones geozones);

    @Query("SELECT * FROM geozones")
    LiveData<List<Geozones>> getAllGeozones();

    @Query("SELECT * FROM geozones WHERE name = :name")
    LiveData<List<Geozones>> getGeoName(String name);


//
//    // Получение всех Person из бд с условием
//    @Query("SELECT * FROM geozonesres WHERE name LIKE :name")
//    List<GeozonesRes> getAllPeopleWithFavoriteColor(String name);

}