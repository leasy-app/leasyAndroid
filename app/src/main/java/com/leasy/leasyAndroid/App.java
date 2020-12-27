package com.leasy.leasyAndroid;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import java.lang.ref.WeakReference;

public class App extends Application {
    private static WeakReference<Context> contextRef;
    @Override
    public void onCreate() {
        super.onCreate();
        contextRef = new WeakReference<>(this);
    }
    public static Context ctx(){
        return contextRef.get();
    }
    public static String username(){
        SharedPreferences sharedPreferences = ctx().getSharedPreferences("user_leasy", Context.MODE_PRIVATE);
        return sharedPreferences.getString("username", "");
    }
}
