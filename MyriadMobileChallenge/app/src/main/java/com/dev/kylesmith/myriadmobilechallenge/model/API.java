package com.dev.kylesmith.myriadmobilechallenge.model;

import java.util.List;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by kylesmith on 3/24/15.
 */
public interface API {
    @GET("/kingdoms")
    void getKingdoms(Callback<List<Kingdom>> callback);

    @GET("/kingdoms/{id}")
    void getQuests(@Path("id") int id, Callback<Kingdom> callback);

    @POST("/subscribe")
    void subscribe(@Body User user, Callback<SubscriptionResponse> callback);
}
