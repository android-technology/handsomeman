package com.tt.handsomeman.ui.handyman.jobs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tt.handsomeman.HandymanApp;
import com.tt.handsomeman.R;
import com.tt.handsomeman.adapter.CustomerReviewAdapter;
import com.tt.handsomeman.databinding.ActivityCustomerProfileJobDetailBinding;
import com.tt.handsomeman.response.CustomerReviewResponse;
import com.tt.handsomeman.response.JobDetailProfile;
import com.tt.handsomeman.ui.BaseAppCompatActivity;
import com.tt.handsomeman.ui.handyman.messages.Conversation;
import com.tt.handsomeman.util.CustomDividerItemDecoration;
import com.tt.handsomeman.util.SharedPreferencesUtils;
import com.tt.handsomeman.viewmodel.HandymanViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CustomerProfileJobDetail extends BaseAppCompatActivity<HandymanViewModel> {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    SharedPreferencesUtils sharedPreferencesUtils;

    private CustomerReviewAdapter customerReviewAdapter;
    private List<CustomerReviewResponse> customerReviewResponses = new ArrayList<>();
    private ImageView customerAvatar;
    private TextView customerName, customerAllProjectCount, customerSuccessedProject, countReviews;
    private RatingBar countPoint;
    private Button btnChat;
    private ActivityCustomerProfileJobDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCustomerProfileJobDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        customerName = binding.customerNameCustomerProfileJobDetail;
        customerAllProjectCount = binding.allProjectsCustomerJobDetail;
        customerSuccessedProject = binding.successedProjectsCustomerJobDetail;
        countReviews = binding.reviewCountCustomerProfileJobDetail;
        countPoint = binding.ratingBarCustomerJobDetail;
        btnChat = binding.chat;

        HandymanApp.getComponent().inject(this);

        baseViewModel = new ViewModelProvider(this, viewModelFactory).get(HandymanViewModel.class);

        binding.customerProfileJobDetailBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        createCustomerReviewRecycleView();

        Integer customerId = getIntent().getIntExtra("customerId", 0);
        fetchData(customerId);
    }

    private void sendMessage(String receiverName) {
        Intent intent = new Intent(CustomerProfileJobDetail.this, Conversation.class);
        intent.putExtra("addressName", receiverName);
        intent.putExtra("receiveId", getIntent().getIntExtra("customerId", 0));
        startActivity(intent);
    }

    private void fetchData(Integer customerId) {
        String authorizationCode = sharedPreferencesUtils.get("token", String.class);

        baseViewModel.fetchJobDetailProfile(authorizationCode, customerId);
        baseViewModel.getJobDetailProfileLiveData().observe(this, new Observer<JobDetailProfile>() {
            @Override
            public void onChanged(JobDetailProfile jobDetailProfile) {
                customerName.setText(jobDetailProfile.getCustomerName());
                customerAllProjectCount.setText(String.valueOf(jobDetailProfile.getAllProject()));
                customerSuccessedProject.setText(String.valueOf(jobDetailProfile.getSuccessedProject()));
                countReviews.setText(getResources().getQuantityString(R.plurals.numberOfReview, jobDetailProfile.getCountReviewers(), jobDetailProfile.getCountReviewers()));
                countPoint.setRating(jobDetailProfile.getAverageReviewPoint());

                customerReviewResponses.clear();
                customerReviewResponses.addAll(jobDetailProfile.getCustomerReviewResponses());
                customerReviewAdapter.notifyDataSetChanged();

                boolean isAccept = getIntent().getBooleanExtra("isAccept", false);
                if (isAccept) {
                    btnChat.setVisibility(View.VISIBLE);
                    btnChat.setOnClickListener(v -> {
                        sendMessage(jobDetailProfile.getCustomerName());
                    });
                }
            }
        });
    }

    private void createCustomerReviewRecycleView() {
        RecyclerView rcvReview = binding.reviewCustomerRecycleView;
        customerReviewAdapter = new CustomerReviewAdapter(this, customerReviewResponses);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcvReview.setLayoutManager(layoutManager);
        rcvReview.setItemAnimator(new DefaultItemAnimator());
        rcvReview.addItemDecoration(new CustomDividerItemDecoration(getResources().getDrawable(R.drawable.recycler_view_divider)));
        rcvReview.setAdapter(customerReviewAdapter);
    }
}
