package com.tt.handsomeman.ui.customer.my_projects;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tt.handsomeman.HandymanApp;
import com.tt.handsomeman.R;
import com.tt.handsomeman.adapter.JobFilterAdapter;
import com.tt.handsomeman.databinding.FragmentMyProjectsChildInPastBinding;
import com.tt.handsomeman.model.Job;
import com.tt.handsomeman.ui.BaseFragment;
import com.tt.handsomeman.ui.handyman.jobs.JobDetail;
import com.tt.handsomeman.util.CustomDividerItemDecoration;
import com.tt.handsomeman.viewmodel.JobsViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MyProjectsChildInPastFragment extends BaseFragment<JobsViewModel, FragmentMyProjectsChildInPastBinding> {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private List<Job> inPastList = new ArrayList<>();
    private JobFilterAdapter jobAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        HandymanApp.getComponent().inject(this);
        baseViewModel = new ViewModelProvider(this, viewModelFactory).get(JobsViewModel.class);
        viewBinding = FragmentMyProjectsChildInPastBinding.inflate(inflater, container, false);
        return viewBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        createJobRecycleView();
        ((MyProjectsFragment) getParentFragment()).inPastList.observe(getViewLifecycleOwner(), new Observer<List<Job>>() {
            @Override
            public void onChanged(List<Job> jobs) {
                inPastList.clear();
                inPastList.addAll(jobs);
                jobAdapter.notifyDataSetChanged();
            }
        });
    }

    private void createJobRecycleView() {
        RecyclerView rcvJob = viewBinding.recycleViewJobsInPast;
        jobAdapter = new JobFilterAdapter(getContext(), inPastList);
        jobAdapter.setOnItemClickListener(new JobFilterAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), JobDetail.class);
                intent.putExtra("jobId", inPastList.get(position).getId());
                startActivity(intent);
            }
        });
        RecyclerView.LayoutManager layoutManagerJob = new LinearLayoutManager(getContext());
        rcvJob.setLayoutManager(layoutManagerJob);
        rcvJob.setItemAnimator(new DefaultItemAnimator());
        rcvJob.addItemDecoration(new CustomDividerItemDecoration(getResources().getDrawable(R.drawable.recycler_view_divider)));
        rcvJob.setAdapter(jobAdapter);
    }

}
