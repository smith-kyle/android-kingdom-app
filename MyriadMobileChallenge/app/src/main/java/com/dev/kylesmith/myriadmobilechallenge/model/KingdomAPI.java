package com.dev.kylesmith.myriadmobilechallenge.model;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by kylesmith on 3/24/15.
 */
public interface KingdomAPI {
    @GET("/kingdoms")
    void getKingdoms(Callback<List<Kingdom>> callback);
}
