package com.shaq.skifme.network;




import com.shaq.skifme.data.LoginBody;

import com.shaq.skifme.data.RegisterBody;
import com.shaq.skifme.data.Tracks.Send.PostTracksBody;
import com.shaq.skifme.data.Tracks.Response.TracksResponseModel;
import com.shaq.skifme.data.res.GeozonesRes;
import com.shaq.skifme.data.res.UserInfoMe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;



public interface APIService {


      @Headers("Content-type: application/json")
      @POST("login")
      Call<Void> loginSubmit(@Body LoginBody loginBody);

      @Headers("Content-type: application/json")
      @POST("registrate")
      Call<Void> registrationSubmit(@Body RegisterBody registerBody);

      @Headers("Content-type: application/json")
      @POST("tracks")
      Call<List<TracksResponseModel>> getTracksPost(@Header("Cookie") String cookie, @Body PostTracksBody postTracksBody);

      @Headers("Content-type: application/json")
      @GET("me")
      Call<UserInfoMe> getUserInfo(@Header("Cookie") String cookie);

      @Headers("Content-type: application/json")
      @GET("geozones")
      Call<GeozonesRes> getGeozonesList(@Header("Cookie") String cookie);
}
