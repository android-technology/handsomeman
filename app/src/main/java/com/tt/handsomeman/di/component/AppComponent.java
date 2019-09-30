package com.tt.handsomeman.di.component;


import com.tt.handsomeman.di.module.AppModule;
import com.tt.handsomeman.di.module.JobModule;
import com.tt.handsomeman.di.module.LoginModule;
import com.tt.handsomeman.di.module.NetworkModule;
import com.tt.handsomeman.di.module.SignUpAddPayoutModule;
import com.tt.handsomeman.di.module.SignUpModule;
import com.tt.handsomeman.di.module.ViewModelModule;
import com.tt.handsomeman.ui.Login;
import com.tt.handsomeman.ui.OnBoardingSlidePagerActivity;
import com.tt.handsomeman.ui.Register;
import com.tt.handsomeman.ui.SignUp;
import com.tt.handsomeman.ui.SignUpAddPayout;
import com.tt.handsomeman.ui.Start;
import com.tt.handsomeman.ui.YourLocation;
import com.tt.handsomeman.ui.jobs.JobsChildFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        JobModule.class,
        LoginModule.class,
        SignUpModule.class,
        SignUpAddPayoutModule.class,
        NetworkModule.class,
        ViewModelModule.class})
public interface AppComponent {

    void inject(JobsChildFragment fragment);

    void inject(Login login);

    void inject(SignUp signUp);

    void inject(Start start);

    void inject(OnBoardingSlidePagerActivity activity);

    void inject(Register register);

    void inject(SignUpAddPayout signUpAddPayout);

    void inject(YourLocation yourLocation);
}
