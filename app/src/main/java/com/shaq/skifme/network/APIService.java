package com.shaq.skifme.network;




import com.shaq.skifme.data.req.LoginBody;

import com.shaq.skifme.data.req.RegisterBody;
import com.shaq.skifme.data.req.PostTracksBody;
import com.shaq.skifme.data.res.ControlRes;
import com.shaq.skifme.data.res.TracksResponseModel;
import com.shaq.skifme.data.res.ObjectsRes;
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

//      @Headers("Content-type: application/json")
//      @GET("geozones")
//      Call<List<GeozonesRes>> getGeozonesList(@Header("Cookie") String cookie);

//      @Headers("Content-type: application/json")
//      @GET("autos")
//      Call<List<DevicesRes>> getAllDevicesList(@Header("Cookie") String cookie);

      @Headers("Content-type: application/json")
      @GET("objects")
      Call<List<ObjectsRes>> getAllObjects();

      @Headers("Content-type: application/json")
      @GET("controls")
      Call<List<ControlRes>> getAllControls();




}
