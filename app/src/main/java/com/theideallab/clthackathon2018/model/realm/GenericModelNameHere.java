package com.theideallab.clthackathon2018.model.realm;


import io.realm.RealmObject;

public class GenericModelNameHere extends RealmObject {

    private double latitude;
    private double longtitude;

    public GenericModelNameHere() {
    }

    public GenericModelNameHere(double latitude, double longtitude) {
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }
}
