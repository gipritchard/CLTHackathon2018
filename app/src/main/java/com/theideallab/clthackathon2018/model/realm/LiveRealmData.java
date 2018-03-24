package com.theideallab.clthackathon2018.model.realm;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import io.realm.RealmChangeListener;
import io.realm.RealmModel;
import io.realm.RealmObject;

public class LiveRealmData<T extends RealmObject> extends LiveData<T> {

    private RealmObject result;

    public LiveRealmData(@NonNull RealmObject realmResults) {
        result = realmResults;
    }

    private final RealmChangeListener<T> listener = t -> setValue(t);

    @Override
    protected void onActive() {
        result.addChangeListener(listener);
    }

    @Override
    protected void onInactive() {
        result.removeChangeListener(listener);
    }
}