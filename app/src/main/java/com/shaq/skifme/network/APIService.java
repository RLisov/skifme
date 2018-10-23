package com.shaq.skifme.network;




import com.shaq.skifme.data.LoginBody;

import com.shaq.skifme.data.RegisterBody;
import com.shaq.skifme.data.Tracks.Send.PostTracksBody;
import com.shaq.skifme.data.Tracks.Response.TracksResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {

//    @Headers("Content-type: application/json")
//    @POST("sessions/salt")
//    Call<AuthSaltResponse> savePost(@Body AuthPost post);
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

      @Headers({"Content-type: application/json",
      "Cookie:PLAY_SESSION=f279cf2a33c0c80b5cc91d02d848f12f61d4a6fd-lang_code=ru&sessionid=ecdc72f0-8690-46fa-9f38-e6cca7409b36"})
      @POST("tracks")
      Call<List<TracksResponseModel>> getTracksPost(@Body PostTracksBody postTracksBody);


}
