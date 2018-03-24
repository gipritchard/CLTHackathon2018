package com.theideallab.clthackathon2018.application.heatmap;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.theideallab.clthackathon2018.R;


public class HeatMapActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener {

    private static final int MY_LOCATION_REQUEST_CODE = 69;

    private GoogleMap googleMap;
    private LocationManager locationManager;
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
        checkAndRequestPermissions(googleMap);
        viewModel.onLoadComplete();
    }

    @Override
    public void onLocationChanged(Location location) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 10);
        googleMap.animateCamera(cameraUpdate);

        if (locationManager != null) {
            locationManager.removeUpdates(this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == MY_LOCATION_REQUEST_CODE) {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED &&
                    permissions.length == 1 &&
                    this.googleMap != null)
            {
                onSuccessfulPermissionRequest(this.googleMap);
            }
        }
    }

    private boolean onSuccessfulPermissionRequest(@NonNull GoogleMap googleMap) {
        boolean permissionGranted = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;

        if (permissionGranted)
        {
            googleMap.setMyLocationEnabled(true);

            // Zoom to user's location
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (locationManager != null) {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 400, 1000, this); //You can also use LocationManager.GPS_PROVIDER and LocationManager.PASSIVE_PROVIDER
            }
        }

        return permissionGranted;
    }

    private void checkAndRequestPermissions(@NonNull GoogleMap googleMap) {
        if (!onSuccessfulPermissionRequest(googleMap))
        {
            String[] permissions = new String[] { Manifest.permission.ACCESS_FINE_LOCATION };
            ActivityCompat.requestPermissions(this, permissions, MY_LOCATION_REQUEST_CODE);
        }
    }

    // Unused OnMapReadyCallback
    @Override public void onPointerCaptureChanged(boolean hasCapture) {}

    // Unused LocationListener
    @Override public void onStatusChanged(String provider, int status, Bundle extras) { }
    @Override public void onProviderEnabled(String provider) { }
    @Override public void onProviderDisabled(String provider) { }
}
