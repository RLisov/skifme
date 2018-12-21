package com.shaq.skifme.data.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.util.List;

@Entity( tableName = "controls")
public class Controls {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "id")
    private String mId;

//    @ColumnInfo(name = "days")
//    @TypeConverters({DaysConverter.class})
//    public List<String> mDays;

    public Controls(@NonNull String id, String name) {
        this.mName = name;
        this.mId = id;
    }

    @NonNull
    public String getName() {
        return mName;
    }

    public void setName(@NonNull String name) {
        this.mName = name;
    }


    public String getId() {

        return mId;
    }

    public void setId(String id) {
        mId = id;
    }
}
