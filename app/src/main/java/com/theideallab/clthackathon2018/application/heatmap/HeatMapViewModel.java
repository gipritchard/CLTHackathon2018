package com.theideallab.clthackathon2018.application.heatmap;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.maps.android.heatmaps.HeatmapTileProvider;
import com.theideallab.clthackathon2018.R;
import com.theideallab.clthackathon2018.application.advancedfilter.AdvancedFilterActivity;
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
        //repository.testOnGetDataSuccessfulNerds();
        repository.retrofitGetObject();
    }

    public void generateTestData(@NonNull ArrayList<HeatMapData> dataset){

        ArrayList<TileOverlayOptions> overlays = new ArrayList<>();

        for(HeatMapData entry : dataset) {

            HeatmapTileProvider provider = new HeatmapTileProvider.Builder()
                    .data(entry.getPoints())
                    //.weightedData(entry.getWeightedPoints())
                    .build();

            overlays.add(new TileOverlayOptions().tileProvider(provider));
        }

        heatmapFilters.postValue(overlays);
    }

    public void getOverlays() {
        //TODO the api things ( and probably return live data things)
        //Can just use filters from the viewmodel now
    }

    public AlertDialog createFilterDialog(@NonNull AppCompatActivity ctx) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);

        builder.setTitle("Filter");

        String[] filterOptions = new String[] { "Compare Price", "Show Schools", "Access to Public Transportation" };
        boolean[] filterOptionsChecked = new boolean[] { true, true, false};

        builder.setMultiChoiceItems(filterOptions, filterOptionsChecked, (dialog, which, isChecked) -> {
        });

        builder.setNeutralButton("Advanced Filter", (dialog, which) -> {
            Intent intent = AdvancedFilterActivity.createIntent(ctx);
            ctx.startActivityForResult(intent, 123);
        });

        return builder.create();
    }

}
