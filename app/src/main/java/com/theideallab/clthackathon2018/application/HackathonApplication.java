package com.theideallab.clthackathon2018.application;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;

import io.fabric.sdk.android.Fabric;
import io.realm.Realm;

/**
 * Created by Alex Pritchard on 3/23/18.
 */

public class HackathonApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Fabric.with(this, new Crashlytics(), new Answers());
        Realm.init(this);
    }
}
