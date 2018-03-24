package com.theideallab.clthackathon2018.application.heatmap;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.maps.android.heatmaps.HeatmapTileProvider;
import com.theideallab.clthackathon2018.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex Pritchard on 3/23/18.
 */

public class HeatMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private HeatMapViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heatmap);

        viewModel = ViewModelProviders.of(this).get(HeatMapViewModel.class);


        // Build the map.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapfragment);
        mapFragment.getMapAsync(this);
    }


    private void addHeatMap(@NonNull GoogleMap map) {
        List<LatLng> list = new ArrayList<>();

        // Get the data: latitude/longitude positions of police stations.
        list.add(new LatLng(-37.1886, 145.708));
        list.add(new LatLng(-37.8361,144.845));
        list.add(new LatLng(-36.9672,144.192));

        // Create a heat map tile provider, passing it the latlngs of the police stations.
        HeatmapTileProvider mProvider = new HeatmapTileProvider.Builder()
                .data(list)
                .build();
        // Add a tile overlay to the map, using the heat map tile provider.
        map.addTileOverlay(new TileOverlayOptions().tileProvider(mProvider));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        addHeatMap(googleMap);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }
}
