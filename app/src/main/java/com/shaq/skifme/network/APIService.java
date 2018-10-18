package com.shaq.skifme.network;



import com.shaq.skifme.data.AuthPost;
import com.shaq.skifme.data.AuthSaltResponse;
import com.shaq.skifme.data.LoginBody;
import com.shaq.skifme.data.RegisterBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIService {

    @Headers("Content-type: application/json")
    @POST("sessions/salt")
    Call<AuthSaltResponse> savePost(@Body AuthPost post);
//
//    @Headers("Content-type: application/json")
//    @POST("sessions")
//    Call<Void> login(@Body LoginParams loginParams);
//
//    @Headers("Content-type: application/json")
//    @GET("sessions")
//    Call<Sessions> getSessions();

      @Headers("Content-type: application/json")
      @POST("login")
      Call<Void> loginSubmit(@Body LoginBody loginBody);

      @Headers("Content-type: application/json")
      @POST("registrate")
      Call<Void> registrationSubmit(@Body RegisterBody registerBody);


}
