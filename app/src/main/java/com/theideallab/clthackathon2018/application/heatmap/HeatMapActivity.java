package com.theideallab.clthackathon2018.application.heatmap;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupWindow;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.theideallab.clthackathon2018.R;

import java.util.ArrayList;


public class HeatMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final int MY_LOCATION_REQUEST_CODE = 69;

    private GoogleMap googleMap;
    private HeatMapViewModel viewModel;
    private PopupWindow popupWindow;

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
        checkAndRequestPermissions(googleMap);
        viewModel.onLoadComplete();
    }

    @Override public void onPointerCaptureChanged(boolean hasCapture) {}

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == MY_LOCATION_REQUEST_CODE) {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED &&
                    permissions.length == 1 &&
                    this.googleMap != null) {
                this.googleMap.setMyLocationEnabled(true);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.filter_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (viewModel.filters == null) {
            viewModel.filters = new ArrayList<String>();
        }

        switch (item.getItemId()) {
            case R.id.has_children:
                item.setChecked(!item.isChecked());
                viewModel.filters.add("SCHOOL");
                break;
            default:
                break;
        }

        viewModel.getOverlays();

        return false;
    }

    private void checkAndRequestPermissions(@NonNull GoogleMap googleMap) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED)
        {
            googleMap.setMyLocationEnabled(true);
        }
        else {
            String[] permissions = new String[] { Manifest.permission.ACCESS_FINE_LOCATION };
            ActivityCompat.requestPermissions(this, permissions, MY_LOCATION_REQUEST_CODE);
        }
    }

    public void showFilterMenu(View v) {
        if (popupWindow == null) {
            popupWindow = new PopupWindow();
        }


    }
}
