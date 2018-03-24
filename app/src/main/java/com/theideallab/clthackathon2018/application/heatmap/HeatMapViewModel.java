package com.theideallab.clthackathon2018.application.heatmap;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.maps.android.heatmaps.HeatmapTileProvider;

import java.util.ArrayList;

/**
 * Created by Alex Pritchard on 3/23/18.
 */

public class HeatMapViewModel extends AndroidViewModel {

    public MutableLiveData<ArrayList<TileOverlayOptions>> heatmapFilters = new MutableLiveData<>();

    public HeatMapViewModel(@NonNull Application application) {
        super(application);

//        ArrayList<HeatMapData> filterData = new ArrayList<>();
    }

    public void generateTestData(){

        ArrayList<LatLng> list = new ArrayList<>();
        list.add(new LatLng(-37.1886, 145.708));
        list.add(new LatLng(-37.8361, 144.845));
        list.add(new LatLng(-36.9672, 144.192));

        // Create a heat map tile provider, passing it the latlngs
        HeatmapTileProvider provider = new HeatmapTileProvider.Builder()
                .data(list)
                .build();

        ArrayList<TileOverlayOptions> overlays = new ArrayList<>();
        overlays.add(new TileOverlayOptions().tileProvider(provider));

        heatmapFilters.postValue(overlays);
    }

}
