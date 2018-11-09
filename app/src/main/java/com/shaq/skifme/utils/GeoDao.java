package com.shaq.skifme.utils;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


import com.shaq.skifme.data.room.Geozones;

import java.util.List;

@Dao
public interface GeoDao {

    // Добавление Person в бд
    @Insert
    void insertAll(Geozones... geozones);

    @Insert
    void insert(Geozones geozones);

    @Update
    void update(Geozones geozones);

    // Удаление Person из бд
    @Delete
    void delete(Geozones geozones);

    @Query("SELECT * FROM geozones WHERE id = :id ")
    Geozones getById(long id);

    // Получение всех Person из бд
    @Query("SELECT * FROM geozones")
    List<Geozones> getAllPeople();
//
//    // Получение всех Person из бд с условием
//    @Query("SELECT * FROM geozonesres WHERE name LIKE :name")
//    List<GeozonesRes> getAllPeopleWithFavoriteColor(String name);

}