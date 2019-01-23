package com.shaq.skifme.data.res;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TracksRes {

    @SerializedName("datepoint")
    @Expose
    private String datepoint;
    @SerializedName("x")
    @Expose
    private String x;
    @SerializedName("y")
    @Expose
    private String y;

    public String getDatepoint() {
        return datepoint;
    }

    public void setDatepoint(String datepoint) {
        this.datepoint = datepoint;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }
}
