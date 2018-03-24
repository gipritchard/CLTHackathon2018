package com.theideallab.clthackathon2018.repository;


import android.arch.lifecycle.MutableLiveData;
import android.graphics.Color;
import android.support.annotation.NonNull;

import com.google.maps.android.heatmaps.Gradient;
import com.theideallab.clthackathon2018.application.heatmap.HeatMapData;
import com.theideallab.clthackathon2018.model.HeatMapDataImpl;
import com.theideallab.clthackathon2018.model.preferences.SharedPreferencesHelper;
import com.theideallab.clthackathon2018.repository.retrofit.HackathonApi;
import com.theideallab.clthackathon2018.repository.retrofit.HackathonApiClient;
import com.theideallab.clthackathon2018.repository.retrofit.response.obj.ObjResponse;

import java.util.ArrayList;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository implements Callback<ObjResponse> {

    private HackathonApi hackathonApi;
    private Realm realm;

    public MutableLiveData<ArrayList<HeatMapData>> heatMapData = new MutableLiveData<>();

    public Repository() {
        hackathonApi = HackathonApiClient.getHackathonApiInterface();
        realm = Realm.getDefaultInstance();
    }

    public void retrofitGetObject(String type){
        hackathonApi.getObject(1000, type).enqueue(this);
    }

    public void launchGetRequestBasedOffOfSharedPreferences(@NonNull SharedPreferencesHelper helper){

        heatMapData.setValue(new ArrayList<>());

        boolean[] filterOptionsChecked = new boolean[]{
                helper.getPrice(),
                helper.getSchools(),
                helper.getHospitals(),
                helper.getParks(),
                helper.getGreenways()
        };

        for (int i = 0; i < filterOptionsChecked.length; i++) {

            boolean checked = filterOptionsChecked[i];
            if (checked) {
                switch (i) {

                    case 0: {
                        retrofitGetObject("Price");
                    }
                    break;

                    case 1: {
                        retrofitGetObject("School");
                    }
                    break;

                    case 2: {
                        retrofitGetObject("Hospital");
                    }
                    break;

                    case 3: {
                        retrofitGetObject("Parks");
                    }
                    break;

                    case 4: {
                        retrofitGetObject("Greenway");
                    }
                    break;

                    default: {
                    }
                    break;
                }
            }
        }
    }


    @Override
    public void onResponse(Call<ObjResponse> call, Response<ObjResponse> response) {
        ArrayList<HeatMapData> value = heatMapData.getValue();
        if (value == null) {
            value = new ArrayList<>();
        }

        Gradient gradient = null;

        String type = call.request().url().queryParameter("obj_type");
        if(type != null && !type.equals("Price")) {

            int[] colors = {
                    Color.argb(100, 0, 0, 255), //#00f0ff
                    Color.argb(100,0, 225, 225)
            };

            float[] startPoints = {
                    0.2f, 1f
            };

            gradient = new Gradient(colors,startPoints);
        }


        ObjResponse data = response.body();
        if (data != null) {
            HeatMapDataImpl obj = new HeatMapDataImpl(data, gradient);
            value.add(obj);

            /*
            String urlPage = data.getNextUrl();
            if (urlPage != null) {
                hackathonApi.getNextPage(urlPage).enqueue(this);
            }
            */
        }

        heatMapData.postValue(value);
    }

    @Override public void onFailure(Call<ObjResponse> call, Throwable t) {}
}
