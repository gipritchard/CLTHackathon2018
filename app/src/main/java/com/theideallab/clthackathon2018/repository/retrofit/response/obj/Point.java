package com.theideallab.clthackathon2018.repository.retrofit.response.obj;

import com.google.gson.annotations.SerializedName;
import com.theideallab.clthackathon2018.model.Coordinate;

/**
 * Created by Alex Pritchard on 3/24/18.
 */



public class Point {

    @SerializedName("latitude")
    private String latitude;

    @SerializedName("longitude")
    private String longitude;


    public double getLatitude() {
        double lat;

        try {
            lat = Double.parseDouble(latitude);
        }
        catch (Exception e){ lat = 0; }

        return lat;
    }

    public double getLongitude() {
        double longitude;

        try {
            longitude = Double.parseDouble(this.longitude);
        }
        catch (Exception e){ longitude = 0; }

        return longitude;
    }

    public Coordinate getCoordinte(){
        return new Coordinate(getLatitude(), getLongitude());
    }

}
