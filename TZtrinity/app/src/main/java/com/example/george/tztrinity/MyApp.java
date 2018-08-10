package com.example.george.tztrinity;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by George on 10.08.2018.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this); // Инициализация базы данных

    }
}
