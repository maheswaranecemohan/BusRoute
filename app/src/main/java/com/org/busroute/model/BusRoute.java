package com.org.busroute.model;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * This  is BusRoute
 */
public class BusRoute implements Parcelable {

    @SerializedName("routes")
    @Expose
    private List<Route> routes = null;

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.routes);
    }

    public BusRoute() {
    }

    protected BusRoute(Parcel in) {
        this.routes = new ArrayList<Route>();
        in.readList(this.routes, Route.class.getClassLoader());
    }

    public static final Parcelable.Creator<BusRoute> CREATOR = new Parcelable.Creator<BusRoute>() {
        @Override
        public BusRoute createFromParcel(Parcel source) {
            return new BusRoute(source);
        }

        @Override
        public BusRoute[] newArray(int size) {
            return new BusRoute[size];
        }
    };
}
