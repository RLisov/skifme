package com.shaq.skifme.data.res;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ObjectsRes {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("control")
    @Expose
    private Control control;
    @SerializedName("battery_level")
    @Expose
    private int batteryLevel;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("alert")
    @Expose
    private boolean alert;
    @SerializedName("coordinates")
    @Expose
    private List<Float> coordinates = new ArrayList<Float>();
    @SerializedName("last_online")
    @Expose
    private int lastOnline;
    @SerializedName("is_online")
    @Expose
    private boolean isOnline;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Control getControl() {
        return control;
    }

    public void setControl(Control control) {
        this.control = control;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isAlert() {
        return alert;
    }

    public void setAlert(boolean alert) {
        this.alert = alert;
    }

    public List<Float> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Float> coordinates) {
        this.coordinates = coordinates;
    }

    public int getLastOnline() {
        return lastOnline;
    }

    public void setLastOnline(int lastOnline) {
        this.lastOnline = lastOnline;
    }

    public boolean isIsOnline() {
        return isOnline;
    }

    public void setIsOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }

    public class Control {

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
}

