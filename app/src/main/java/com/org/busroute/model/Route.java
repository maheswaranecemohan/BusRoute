
package com.org.busroute.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * This  is Route
 */
public class Route implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("stops")
    @Expose
    private List<Stop> stops = null;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("accessible")
    @Expose
    private String accessible;
    @SerializedName("image")
    @Expose
    private String image;

    public Route(String id, String name, List<Stop> stops, String description, String accessible, String image) {
        this.id = id;
        this.name = name;
        this.stops = stops;
        this.description = description;
        this.accessible = accessible;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Stop> getStops() {
        return stops;
    }

    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAccessible() {
        return accessible;
    }

    public void setAccessible(String accessible) {
        this.accessible = accessible;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeList(this.stops);
        dest.writeString(this.description);
        dest.writeString(this.accessible);
        dest.writeString(this.image);
    }



    public Route(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.stops = new ArrayList<Stop>();
        in.readList(this.stops, Stop.class.getClassLoader());
        this.description = in.readString();
        this.accessible = in.readString();
        this.image = in.readString();
    }

    public static final Parcelable.Creator<Route> CREATOR = new Parcelable.Creator<Route>() {
        @Override
        public Route createFromParcel(Parcel source) {
            return new Route(source);
        }

        @Override
        public Route[] newArray(int size) {
            return new Route[size];
        }
    };
}
