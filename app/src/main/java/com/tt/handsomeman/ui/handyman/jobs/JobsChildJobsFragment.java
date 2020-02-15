package com.tt.handsomeman.ui.handyman.jobs;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tt.handsomeman.HandymanApp;
import com.tt.handsomeman.R;
import com.tt.handsomeman.adapter.CategoryAdapter;
import com.tt.handsomeman.adapter.JobAdapter;
import com.tt.handsomeman.model.Category;
import com.tt.handsomeman.model.Job;
import com.tt.handsomeman.ui.BaseFragment;
import com.tt.handsomeman.util.Constants;
import com.tt.handsomeman.util.CustomDividerItemDecoration;
import com.tt.handsomeman.util.SharedPreferencesUtils;
import com.tt.handsomeman.viewmodel.JobsViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class JobsChildJobsFragment extends BaseFragment<JobsViewModel> {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    SharedPreferencesUtils sharedPreferencesUtils;
    private ProgressBar pgJob, pgCategory;
    private JobAdapter jobAdapter;
    private CategoryAdapter categoryAdapter;
    private List<Job> jobArrayList = new ArrayList<>();
    private List<Category> categoryArrayList = new ArrayList<>();
    private LinearLayout showMoreYourLocation;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        HandymanApp.getComponent().inject(this);
        baseViewModel = new ViewModelProvider(this, viewModelFactory).get(JobsViewModel.class);
        return inflater.inflate(R.layout.fragment_jobs_child_jobs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        pgJob = view.findViewById(R.id.progressBarJobs);
        pgCategory = view.findViewById(R.id.progressBarCategory);
        showMoreYourLocation = view.findViewById(R.id.showMoreYourLocation);

        createJobRecycleView(view);

        createCategoryRecycleView(view);

        showMoreByYourLocation();

        Constants.Latitude.observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                fetchData(aDouble, Constants.Longitude.getValue());
            }
        });
    }

    private void showMoreByYourLocation() {
        showMoreYourLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), YourLocation.class));
            }
        });
    }

    private void createJobRecycleView(View view) {
        RecyclerView rcvJob = view.findViewById(R.id.recycleViewJobs);
        jobAdapter = new JobAdapter(getContext(), jobArrayList);
        jobAdapter.setOnItemClickListener(new JobAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), JobDetail.class);
                intent.putExtra("jobId", jobArrayList.get(position).getId());
                startActivity(intent);
            }
        });
        RecyclerView.LayoutManager layoutManagerJob = new LinearLayoutManager(getContext());
        rcvJob.setLayoutManager(layoutManagerJob);
        rcvJob.setItemAnimator(new DefaultItemAnimator());
        rcvJob.addItemDecoration(new CustomDividerItemDecoration(getResources().getDrawable(R.drawable.recycler_view_divider)));
        rcvJob.setAdapter(jobAdapter);
    }

    private void createCategoryRecycleView(View view) {
        RecyclerView rcvCategory = view.findViewById(R.id.recycleViewCategories);
        categoryAdapter = new CategoryAdapter(getContext(), categoryArrayList);
        categoryAdapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String categoryName = categoryArrayList.get(position).getName();

                Intent intent = new Intent(getActivity(), GroupByCategory.class);
                intent.putExtra("categoryName", categoryName);
                intent.putExtra("categoryId", categoryArrayList.get(position).getId());
                startActivity(intent);
            }
        });
        RecyclerView.LayoutManager layoutManagerCategory = new LinearLayoutManager(getContext());
        rcvCategory.setLayoutManager(layoutManagerCategory);
        rcvCategory.setItemAnimator(new DefaultItemAnimator());
        rcvCategory.addItemDecoration(new CustomDividerItemDecoration(getResources().getDrawable(R.drawable.recycler_view_divider)));
        rcvCategory.setAdapter(categoryAdapter);
    }

    private void fetchData(Double lat, Double lng) {
        String authorizationCode = sharedPreferencesUtils.get("token", String.class);

        double radius = 10d;

        baseViewModel.fetchData(authorizationCode, lat, lng, radius);

        baseViewModel.getStartScreenData().observe(this, data -> {
            pgJob.setVisibility(View.GONE);
            jobArrayList.clear();
            jobArrayList.addAll(data.getJobList());
            jobAdapter.notifyDataSetChanged();

            pgCategory.setVisibility(View.GONE);
            categoryArrayList.clear();
            categoryArrayList.addAll(data.getCategoryList());
            categoryAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public void onStop() {
        Constants.Latitude.removeObservers(this);
        super.onStop();
    }
}
