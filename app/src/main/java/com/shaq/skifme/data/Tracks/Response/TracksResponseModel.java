package com.shaq.skifme.data.Tracks.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TracksResponseModel {



    @SerializedName("datepoint")
    @Expose
    private String datepoint;
    @SerializedName("x")
    @Expose
    private Double x;
    @SerializedName("y")
    @Expose
    private Double y;

    public TracksResponseModel(String datepoint, Double x, Double y) {
        this.datepoint = datepoint;
        this.x = x;
        this.y = y;
    }

    public String getDatepoint() {
        return datepoint;
    }

    public void setDatepoint(String datepoint) {
        this.datepoint = datepoint;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "TracksResponseModel{" +
                "datepoint='" + datepoint + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}