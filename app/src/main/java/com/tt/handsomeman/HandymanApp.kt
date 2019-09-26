package com.tt.handsomeman

import android.app.Application

import com.google.gson.Gson
import com.tt.handsomeman.di.component.AppComponent
import com.tt.handsomeman.di.component.DaggerAppComponent
import com.tt.handsomeman.di.module.AppModule
import com.tt.handsomeman.di.module.LoginModule
import com.tt.handsomeman.di.module.NetworkModule
import com.tt.handsomeman.di.module.SignUpAddPayoutModule
import com.tt.handsomeman.di.module.SignUpModule
import com.tt.handsomeman.di.module.StartScreenModule

class HandymanApp : Application() {
    var gSon: Gson? = null
        private set

    override fun onCreate() {
        super.onCreate()

        instance = this
        gSon = Gson()

        component = DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule())
                .startScreenModule(StartScreenModule())
                .loginModule(LoginModule())
                .signUpModule(SignUpModule())
                .signUpAddPayoutModule(SignUpAddPayoutModule())
                .build()
    }

    companion object {

        var component: AppComponent? = null
            private set

        var instance: HandymanApp? = null
            private set
    }
}
