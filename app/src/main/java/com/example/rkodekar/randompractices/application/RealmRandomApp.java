package com.example.rkodekar.randompractices.application;

import android.app.Application;
import android.content.Context;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by rkodekar on 9/28/16.
 */
public class RealmRandomApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .name("sample.realm")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);


    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public Context getBaseContext() {
        return super.getBaseContext();
    }
}
