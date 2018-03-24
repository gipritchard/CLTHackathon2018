package com.theideallab.clthackathon2018.model;


import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import io.realm.RealmObject;

public class GenericModelNameHere extends RealmObject {

    private ArrayList<LatLng> coordinateList;

    public GenericModelNameHere() {}

    public GenericModelNameHere(ArrayList<LatLng> coordinateList) {
        this.coordinateList = coordinateList;
    }

    public ArrayList<LatLng> getCoordinateList() {
        return coordinateList;
    }

    public void setCoordinateList(ArrayList<LatLng> coordinateList) {
        this.coordinateList = coordinateList;
    }
}
