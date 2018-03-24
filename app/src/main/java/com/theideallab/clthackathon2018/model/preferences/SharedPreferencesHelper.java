package com.theideallab.clthackathon2018.model.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

/**
 * Created by Alex Pritchard on 3/24/18.
 */

public class SharedPreferencesHelper {

    private static final String TAG_PRICE = "__TAG_PRICE__";
    private static final String TAG_Schools = "__TAG_Schools__";
    private static final String TAG_Hospital = "__TAG_Hospital__";

    private SharedPreferences preferences;

    public SharedPreferencesHelper(@NonNull Context ctx) {
        preferences = ctx.getSharedPreferences("aasdf", Context.MODE_PRIVATE);
    }


    public boolean getPrice(){
        return preferences.getBoolean(TAG_PRICE, true);
    }

    public boolean getSchools(){
        return preferences.getBoolean(TAG_Schools, true);
    }

    public boolean getHospitals(){
        return preferences.getBoolean(TAG_Hospital, false);
    }

    public void setPrice(boolean b){
        preferences.edit().putBoolean(TAG_PRICE, b).apply();
    }

    public void setSchools(boolean b){
        preferences.edit().putBoolean(TAG_Schools, b).apply();
    }

    public void setHospital(boolean b){
        preferences.edit().putBoolean(TAG_Hospital, b).apply();
    }

}
