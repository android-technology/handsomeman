package com.tt.handsomeman.di.module

import com.tt.handsomeman.service.SignUpAddPayoutService

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class SignUpAddPayoutModule {
    @Provides
    @Singleton
    internal fun provideSignUpAddPayoutService(retrofit: Retrofit): SignUpAddPayoutService {
        return retrofit.create(SignUpAddPayoutService::class.java!!)
    }
}
