package com.shaq.skifme.data.res;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeozonesRes {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("type")
    @Expose
    public Type type;
    @SerializedName("geometry")
    @Expose
    public List<List<Float>> geometry = new ArrayList<List<Float>>();
    @SerializedName("color")
    @Expose
    public Color color;
    @SerializedName("width")
    @Expose
    public int width;
    @SerializedName("icon")
    @Expose
    public Icon icon;

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

    }
}