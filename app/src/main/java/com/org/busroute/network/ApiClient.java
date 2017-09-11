package com.org.busroute.network;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.org.busroute.model.BusRoute;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
/**
 * This  is ApiClient
 */
public class ApiClient {
    private static final String BASE_URL = "http://www.mocky.io/";
    private static ApiClient instance;
    private ApiClient() {
    }

    public static ApiClient getInstance() {
        if (instance == null) {
            instance = new ApiClient();
        }
        return instance;
    }

    public Observable<BusRoute> getRoutes() {
        ApiInterface gitHubService = ApiFactory.getApi("ROUTELIST");
        return gitHubService.getRoutes();
    }
}
