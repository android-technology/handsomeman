package com.tt.handsomeman.di.module

import com.tt.handsomeman.service.LoginService

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class LoginModule {

    @Provides
    @Singleton
    internal fun provideLoginService(retrofit: Retrofit): LoginService {
        return retrofit.create(LoginService::class.java!!)
    }
}
