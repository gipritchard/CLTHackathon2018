package com.theideallab.clthackathon2018.application.advancedfilter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.theideallab.clthackathon2018.R;

/**
 * Created by Alex Pritchard on 3/24/18.
 */

public class AdvancedFilterActivity extends AppCompatActivity {

    public static Intent createIntent(@NonNull Context ctx) {
        return new Intent(ctx, AdvancedFilterActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
