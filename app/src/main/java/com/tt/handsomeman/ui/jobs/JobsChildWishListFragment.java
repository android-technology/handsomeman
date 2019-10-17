package com.tt.handsomeman.ui.jobs;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tt.handsomeman.HandymanApp;
import com.tt.handsomeman.R;
import com.tt.handsomeman.adapter.JobAdapter;
import com.tt.handsomeman.model.Job;
import com.tt.handsomeman.ui.JobDetail;
import com.tt.handsomeman.util.SharedPreferencesUtils;
import com.tt.handsomeman.viewmodel.JobsViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class JobsChildWishListFragment extends Fragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    SharedPreferencesUtils sharedPreferencesUtils;
    private JobAdapter jobAdapter;
    private List<Job> jobArrayList = new ArrayList<>();
    private JobsViewModel jobsViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        HandymanApp.getComponent().inject(this);
        jobsViewModel = ViewModelProviders.of(this, viewModelFactory).get(JobsViewModel.class);
        return inflater.inflate(R.layout.fragment_jobs_child_wish_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        createJobRecycleView(view);

        fetchData();
    }

    private void fetchData() {
        String authorizationCode = sharedPreferencesUtils.get("token", String.class);
        jobsViewModel.fetchJobsWishList(authorizationCode);

        jobsViewModel.getJobLiveData().observe(this, data -> {
            jobArrayList.clear();
            jobArrayList.addAll(data);
            jobAdapter.notifyDataSetChanged();
        });
    }

    private void createJobRecycleView(@NonNull View view) {
        RecyclerView rcvJob = view.findViewById(R.id.recycleViewJobsWishList);
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
        rcvJob.setAdapter(jobAdapter);
    }
}