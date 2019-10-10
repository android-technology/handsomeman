package com.tt.handsomeman.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.tt.handsomeman.HandymanApp;
import com.tt.handsomeman.R;
import com.tt.handsomeman.adapter.CustomerReviewAdapter;
import com.tt.handsomeman.adapter.JobAdapter;
import com.tt.handsomeman.response.JobDetailCustomerReview;
import com.tt.handsomeman.response.JobDetailProfile;
import com.tt.handsomeman.util.SharedPreferencesUtils;
import com.tt.handsomeman.viewmodel.JobsViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CustomerProfileJobDetail extends AppCompatActivity {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    SharedPreferencesUtils sharedPreferencesUtils;
    private JobsViewModel jobsViewModel;

    private CustomerReviewAdapter customerReviewAdapter;
    private List<JobDetailCustomerReview> jobDetailCustomerReviews = new ArrayList<>();
    private ImageView customerAvatar;
    private TextView customerName, customerAllProjectCount, customerSuccessedProject, countReviewers;
    private RatingBar countPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile_job_detail);

        customerName = findViewById(R.id.customerNameCustomerProfileJobDetail);
        customerAllProjectCount = findViewById(R.id.allProjectsCustomerJobDetail);
        customerSuccessedProject = findViewById(R.id.successedProjectsCustomerJobDetail);
        countReviewers = findViewById(R.id.reviewCountCustomerProfileJobDetail);
        countPoint = findViewById(R.id.ratingBarCustomerJobDetail);

        HandymanApp.getComponent().inject(this);

        jobsViewModel = ViewModelProviders.of(this, viewModelFactory).get(JobsViewModel.class);

        findViewById(R.id.customerProfileJobDetailBackButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        createCustomerReviewRecycleView();

        Integer customerId = getIntent().getIntExtra("customerId", 0);
        fetchData(customerId);
    }

    private void fetchData(Integer customerId) {
        String authorizationCode = sharedPreferencesUtils.get("token", String.class);

        jobsViewModel.fetchJobDetailProfile(authorizationCode, customerId);
        jobsViewModel.getJobDetailProfileLiveData().observe(this, new Observer<JobDetailProfile>() {
            @Override
            public void onChanged(JobDetailProfile jobDetailProfile) {
                customerName.setText(jobDetailProfile.getCustomerName());
                customerAllProjectCount.setText(String.valueOf(jobDetailProfile.getAllProject()));
                customerSuccessedProject.setText(String.valueOf(jobDetailProfile.getAllProject()));
                countReviewers.setText(String.valueOf(jobDetailProfile.getCountReviewers()));
                countPoint.setRating(jobDetailProfile.getAverageReviewPoint());

                jobDetailCustomerReviews.clear();
                jobDetailCustomerReviews.addAll(jobDetailProfile.getJobDetailCustomerReviews());
                customerReviewAdapter.notifyDataSetChanged();
            }
        });
    }

    private void createCustomerReviewRecycleView() {
        RecyclerView rcvJob = findViewById(R.id.reviewCustomerRecycleView);
        customerReviewAdapter = new CustomerReviewAdapter(this, jobDetailCustomerReviews);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcvJob.setLayoutManager(layoutManager);
        rcvJob.setItemAnimator(new DefaultItemAnimator());
        rcvJob.setAdapter(customerReviewAdapter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        jobsViewModel.clearSubscriptions();
    }
}
