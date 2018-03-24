package com.theideallab.clthackathon2018.repository.retrofit.response.obj;

import com.google.gson.annotations.SerializedName;
import com.theideallab.clthackathon2018.model.Coordinate;

import java.util.ArrayList;

public class ObjResponse {

    @SerializedName("count")
    private int count;

    @SerializedName("next")
    private String nextUrl;

    @SerializedName("previous")
    private String previousUrl;

    @SerializedName("results")
    private ArrayList<DataPoint> results;


    public int getCount() {
        return count;
    }

    public String getNextUrl() {
        return nextUrl;
    }

    public String getPreviousUrl() {
        return previousUrl;
    }

    public ArrayList<DataPoint> getResults() {
        return results;
    }

    public ArrayList<Coordinate> getAllCoordinates(){
        ArrayList<Coordinate> coordinates = new ArrayList<>();

        for(DataPoint entry : results) {
            coordinates.add(entry.getCoordinate());
        }

        return coordinates;
    }
}
