package com.theideallab.clthackathon2018.model;


import io.realm.RealmObject;

public class Coordinate extends RealmObject {

    private double latitutde;
    private double longitude;

    public Coordinate() {}

    public Coordinate(double latitutde, double longitude) {
        this.latitutde = latitutde;
        this.longitude = longitude;
    }

    public double getLatitutde() {
        return latitutde;
    }

    public void setLatitutde(double latitutde) {
        this.latitutde = latitutde;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
