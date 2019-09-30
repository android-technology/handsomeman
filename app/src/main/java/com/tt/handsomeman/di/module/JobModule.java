package com.tt.handsomeman.di.module;

import com.tt.handsomeman.service.JobService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class JobModule {

    @Provides
    @Singleton
    JobService provideJobService(Retrofit retrofit) {
        return retrofit.create(JobService.class);
    }
}
