package com.tt.handsomeman;

import android.app.Application;

import com.google.gson.Gson;
import com.tt.handsomeman.di.component.AppComponent;
import com.tt.handsomeman.di.component.DaggerAppComponent;
import com.tt.handsomeman.di.module.AppModule;
import com.tt.handsomeman.di.module.LoginModule;
import com.tt.handsomeman.di.module.NetworkModule;
import com.tt.handsomeman.di.module.SignUpAddPayoutModule;
import com.tt.handsomeman.di.module.SignUpModule;
import com.tt.handsomeman.di.module.StartScreenModule;

public class HandymanApp extends Application {

    private static AppComponent component;

    private static HandymanApp mSelf;
    private Gson mGSon;

    public static HandymanApp getInstance() {
        return mSelf;
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
                .startScreenModule(new StartScreenModule())
                .loginModule(new LoginModule())
                .signUpModule(new SignUpModule())
                .signUpAddPayoutModule(new SignUpAddPayoutModule())
                .build();
    }

    public static AppComponent getComponent() {
        return component;
    }
}
