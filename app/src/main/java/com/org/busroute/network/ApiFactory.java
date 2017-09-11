package com.org.busroute.network;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

import com.org.busroute.model.BusRoute;
import com.org.busroute.network.ApiInterface;

/**
 * Created by Maheswaran on 9/11/2017.
 */

public class ApiFactory {
    private static final String BASE_URL = "http://www.mocky.io/";
    private static ApiClient instance;
    /*return APIInterface based on the APItype*/
    public static ApiInterface  getApi(String apiType){
        if(apiType.equalsIgnoreCase("ROUTELIST")){
            final Gson gson =
                    new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
            final Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            ApiInterface gitHubService = retrofit.create(ApiInterface.class);
            return gitHubService;
        }
        return null;
    }
}
