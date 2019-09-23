package com.tt.handsomeman.di.component;


import com.tt.handsomeman.di.module.AppModule;
import com.tt.handsomeman.di.module.LoginModule;
import com.tt.handsomeman.di.module.NetworkModule;
import com.tt.handsomeman.di.module.StartScreenModule;
import com.tt.handsomeman.ui.Login;
import com.tt.handsomeman.ui.jobs.JobsChildFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        StartScreenModule.class,
        LoginModule.class,
        NetworkModule.class})
public interface AppComponent {

    void inject(JobsChildFragment fragment);

    void inject(Login login);
}
