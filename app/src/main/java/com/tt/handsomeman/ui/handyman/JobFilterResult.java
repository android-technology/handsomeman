package com.tt.handsomeman.ui.handyman;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tt.handsomeman.HandymanApp;
import com.tt.handsomeman.R;
import com.tt.handsomeman.adapter.JobFilterAdapter;
import com.tt.handsomeman.model.Job;
import com.tt.handsomeman.util.Constants;
import com.tt.handsomeman.util.SharedPreferencesUtils;
import com.tt.handsomeman.viewmodel.JobsViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class JobFilterResult extends AppCompatActivity {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    SharedPreferencesUtils sharedPreferencesUtils;
    private JobsViewModel jobsViewModel;

    private JobFilterAdapter jobAdapter;
    private List<Job> jobArrayList = new ArrayList<>();
    private ProgressBar pgJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_result);

        HandymanApp.getComponent().inject(this);

        jobsViewModel = ViewModelProviders.of(this, viewModelFactory).get(JobsViewModel.class);

        pgJob = findViewById(R.id.progressBarFilterResult);

        backPreviousActivity();

        createJobRecycleView();

        Integer radius = getIntent().getIntExtra("radius", 0);
        Integer priceMin = getIntent().getIntExtra("priceMin", 0);
        Integer priceMax = getIntent().getIntExtra("priceMax", 0);
        String dateCreated = getIntent().getStringExtra("dateCreated");

        Constants.Latitude.observe(this, new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                fetchData(aDouble, Constants.Longitude.getValue(), radius, priceMin, priceMax, dateCreated);
            }
        });
    }

    private void backPreviousActivity() {
        findViewById(R.id.filterResultBackButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void createJobRecycleView() {
        RecyclerView rcvJob = findViewById(R.id.recycleViewFilterResult);
        jobAdapter = new JobFilterAdapter(this, jobArrayList);
        jobAdapter.setOnItemClickListener(new JobFilterAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(JobFilterResult.this, JobDetail.class);
                intent.putExtra("jobId", jobArrayList.get(position).getId());
                startActivity(intent);
            }
        });
        RecyclerView.LayoutManager layoutManagerJob = new LinearLayoutManager(this);
        rcvJob.setLayoutManager(layoutManagerJob);
        rcvJob.setItemAnimator(new DefaultItemAnimator());

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rcvJob.getContext(), ((LinearLayoutManager) layoutManagerJob).getOrientation());
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.recycler_view_divider));
        rcvJob.addItemDecoration(dividerItemDecoration);

        rcvJob.setAdapter(jobAdapter);
    }


    private void fetchData(Double lat, Double lng, Integer radius, Integer priceMin, Integer priceMax, String createTime) {
        String authorizationCode = sharedPreferencesUtils.get("token", String.class);

        jobsViewModel.fetchJobsByFilter(authorizationCode, lat, lng, radius, priceMin, priceMax, createTime);

        jobsViewModel.getJobLiveData().observe(this, data -> {
            pgJob.setVisibility(View.GONE);
            jobArrayList.clear();
            jobArrayList.addAll(data);
            jobAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public void onStop() {
        jobsViewModel.clearSubscriptions();
        Constants.Latitude.removeObservers(this);
        super.onStop();
    }
}
