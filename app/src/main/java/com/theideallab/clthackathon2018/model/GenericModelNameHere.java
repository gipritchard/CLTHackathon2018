package com.theideallab.clthackathon2018.model;

import android.support.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.heatmaps.WeightedLatLng;
import com.theideallab.clthackathon2018.application.heatmap.HeatMapData;

import java.util.ArrayList;

import javax.annotation.Nonnull;

import io.realm.RealmList;
import io.realm.RealmObject;

public class GenericModelNameHere extends RealmObject implements HeatMapData {

    private RealmList<Coordinate> coordinateList;
    private String filterName;
    private String filterType;

    public GenericModelNameHere() {
    }

    public GenericModelNameHere(RealmList<Coordinate> coordinateList) {
        this.coordinateList = coordinateList;
    }

    @Override
    public int getId() {
        return 0;
    }

    @NonNull
    @Override
    public ArrayList<LatLng> getPoints() {
        ArrayList<LatLng> retval = new ArrayList<>();
        for (Coordinate c : coordinateList) {
            retval.add(new LatLng(c.getLatitutde(), c.getLongitude()));
        }

        return retval;
    }

    @NonNull
    @Override
    public ArrayList<WeightedLatLng> getWeightedPoints() {
        return null;
    }

    @NonNull
    @Override
    public String getType() {
        return filterType;
    }

    public RealmList<Coordinate> getCoordinateList() {
        return coordinateList;
    }

    public void setCoordinateList(RealmList<Coordinate> coordinateList) {
        this.coordinateList = coordinateList;
    }

    @Nonnull
    @Override
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