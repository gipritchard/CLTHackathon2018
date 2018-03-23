package com.theideallab.clthackathon2018.model.realm;

import android.arch.lifecycle.LiveData;

import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.RealmChangeListener;
import io.realm.RealmModel;
import io.realm.RealmResults;

/**
 * Wrapper Class for RealmModel that connects LiveData making our Realm Objects the updating
 * datasource
 */
public class LiveRealmDataList<T extends RealmModel> extends LiveData<RealmResults<T>> {

    private RealmResults<T> results;

    public LiveRealmDataList(RealmResults<T> realmResults) {
        results = realmResults;
    }

    private final RealmChangeListener<RealmResults<T>> listener = new RealmChangeListener<RealmResults<T>>() {
        @Override
        public void onChange(RealmResults<T> ts) {
            setValue(ts);
        }
    };

    @Override
    protected void onActive() {
        results.addChangeListener(listener);
    }

    @Override
    protected void onInactive() {
        results.removeChangeListener(listener);
    }

}
