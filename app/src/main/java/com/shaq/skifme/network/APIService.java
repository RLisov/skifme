package com.shaq.skifme.network;

import com.shaq.skifme.data.AuthPost;
import com.shaq.skifme.data.AuthSaltResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {

    @Headers("Content-type: application/json")
    @POST("sessions/salt")
    Call<AuthSaltResponse> savePost(@Body AuthPost post);

    //@POST("sessions")
    //Call<LoginResponse> login(@Body LoginParams loginParams);


}
