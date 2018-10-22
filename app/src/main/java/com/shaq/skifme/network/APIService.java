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
      "Cookie:PLAY_SESSION=84dd6e49a3bb529faad5f262643ee167d922b681-lang_code=ru&sessionid=1d9287f9-0e50-4bd5-97b0-ef87c318db42"})
      @POST("tracks")
      Call<List<TracksResponseModel>> getTracksPost(@Body PostTracksBody postTracksBody);


}
