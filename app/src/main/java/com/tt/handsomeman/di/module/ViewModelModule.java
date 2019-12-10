package com.tt.handsomeman.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.tt.handsomeman.di.annotation.ViewModelKey;
import com.tt.handsomeman.viewmodel.JobsViewModel;
import com.tt.handsomeman.viewmodel.MessageViewModel;
import com.tt.handsomeman.viewmodel.MoreViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(JobsViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(JobsViewModel.class)
    abstract ViewModel bindJobsViewModel(JobsViewModel jobsViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MessageViewModel.class)
    abstract ViewModel bindMessageViewModel(MessageViewModel messageViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MoreViewModel.class)
    abstract ViewModel bindMoreViewModel(MoreViewModel moreViewModel);

    //bind new ViewModel here
}
