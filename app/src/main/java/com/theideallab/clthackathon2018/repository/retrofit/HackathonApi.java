package com.theideallab.clthackathon2018.repository.retrofit;


import com.theideallab.clthackathon2018.repository.retrofit.response.obj.ObjResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HackathonApi {

    @GET("obj")
    Call<ObjResponse> getObject();

}
