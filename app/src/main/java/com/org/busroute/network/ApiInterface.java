package com.org.busroute.network;
import com.org.busroute.model.BusRoute;

import retrofit2.http.GET;
import rx.Observable;
/**
 * This  is ApiInterface
 */
public interface ApiInterface {

    @GET("v2/5808f00d10000005074c6340")
    Observable<BusRoute>  getRoutes();

}
