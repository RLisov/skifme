package com.shaq.skifme.data.res;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeozonesRes  {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("type")
    @Expose
    public Type type;

    @Ignore
    @SerializedName("geometry")
    @Expose
    public List<List<Double>> geometry = new ArrayList<List<Double>>();
    @SerializedName("color")
    @Expose
    public Color color;
    @SerializedName("width")
    @Expose
    public Double width;
    @SerializedName("icon")
    @Expose
    public Icon icon;

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public void setWidth(Double width) {

        this.width = width;
    }

    public void setGeometry(List<List<Double>> geometry) {

        this.geometry = geometry;
    }

    public void setType(Type type) {

        this.type = type;
    }

    public void setColor(Color color) {

        this.color = color;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setId(String id) {

        this.id = id;
    }

    public List<List<Double>> getGeometry() {
        return geometry;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getWidth() {
        return width;
    }

    public Color getColor() {
        return color;
    }

    public class Icon {

        @SerializedName("type")
        @Expose
        public String type;
        @SerializedName("key")
        @Expose
        public String key;
        @SerializedName("value_ru")
        @Expose
        public String valueRu;
        @SerializedName("value_en")
        @Expose
        public String valueEn;
        @SerializedName("value_kz")
        @Expose
        public String valueKz;

    }

    public class Color {

        @SerializedName("type")
        @Expose
        public String type;
        @SerializedName("key")
        @Expose
        public String key;
        @SerializedName("value_ru")
        @Expose
        public String valueRu;
        @SerializedName("value_en")
        @Expose
        public String valueEn;
        @SerializedName("value_kz")
        @Expose
        public String valueKz;

    }

    public class Type {

        @SerializedName("type")
        @Expose
        public String type;
        @SerializedName("key")
        @Expose
        public String key;
        @SerializedName("value_ru")
        @Expose
        public String valueRu;
        @SerializedName("value_en")
        @Expose
        public String valueEn;
        @SerializedName("value_kz")
        @Expose
        public String valueKz;

        public String getType() {
            return type;
        }

        public String getValueRu() {
            return valueRu;
        }

        public String getValueEn() {
            return valueEn;
        }

        public String getValueKz() {
            return valueKz;
        }
    }

    public Type getType() {
        return type;
    }
}