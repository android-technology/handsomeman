package com.tt.handsomeman.ui;

import androidx.fragment.app.FragmentActivity;
import androidx.viewbinding.ViewBinding;

import com.tt.handsomeman.BuildConfig;
import com.tt.handsomeman.viewmodel.BaseViewModel;

public abstract class BaseFragmentActivity<T extends BaseViewModel, Tx extends ViewBinding> extends FragmentActivity {
    protected T baseViewModel;
    public Tx viewBinding;

    @Override
    public void onDestroy() {
        viewBinding = null;
        baseViewModel.clearSubscriptions(this.getClass().getName().replace(BuildConfig.APPLICATION_ID,""));
        super.onDestroy();
    }
}
