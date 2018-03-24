package com.theideallab.clthackathon2018.repository.retrofit.response.obj;

import com.google.gson.annotations.SerializedName;
import com.theideallab.clthackathon2018.model.Coordinate;

import java.util.ArrayList;

/*

{
  "count": 7,
  "next": null,
  "previous": null,
  "results": [
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
    }]}

 */


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
            coordinates.addAll(entry.getPoints());
        }

        return coordinates;
    }
}
