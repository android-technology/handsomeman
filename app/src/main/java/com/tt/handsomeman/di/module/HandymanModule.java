package com.tt.handsomeman.di.module;

import com.tt.handsomeman.service.HandymanService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class HandymanModule {
    @Provides
    @Singleton
    HandymanService provideHandymanService(Retrofit retrofit) {
        return retrofit.create(HandymanService.class);
    }
}
