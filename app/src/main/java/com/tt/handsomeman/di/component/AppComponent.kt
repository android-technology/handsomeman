package com.tt.handsomeman.di.component


import com.tt.handsomeman.di.module.AppModule
import com.tt.handsomeman.di.module.LoginModule
import com.tt.handsomeman.di.module.NetworkModule
import com.tt.handsomeman.di.module.SignUpAddPayoutModule
import com.tt.handsomeman.di.module.SignUpModule
import com.tt.handsomeman.di.module.StartScreenModule
import com.tt.handsomeman.ui.Login
import com.tt.handsomeman.ui.OnBoardingSlidePagerActivity
import com.tt.handsomeman.ui.Register
import com.tt.handsomeman.ui.SignUp
import com.tt.handsomeman.ui.SignUpAddPayout
import com.tt.handsomeman.ui.Start
import com.tt.handsomeman.ui.jobs.JobsChildFragment

import javax.inject.Singleton

import dagger.Component

@Singleton
@Component(modules = [AppModule::class, StartScreenModule::class, LoginModule::class, SignUpModule::class, SignUpAddPayoutModule::class, NetworkModule::class])
interface AppComponent {

    fun inject(fragment: JobsChildFragment)

    fun inject(login: Login)

    fun inject(signUp: SignUp)

    fun inject(start: Start)

    fun inject(activity: OnBoardingSlidePagerActivity)

    fun inject(register: Register)

    fun inject(signUpAddPayout: SignUpAddPayout)
}
