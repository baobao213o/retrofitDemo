package com.example.admin;

import android.app.Application;
import android.content.Context;


public class App extends Application {

    private static Context mAppContext = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = getApplicationContext();
    }

    public static Context getmAppContext() {
        return mAppContext;
    }
}
