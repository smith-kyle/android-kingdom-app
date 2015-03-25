package com.dev.kylesmith.myriadmobilechallenge.model;

import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by kylesmith on 3/24/15.
 */
public class RestClient {
    private static API REST_CLIENT;
    private static String baseURL = "https://challenge2015.myriadapps.com/api/v1";

    static{
        initRestClient();
    }

    public static API get(){
        return REST_CLIENT;
    }


    private static void initRestClient(){
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(baseURL).setClient(new OkClient(new OkHttpClient())).setLogLevel(RestAdapter.LogLevel.FULL).build();
        REST_CLIENT = restAdapter.create(API.class);
    }
}
