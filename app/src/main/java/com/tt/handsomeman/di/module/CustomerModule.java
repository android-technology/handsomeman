package com.tt.handsomeman.di.module;

import com.tt.handsomeman.service.CustomerService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class CustomerModule {
    @Provides
    @Singleton
    CustomerService provideCustomerService(Retrofit retrofit) {
        return retrofit.create(CustomerService.class);
    }
}
