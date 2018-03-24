package com.theideallab.clthackathon2018.repository.retrofit.response.obj;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Alex Pritchard on 3/24/18.
 */

public class DataPoint {

    @SerializedName("pk")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("latitude")
    private String latitude;

    @SerializedName("longitude")
    private String longitude;

    @SerializedName("obj_type")
    private String type;

    @SerializedName("value")
    private String valueObject;

    @SerializedName("extra")
    private String extra;



    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

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

    public String getType() {
        return type;
    }

    public String getValueObject() {
        return valueObject;
    }

    public String getExtra() {
        return extra;
    }
}
