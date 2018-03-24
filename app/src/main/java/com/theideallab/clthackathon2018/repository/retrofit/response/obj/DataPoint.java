package com.theideallab.clthackathon2018.repository.retrofit.response.obj;

import com.google.gson.annotations.SerializedName;
import com.theideallab.clthackathon2018.model.Coordinate;

import java.util.ArrayList;

/**


 {
 "pk": 2633,
 "name": "Presbyterian Hospital",
 "latitude": null,
 "longitude": null,
 "location": [
     {
     "latitude": -80.824491,
     "longitude": 35.211834
     }
 ],
 "obj_type": "Hospital",
 "sub_type": null,
 "value": null,
 "extra": "28204"
 }


 */

public class DataPoint {

    @SerializedName("pk")
    private int id;

    @SerializedName("name")
    private String name;

    /*
    @SerializedName("latitude")
    private String latitude;

    @SerializedName("longitude")
    private String longitude;
    */

    @SerializedName("obj_type")
    private String type;

    @SerializedName("value")
    private String valueObject;

    @SerializedName("extra")
    private String extra;

    @SerializedName("location")
    private ArrayList<Point> points;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public ArrayList<Coordinate> getPoints(){
        ArrayList<Coordinate> coordinates = new ArrayList<>();

        if (points != null) {
            for(Point point : points) {
                coordinates.add(point.getCoordinte());
            }
        }

        return coordinates;
    }
}
