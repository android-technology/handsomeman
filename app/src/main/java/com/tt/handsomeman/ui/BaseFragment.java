package com.tt.handsomeman.ui;

import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import com.tt.handsomeman.viewmodel.BaseViewModel;

public abstract class BaseFragment<T extends BaseViewModel, Tx extends ViewBinding> extends Fragment {
    protected T baseViewModel;
    protected Tx viewBinding;

    @Override
    public void onStop() {
        baseViewModel.clearSubscriptions();
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        viewBinding = null;
        super.onDestroyView();
    }
}
