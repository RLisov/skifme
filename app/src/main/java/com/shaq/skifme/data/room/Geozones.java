package com.shaq.skifme.data.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity( tableName = "geozones")
public class Geozones {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "name")
    private String mName;

    public Geozones(@NonNull String name) {
        this.mName = name;
    }

    @NonNull
    public String getName() {
        return mName;
    }

    public void setName(@NonNull String name) {
        this.mName = name;
    }
}
