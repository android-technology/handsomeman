package com.tt.handsomeman.ui.jobs;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.tt.handsomeman.HandymanApp;
import com.tt.handsomeman.R;
import com.tt.handsomeman.adapter.CategoryAdapter;
import com.tt.handsomeman.adapter.JobAdapter;
import com.tt.handsomeman.model.Category;
import com.tt.handsomeman.model.Job;
import com.tt.handsomeman.service.StartScreenService;
import com.tt.handsomeman.util.Constants;
import com.tt.handsomeman.util.SharedPreferencesUtils;
import com.tt.handsomeman.viewmodel.JobsViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

public class JobsChildFragment extends Fragment {

    private ProgressBar pgJob, pgCategory;
    private JobAdapter jobAdapter;
    private CategoryAdapter categoryAdapter;
    private List<Job> jobArrayList = new ArrayList<>();
    private List<Category> categoryArrayList = new ArrayList<>();

    private JobsViewModel jobsViewModel;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Inject
    SharedPreferencesUtils sharedPreferencesUtils;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        HandymanApp.getComponent().inject(this);

        jobsViewModel = ViewModelProviders.of(this, viewModelFactory).get(JobsViewModel.class);

        View view = inflater.inflate(R.layout.fragment_job_child, container, false);

        pgJob = view.findViewById(R.id.progressBarJobs);
        pgCategory = view.findViewById(R.id.progressBarCategory);

        createJobRecycleView(view);

        createCategoryRecycleView(view);

        fetchData(Constants.Latitude.getValue(), Constants.Longitude.getValue());

        Constants.Latitude.observe(this, new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                fetchData(aDouble, Constants.Longitude.getValue());
            }
        });

        return view;
    }

    private void createJobRecycleView(View view) {
        RecyclerView rcvJob = view.findViewById(R.id.recycleViewJobs);
        jobAdapter = new JobAdapter(getContext(), jobArrayList);
        jobAdapter.setOnItemClickListener(new JobAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getActivity(), jobArrayList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        RecyclerView.LayoutManager layoutManagerJob = new LinearLayoutManager(getContext());
        rcvJob.setLayoutManager(layoutManagerJob);
        rcvJob.setItemAnimator(new DefaultItemAnimator());
        rcvJob.setAdapter(jobAdapter);
    }

    private void createCategoryRecycleView(View view) {
        RecyclerView rcvCategory = view.findViewById(R.id.recycleViewCategories);
        categoryAdapter = new CategoryAdapter(getContext(), categoryArrayList);
        categoryAdapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getActivity(), categoryArrayList.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
        RecyclerView.LayoutManager layoutManagerCategory = new LinearLayoutManager(getContext());
        rcvCategory.setLayoutManager(layoutManagerCategory);
        rcvCategory.setItemAnimator(new DefaultItemAnimator());
        rcvCategory.setAdapter(categoryAdapter);
    }

    private void fetchData(Double lat, Double lng) {
        String authorizationCode = sharedPreferencesUtils.get("token", String.class);

        double radius = 10d;

        jobsViewModel.fetchData(authorizationCode, lat, lng, radius);

        jobsViewModel.getStartScreenData().observe(this, data -> {
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
        super.onStop();
        jobsViewModel.clearSubscriptions();
    }
}
