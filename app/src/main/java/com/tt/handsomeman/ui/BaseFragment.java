package com.tt.handsomeman.ui;

import androidx.fragment.app.Fragment;

import com.tt.handsomeman.viewmodel.BaseViewModel;

public abstract class BaseFragment<T extends BaseViewModel> extends Fragment {
    protected T baseViewModel;

    @Override
    public void onStop() {
        baseViewModel.clearSubscriptions();
        super.onStop();
    }
}
