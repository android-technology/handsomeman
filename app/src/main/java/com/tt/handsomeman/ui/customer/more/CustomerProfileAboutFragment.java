package com.tt.handsomeman.ui.customer.more;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.tt.handsomeman.HandymanApp;
import com.tt.handsomeman.databinding.FragmentCustomerProfileAboutBinding;
import com.tt.handsomeman.model.Customer;
import com.tt.handsomeman.response.CustomerProfileResponse;
import com.tt.handsomeman.ui.BaseFragment;
import com.tt.handsomeman.ui.MyProfile;
import com.tt.handsomeman.util.SharedPreferencesUtils;
import com.tt.handsomeman.viewmodel.CustomerViewModel;

import javax.inject.Inject;

public class CustomerProfileAboutFragment extends BaseFragment<CustomerViewModel, FragmentCustomerProfileAboutBinding> {

    private static final Integer REQUEST_MY_PROFILE_RESULT_CODE = 77;

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    SharedPreferencesUtils sharedPreferencesUtils;
    private TextView yourName, allProjects, successedProject;
    private boolean isMyProfileEdit = false;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        HandymanApp.getComponent().inject(this);
        baseViewModel = new ViewModelProvider(this, viewModelFactory).get(CustomerViewModel.class);
        viewBinding = FragmentCustomerProfileAboutBinding.inflate(inflater, container, false);
        return viewBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        yourName = viewBinding.aboutYourName;
        allProjects = viewBinding.aboutAllProjects;
        successedProject = viewBinding.aboutSuccessedProjects;

        fetchCustomerProfile();
    }

    private void fetchCustomerProfile() {
        String authorizationCode = sharedPreferencesUtils.get("token", String.class);
        baseViewModel.fetchCustomerProfile(authorizationCode);
        baseViewModel.getCustomerProfileResponseMutableLiveData().observe(getViewLifecycleOwner(), new Observer<CustomerProfileResponse>() {
            @Override
            public void onChanged(CustomerProfileResponse customerProfileResponse) {
                Customer customer = customerProfileResponse.getCustomer();

                yourName.setText(customer.getName());
                allProjects.setText(String.valueOf(customerProfileResponse.getAllProject()));
                successedProject.setText(String.valueOf(customerProfileResponse.getSuccessedProject()));
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode,
                                 int resultCode,
                                 @Nullable Intent data) {

        if (requestCode == REQUEST_MY_PROFILE_RESULT_CODE && resultCode == Activity.RESULT_OK && data != null) {
            if (data.getBooleanExtra("isMyProfileEdit", false)) {
                fetchCustomerProfile();
                isMyProfileEdit = true;
                MyProfile myProfile = (MyProfile) getActivity();
                myProfile.setMyProfileEditResult(isMyProfileEdit);
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
