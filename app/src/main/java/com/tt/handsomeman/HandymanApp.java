package com.tt.handsomeman;

import android.app.Application;

import com.google.gson.Gson;
import com.tt.handsomeman.di.component.AppComponent;
import com.tt.handsomeman.di.component.DaggerAppComponent;
import com.tt.handsomeman.di.module.AppModule;
import com.tt.handsomeman.di.module.JobModule;
import com.tt.handsomeman.di.module.NetworkModule;
import com.tt.handsomeman.di.module.UserModule;

public class HandymanApp extends Application {

    private static AppComponent component;

    private static HandymanApp mSelf;
    private Gson mGSon;

    public static HandymanApp getInstance() {
        return mSelf;
    }

    public static AppComponent getComponent() {
        return component;
    }

    public Gson getGSon() {
        return mGSon;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mSelf = this;
        mGSon = new Gson();

        component = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .jobModule(new JobModule())
                .userModule(new UserModule())
                .build();
    }
}
