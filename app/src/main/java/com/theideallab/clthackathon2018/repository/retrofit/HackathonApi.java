package com.theideallab.clthackathon2018.repository.retrofit;


import com.theideallab.clthackathon2018.repository.retrofit.response.obj.ObjResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HackathonApi {

    @GET("obj")
    Call<ObjResponse> getObject(@Query(value = "limit") int limit);

}
