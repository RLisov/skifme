package com.shaq.skifme.data.eventbus_data;

import com.shaq.skifme.data.res.GeozonesRes;

import java.util.List;

public class GeozonesEvent {
    private GeozonesRes geodata;

    public GeozonesEvent(GeozonesRes geodata) {
        this.geodata = geodata;
    }

    public GeozonesRes getGeodata() {
        return geodata;
    }
}
