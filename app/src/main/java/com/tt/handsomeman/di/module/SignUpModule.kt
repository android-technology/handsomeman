package com.tt.handsomeman.di.module

import com.tt.handsomeman.service.SignUpService

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class SignUpModule {

    @Provides
    @Singleton
    internal fun provideSignUpService(retrofit: Retrofit): SignUpService {
        return retrofit.create(SignUpService::class.java!!)
    }
}
