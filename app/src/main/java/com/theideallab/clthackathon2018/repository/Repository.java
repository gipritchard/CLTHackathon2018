package com.theideallab.clthackathon2018.repository;


import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.heatmaps.WeightedLatLng;
import com.theideallab.clthackathon2018.application.heatmap.HeatMapData;
import com.theideallab.clthackathon2018.model.GenericModelNameHere;
import com.theideallab.clthackathon2018.repository.retrofit.HackathonApi;
import com.theideallab.clthackathon2018.repository.retrofit.HackathonApiClient;
import com.theideallab.clthackathon2018.repository.retrofit.response.obj.ObjResponse;

import java.util.ArrayList;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private HackathonApi hackathonApi;
    private Realm realm;

    public MutableLiveData<ArrayList<HeatMapData>> heatMapData = new MutableLiveData<>();

    public Repository() {
        hackathonApi = HackathonApiClient.getHackathonApiInterface();
        //realm = Realm.getDefaultInstance();
    }

    public void retrofitGetObject(){

        hackathonApi.getObject().enqueue(new Callback<ObjResponse>() {
            @Override
            public void onResponse(Call<ObjResponse> call, Response<ObjResponse> response) {
                ArrayList<HeatMapData> value = heatMapData.getValue();
                if (value == null) {
                    value = new ArrayList<>();
                }

                ObjResponse data = response.body();
                if (data != null) {
                    GenericModelNameHere obj = new GenericModelNameHere(data);
                    value.add(obj);
                }

                heatMapData.postValue(value);
            }

            @Override public void onFailure(Call<ObjResponse> call, Throwable t) {}
        });

    }

    public void testOnGetDataSuccessfulNerds() {
        ArrayList<HeatMapData> data = new ArrayList<>();

        data.add(new HeatMapData() {
            @Override
            public int getId() {
                return -1;
            }

            @NonNull
            @Override
            public ArrayList<LatLng> getPoints() {
                ArrayList<LatLng> points = new ArrayList<>();
                points.add(new LatLng(-37.1886, 145.708));
                points.add(new LatLng(-37.8361, 144.845));
                points.add(new LatLng(-36.9672, 144.192));

                return points;
            }

            @NonNull
            @Override
            public ArrayList<WeightedLatLng> getWeightedPoints() {
                ArrayList<WeightedLatLng> points = new ArrayList<WeightedLatLng>();
                points.add(new WeightedLatLng(new LatLng(-37.1886, 145.708), 1.0));
                points.add(new WeightedLatLng(new LatLng(-37.8361, 144.845), 0.5));
                points.add(new WeightedLatLng(new LatLng(-36.9672, 144.192), 0.1));

                return points;
            }

            @NonNull
            @Override
            public String getFilterName() {
                return "FilterNameTest";
            }

            @NonNull
            @Override
            public String getType() {
                return "TypeTest";
            }
        });

        heatMapData.postValue(data);
    }
}
