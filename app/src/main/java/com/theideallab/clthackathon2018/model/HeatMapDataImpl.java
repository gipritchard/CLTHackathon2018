package com.theideallab.clthackathon2018.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.heatmaps.Gradient;
import com.google.maps.android.heatmaps.WeightedLatLng;
import com.theideallab.clthackathon2018.application.heatmap.HeatMapData;
import com.theideallab.clthackathon2018.repository.retrofit.response.obj.ObjResponse;

import java.util.ArrayList;

import javax.annotation.Nonnull;

import io.realm.RealmList;
import io.realm.RealmObject;

public class HeatMapDataImpl implements HeatMapData {

    private RealmList<Coordinate> coordinateList = new RealmList<>();
    private Gradient gradient;

    public HeatMapDataImpl() {}

    public HeatMapDataImpl(@NonNull ObjResponse data, @Nullable Gradient gradient) {
        this.coordinateList.addAll(data.getAllCoordinates());
        this.gradient = gradient;
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
        return new ArrayList<>();
    }

    @NonNull
    @Override
    public String getType() {
        return "";
    }

    @Nonnull
    @Override
    public String getFilterName() {
        return "";
    }


    @Nullable
    @Override
    public Gradient getGradient() {
        return gradient;
    }
}