package com.theideallab.clthackathon2018.model;


import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import io.realm.RealmObject;

public class GenericModelNameHere extends RealmObject {

    private ArrayList<LatLng> coordinateList;
    private String filterName;
    private String filterType;

    public GenericModelNameHere() {}

    public GenericModelNameHere(ArrayList<LatLng> coordinateList, String filterName, String filterType) {
        this.coordinateList = coordinateList;
        this.filterName = filterName;
        this.filterType = filterType;
    }

    public ArrayList<LatLng> getCoordinateList() {
        return coordinateList;
    }

    public void setCoordinateList(ArrayList<LatLng> coordinateList) {
        this.coordinateList = coordinateList;
    }

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public String getFilterType() {
        return filterType;
    }

    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }
}
