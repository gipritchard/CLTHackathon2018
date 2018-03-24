package com.theideallab.clthackathon2018.application.heatmap;

import android.support.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.heatmaps.WeightedLatLng;

import java.util.ArrayList;

/**
 * Created by Alex Pritchard on 3/23/18.
 *
 */
public interface HeatMapData {

    int getId();
    @NonNull ArrayList<LatLng> getPoints();
    @NonNull ArrayList<WeightedLatLng> getWeightedPoints();
    @NonNull String getFilterName();
    @NonNull String getType();

}
