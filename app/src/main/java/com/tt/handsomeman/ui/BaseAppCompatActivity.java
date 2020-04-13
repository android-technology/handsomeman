package com.tt.handsomeman.ui;

import androidx.appcompat.app.AppCompatActivity;

import com.tt.handsomeman.BuildConfig;
import com.tt.handsomeman.viewmodel.BaseViewModel;

public abstract class BaseAppCompatActivity<T extends BaseViewModel> extends AppCompatActivity {
    protected T baseViewModel;

    @Override
    protected void onDestroy() {
        baseViewModel.clearSubscriptions(this.getClass().getName().replace(BuildConfig.APPLICATION_ID,""));
        super.onDestroy();
    }
}
