package com.tt.handsomeman.di.module;

import com.tt.handsomeman.service.SignUpAddPayoutService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class SignUpAddPayoutModule {
    @Provides
    @Singleton
    SignUpAddPayoutService provideSignUpAddPayoutService(Retrofit retrofit) {
        return retrofit.create(SignUpAddPayoutService.class);
    }
}
