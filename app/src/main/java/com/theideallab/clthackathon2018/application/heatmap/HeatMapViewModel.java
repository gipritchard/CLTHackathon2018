package com.theideallab.clthackathon2018.application.heatmap;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.maps.android.heatmaps.HeatmapTileProvider;
import com.theideallab.clthackathon2018.model.preferences.SharedPreferencesHelper;
import com.theideallab.clthackathon2018.repository.Repository;

import java.util.ArrayList;

/**
 * Created by Alex Pritchard on 3/23/18.
 */

public class HeatMapViewModel extends AndroidViewModel {

    private Repository repository;
    public ArrayList<String> filters;

    public MutableLiveData<Boolean> clearFilters = new MutableLiveData<>();
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

    public void onLoadComplete(@NonNull Context ctx){
        SharedPreferencesHelper helper = new SharedPreferencesHelper(ctx);
        repository.launchGetRequestBasedOffOfSharedPreferences(helper);
    }

    public void generateTestData(@NonNull ArrayList<HeatMapData> dataset){

        ArrayList<TileOverlayOptions> overlays = new ArrayList<>();

        for(HeatMapData entry : dataset) {

            if(!entry.getPoints().isEmpty()) {
                HeatmapTileProvider provider = new HeatmapTileProvider.Builder()
                        .data(entry.getPoints())
                        //.weightedData(entry.getWeightedPoints())
                        .build();

                overlays.add(new TileOverlayOptions().tileProvider(provider));
            }
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

        SharedPreferencesHelper helper = new SharedPreferencesHelper(ctx);

        String[] filterOptions = new String[] { "Compare Price", "Show Schools", "Show Hospitals" };
        boolean[] filterOptionsChecked = new boolean[] { helper.getPrice(), helper.getSchools(), helper.getHospitals()};

        builder.setMultiChoiceItems(filterOptions, filterOptionsChecked, (dialog, which, isChecked) -> {
            filterOptionsChecked[which] = isChecked;
        });

        builder.setPositiveButton("Apply", (dialog, which) -> {

            clearFilters.postValue(true);
            heatmapFilters.setValue(new ArrayList<>());

            for (int i = 0; i < filterOptionsChecked.length; i++) {

                boolean checked = filterOptionsChecked[i];

                switch (i) {

                    case 0:
                    {
                        helper.setPrice(checked);
                    }
                    break;

                    case 1:
                    {
                        helper.setSchools(checked);
                    }
                    break;

                    case 2:
                    {
                        helper.setHospital(checked);
                    }
                    break;

                    default: { } break;
                }
            }

            repository.launchGetRequestBasedOffOfSharedPreferences(helper);
        });

        builder.setNeutralButton("Advanced Filter", (dialog, which) -> {
            Toast.makeText(ctx, "Coming Soon",Toast.LENGTH_SHORT).show();
//            Intent intent = AdvancedFilterActivity.createIntent(ctx);
//            ctx.startActivityForResult(intent, 123);
        });

        return builder.create();
    }

}
