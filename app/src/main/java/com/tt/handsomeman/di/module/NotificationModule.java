package com.tt.handsomeman.di.module;

import com.tt.handsomeman.service.NotificationService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class NotificationModule {
    @Provides
    @Singleton
    NotificationService provideNotificationService(Retrofit retrofit) {
        return retrofit.create(NotificationService.class);
    }
}
