package com.shaq.skifme.data.res;



import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ControlRes {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("coordinates")
    @Expose
    private List<Float> coordinates = new ArrayList<Float>();
    @SerializedName("days")
    @Expose
    private List<Integer> days = new ArrayList<Integer>();
    @SerializedName("control_start")
    @Expose
    private String controlStart;
    @SerializedName("control_end")
    @Expose
    private String controlEnd;
    @SerializedName("notify_in")
    @Expose
    private boolean notifyIn;
    @SerializedName("notify_bad")
    @Expose
    private boolean notifyBad;
    @SerializedName("is_active")
    @Expose
    private boolean isActive;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Float> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Float> coordinates) {
        this.coordinates = coordinates;
    }

    public List<Integer> getDays() {
        return days;
    }

    public void setDays(List<Integer> days) {
        this.days = days;
    }

    public String getControlStart() {
        return controlStart;
    }

    public void setControlStart(String controlStart) {
        this.controlStart = controlStart;
    }

    public String getControlEnd() {
        return controlEnd;
    }

    public void setControlEnd(String controlEnd) {
        this.controlEnd = controlEnd;
    }

    public boolean isNotifyIn() {
        return notifyIn;
    }

    public void setNotifyIn(boolean notifyIn) {
        this.notifyIn = notifyIn;
    }

    public boolean isNotifyBad() {
        return notifyBad;
    }

    public void setNotifyBad(boolean notifyBad) {
        this.notifyBad = notifyBad;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

}
