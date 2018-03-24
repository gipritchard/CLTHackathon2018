package com.theideallab.clthackathon2018.application.heatmap;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.theideallab.clthackathon2018.R;


public class HeatMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private HeatMapViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heatmap);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapfragment);
        mapFragment.getMapAsync(this);


        viewModel = ViewModelProviders.of(this).get(HeatMapViewModel.class);
        viewModel.heatmapFilters.observe(this, tileProviders -> {

            if (googleMap != null && tileProviders != null) {

                googleMap.clear();
                for(TileOverlayOptions entry : tileProviders) {
                    googleMap.addTileOverlay(entry);
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        viewModel.generateTestData();
    }

    @Override public void onPointerCaptureChanged(boolean hasCapture) {}
}
