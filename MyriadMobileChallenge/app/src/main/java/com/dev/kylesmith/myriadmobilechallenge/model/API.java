package com.dev.kylesmith.myriadmobilechallenge.model;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
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
}
