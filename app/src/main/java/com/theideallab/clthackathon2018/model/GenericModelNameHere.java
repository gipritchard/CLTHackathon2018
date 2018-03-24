package com.theideallab.clthackathon2018.model;

import io.realm.RealmList;
import io.realm.RealmObject;

public class GenericModelNameHere extends RealmObject {

    private RealmList<Coordinate> coordinateList;
    private String filterName;
    private String filterType;

    public GenericModelNameHere() {
    }

    public GenericModelNameHere(RealmList<Coordinate> coordinateList) {
        this.coordinateList = coordinateList;
    }

    public RealmList<Coordinate> getCoordinateList() {
        return coordinateList;
    }

    public void setCoordinateList(RealmList<Coordinate> coordinateList) {
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