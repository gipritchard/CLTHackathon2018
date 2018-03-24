package com.theideallab.clthackathon2018.application.heatmap;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.maps.android.heatmaps.HeatmapTileProvider;
import com.theideallab.clthackathon2018.repository.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex Pritchard on 3/23/18.
 */

public class HeatMapViewModel extends AndroidViewModel {

    private Repository repository;
    public ArrayList<String> filters;

    public MutableLiveData<ArrayList<TileOverlayOptions>> heatmapFilters = new MutableLiveData<>();

    public HeatMapViewModel(@NonNull Application application) {
        super(application);

        repository = new Repository();
        repository.heatMapData.observeForever(heatMapData -> {
            if (heatMapData != null) {
                generateTestData(heatMapData);
            }
        });
    }

    public void onLoadComplete(){
        repository.testOnGetDataSuccessfulNerds();
    }

    public void generateTestData(@NonNull ArrayList<HeatMapData> dataset){

        ArrayList<TileOverlayOptions> overlays = new ArrayList<>();

        for(HeatMapData entry : dataset) {

            HeatmapTileProvider provider = new HeatmapTileProvider.Builder()
                    .data(entry.getPoints())
                    .weightedData(entry.getWeightedPoints())
                    .build();

            overlays.add(new TileOverlayOptions().tileProvider(provider));
        }

        heatmapFilters.postValue(overlays);
    }

    public void getOverlays() {
        //TODO the api things ( and probably return live data things)
        //Can just use filters from the viewmodel now
    }

}
