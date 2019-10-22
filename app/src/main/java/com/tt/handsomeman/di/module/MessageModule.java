package com.tt.handsomeman.di.module;

import com.tt.handsomeman.service.MessageService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MessageModule {

    @Provides
    @Singleton
    MessageService provideMessageService(Retrofit retrofit) {
        return retrofit.create(MessageService.class);
    }
}
