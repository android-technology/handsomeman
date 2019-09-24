package com.tt.handsomeman.di.module;

import com.tt.handsomeman.service.SignUpService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class SignUpModule {

    @Provides
    @Singleton
    SignUpService provideSignUpService(Retrofit retrofit) {
        return retrofit.create(SignUpService.class);
    }
}
