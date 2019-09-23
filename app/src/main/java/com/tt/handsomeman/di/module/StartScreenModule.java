package com.tt.handsomeman.di.module;

import com.tt.handsomeman.service.StartScreenService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class StartScreenModule {

    @Provides
    @Singleton
    StartScreenService provideStartScreenService(Retrofit retrofit) {
        return retrofit.create(StartScreenService.class);
    }
}
