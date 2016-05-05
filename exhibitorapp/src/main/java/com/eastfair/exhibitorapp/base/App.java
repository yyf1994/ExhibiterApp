package com.eastfair.exhibitorapp.base;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;

import com.aspsine.swipetoloadlayout.BuildConfig;

/**
 * Created by Aspsine on 2015/9/2.
 */
public class App extends Application {

//    private static RequestQueue sRequestQueue;

    private static Context sContext;
    private static App sInstance;


    @Override
    public void onCreate() {
        super.onCreate();
        setStrictMode();
        sInstance = this;
        sContext = getApplicationContext();
    }

    public static App getInstance() {
        return sInstance;
    }


    private void setStrictMode() {
        if (BuildConfig.DEBUG && Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            StrictMode.enableDefaults();
        }
    }

//    public static RequestQueue getRequestQueue() {
//        if (sRequestQueue == null) {
//            sRequestQueue = Volley.newRequestQueue(sContext);
//        }
//        return sRequestQueue;
//    }
}
