package com.theideallab.clthackathon2018.repository;


import com.theideallab.clthackathon2018.repository.retrofit.HackathonApi;
import com.theideallab.clthackathon2018.repository.retrofit.HackathonApiClient;

import io.realm.Realm;

public class Repository {

    private HackathonApi hackathonApi;
    private Realm realm;

    public Repository() {
        hackathonApi = HackathonApiClient.getHackathonApiInterface();
        realm = Realm.getDefaultInstance();
    }


}
