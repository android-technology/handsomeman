package com.tt.handsomeman.ui.handyman;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tt.handsomeman.HandymanApp;
import com.tt.handsomeman.R;
import com.tt.handsomeman.adapter.JobFilterAdapter;
import com.tt.handsomeman.model.Job;
import com.tt.handsomeman.ui.BaseAppCompatActivity;
import com.tt.handsomeman.util.CustomDividerItemDecoration;
import com.tt.handsomeman.util.SharedPreferencesUtils;
import com.tt.handsomeman.viewmodel.JobsViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class GroupByCategory extends BaseAppCompatActivity<JobsViewModel> {
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    SharedPreferencesUtils sharedPreferencesUtils;

    private JobFilterAdapter jobAdapter;
    private List<Job> jobArrayList = new ArrayList<>();
    private ProgressBar pgJob;
    private TextView categoryName;
    private ImageButton btnFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_by_category);

        HandymanApp.getComponent().inject(this);

        baseViewModel = ViewModelProviders.of(this, viewModelFactory).get(JobsViewModel.class);

        pgJob = findViewById(R.id.progressBarJobCategory);
        categoryName = findViewById(R.id.textViewCategoryName);
        btnFilter = findViewById(R.id.imageButtonFilter);

        backPreviousActivity();

        navigateToFilter();

        createJobRecycleView();

        Integer categoryId = getIntent().getIntExtra("categoryId", 0);
        String categoryNameIntent = getIntent().getStringExtra("categoryName");
        categoryName.setText(categoryNameIntent);
        fetchData(categoryId);
    }

    private void navigateToFilter() {
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GroupByCategory.this, JobFilter.class));
            }
        });
    }

    private void backPreviousActivity() {
        findViewById(R.id.categoryBackButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void createJobRecycleView() {
        RecyclerView rcvJob = findViewById(R.id.recycleViewJobsByCategory);
        jobAdapter = new JobFilterAdapter(this, jobArrayList);
        jobAdapter.setOnItemClickListener(new JobFilterAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(GroupByCategory.this, JobDetail.class);
                intent.putExtra("jobId", jobArrayList.get(position).getId());
                startActivity(intent);
            }
        });
        RecyclerView.LayoutManager layoutManagerJob = new LinearLayoutManager(this);
        rcvJob.setLayoutManager(layoutManagerJob);
        rcvJob.setItemAnimator(new DefaultItemAnimator());
        rcvJob.addItemDecoration(new CustomDividerItemDecoration(getResources().getDrawable(R.drawable.recycler_view_divider)));
        rcvJob.setAdapter(jobAdapter);
    }


    private void fetchData(Integer categoryId) {
        String authorizationCode = sharedPreferencesUtils.get("token", String.class);

        baseViewModel.fetchJobsByCategory(authorizationCode, categoryId);

        baseViewModel.getJobLiveData().observe(this, data -> {
            pgJob.setVisibility(View.GONE);
            jobArrayList.clear();
            jobArrayList.addAll(data);
            jobAdapter.notifyDataSetChanged();
        });
    }
}
