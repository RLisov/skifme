
package com.shaq.skifme.data.req;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostTracksBody {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("auto")
    @Expose
    private Auto auto;
    @SerializedName("time_from")
    @Expose
    private String timeFrom;
    @SerializedName("time_to")
    @Expose
    private String timeTo;
    @SerializedName("zoom")
    @Expose
    private Integer zoom;
    @SerializedName("south_west")
    @Expose
    private SouthWest southWest;
    @SerializedName("north_east")
    @Expose
    private NorthEast northEast;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    public String getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
    }

    public Integer getZoom() {
        return zoom;
    }

    public void setZoom(Integer zoom) {
        this.zoom = zoom;
    }

    public SouthWest getSouthWest() {
        return southWest;
    }

    public void setSouthWest(SouthWest southWest) {
        this.southWest = southWest;
    }

    public NorthEast getNorthEast() {
        return northEast;
    }

    public void setNorthEast(NorthEast northEast) {
        this.northEast = northEast;
    }

    @Override
    public String toString() {
        return "PostTracksBody{" +
                "type='" + type + '\'' +
                ", auto=" + auto +
                ", timeFrom='" + timeFrom + '\'' +
                ", timeTo='" + timeTo + '\'' +
                ", zoom=" + zoom +
                ", southWest=" + southWest +
                ", northEast=" + northEast +
                '}';
    }

    public class SouthWest {

        @SerializedName("lat")
        @Expose
        private Float lat;
        @SerializedName("lon")
        @Expose
        private Float lon;

        public Float getLat() {
            return lat;
        }

        public void setLat(Float lat) {
            this.lat = lat;
        }

        public Float getLon() {
            return lon;
        }

        public void setLon(Float lon) {
            this.lon = lon;
        }

    }

    public class NorthEast {

        @SerializedName("lat")
        @Expose
        private Float lat;
        @SerializedName("lon")
        @Expose
        private Float lon;

        public Float getLat() {
            return lat;
        }

        public void setLat(Float lat) {
            this.lat = lat;
        }

        public Float getLon() {
            return lon;
        }

        public void setLon(Float lon) {
            this.lon = lon;
        }

    }

    public class Auto {

        @SerializedName("id")
        @Expose
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

    }
}
