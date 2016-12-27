package com.example.admin;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by Admin on 2016/12/16.
 */

public class App extends Application {
    //内存泄漏检测
    private RefWatcher refWatcher;

    public static Context mAppContext = null;
    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = getApplicationContext();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
        refWatcher=LeakCanary.install(this);
    }

    public static RefWatcher getRefWatcher(Context context) {
        App application = (App) context.getApplicationContext();
        return application.refWatcher;
    }

    public static Context getmAppContext() {
        return mAppContext;
    }
}
