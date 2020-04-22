package com.tt.handsomeman.ui.customer.more;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tt.handsomeman.HandymanApp;
import com.tt.handsomeman.R;
import com.tt.handsomeman.adapter.CustomerReviewAdapter;
import com.tt.handsomeman.databinding.FragmentCustomerProfileReviewBinding;
import com.tt.handsomeman.response.CustomerReviewProfile;
import com.tt.handsomeman.response.CustomerReviewResponse;
import com.tt.handsomeman.ui.BaseFragment;
import com.tt.handsomeman.util.CustomDividerItemDecoration;
import com.tt.handsomeman.util.SharedPreferencesUtils;
import com.tt.handsomeman.viewmodel.CustomerViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CustomerProfileReviewFragment extends BaseFragment<CustomerViewModel, FragmentCustomerProfileReviewBinding> {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    SharedPreferencesUtils sharedPreferencesUtils;
    private TextView countReviewers;
    private RatingBar rtCountPoint;
    private CustomerReviewAdapter customerReviewAdapter;
    private List<CustomerReviewResponse> customerReviewResponseList = new ArrayList<>();
    private String authorizationCode;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        HandymanApp.getComponent().inject(this);
        authorizationCode = sharedPreferencesUtils.get("token", String.class);
        baseViewModel = new ViewModelProvider(this, viewModelFactory).get(CustomerViewModel.class);
        viewBinding = FragmentCustomerProfileReviewBinding.inflate(inflater, container, false);
        return viewBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        countReviewers = viewBinding.reviewCountCustomerProfile;
        rtCountPoint = viewBinding.ratingBarCustomerProfile;

        createReviewRecyclerView();
        fetchData();
    }

    private void fetchData() {
        baseViewModel.fetchCustomerReview(authorizationCode);
        baseViewModel.getCustomerReviewProfileMutableLiveData().observe(getViewLifecycleOwner(), new Observer<CustomerReviewProfile>() {
            @Override
            public void onChanged(CustomerReviewProfile customerReviewProfile) {
                countReviewers.setText(getResources().getQuantityString(R.plurals.numberOfReview, customerReviewProfile.getCountReviewers(), customerReviewProfile.getCountReviewers()));
                rtCountPoint.setRating(customerReviewProfile.getAverageReviewPoint());

                customerReviewResponseList.clear();
                customerReviewResponseList.addAll(customerReviewProfile.getCustomerReviewResponses());
                customerReviewAdapter.notifyDataSetChanged();
            }
        });
    }

    private void createReviewRecyclerView() {
        RecyclerView rcvReview = viewBinding.reviewCustomerProfile;
        customerReviewAdapter = new CustomerReviewAdapter(getContext(), customerReviewResponseList, authorizationCode);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvReview.setLayoutManager(layoutManager);
        rcvReview.setItemAnimator(new DefaultItemAnimator());
        rcvReview.addItemDecoration(new CustomDividerItemDecoration(getResources().getDrawable(R.drawable.recycler_view_divider)));
        rcvReview.setAdapter(customerReviewAdapter);
    }
}
