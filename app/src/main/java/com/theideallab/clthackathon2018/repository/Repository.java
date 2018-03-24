package com.theideallab.clthackathon2018.repository;


import android.arch.lifecycle.MutableLiveData;

import com.theideallab.clthackathon2018.application.heatmap.HeatMapData;
import com.theideallab.clthackathon2018.model.HeatMapDataImpl;
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

    public void retrofitGetObject(){
        hackathonApi.getObject(1000).enqueue(this);
    }

    @Override
    public void onResponse(Call<ObjResponse> call, Response<ObjResponse> response) {
        ArrayList<HeatMapData> value = heatMapData.getValue();
        if (value == null) {
            value = new ArrayList<>();
        }

        ObjResponse data = response.body();
        if (data != null) {
            HeatMapDataImpl obj = new HeatMapDataImpl(data);
            value.add(obj);

            String urlPage = data.getNextUrl();
            if (urlPage != null) {
                //hackathonApi.getNextPage(urlPage).enqueue(this);
            }
        }

        heatMapData.postValue(value);
    }

    @Override public void onFailure(Call<ObjResponse> call, Throwable t) {}
}
