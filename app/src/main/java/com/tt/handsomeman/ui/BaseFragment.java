package com.tt.handsomeman.ui;

import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import com.tt.handsomeman.BuildConfig;
import com.tt.handsomeman.viewmodel.BaseViewModel;

public abstract class BaseFragment<T extends BaseViewModel, Tx extends ViewBinding> extends Fragment {
    protected T baseViewModel;
    protected Tx viewBinding;

    @Override
    public void onDestroy() {
        baseViewModel.clearSubscriptions(this.getClass().getName().replace(BuildConfig.APPLICATION_ID, ""));
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        viewBinding = null;
        super.onDestroyView();
    }
}
