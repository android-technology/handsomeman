package com.tt.handsomeman.di.module

import com.tt.handsomeman.service.StartScreenService

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class StartScreenModule {

    @Provides
    @Singleton
    internal fun provideStartScreenService(retrofit: Retrofit): StartScreenService {
        return retrofit.create(StartScreenService::class.java!!)
    }
}
