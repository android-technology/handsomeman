package com.tt.handsomeman.ui.handyman.jobs;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tt.handsomeman.HandymanApp;
import com.tt.handsomeman.R;
import com.tt.handsomeman.adapter.JobFilterAdapter;
import com.tt.handsomeman.databinding.FragmentJobsChildWishListBinding;
import com.tt.handsomeman.model.Job;
import com.tt.handsomeman.ui.BaseFragment;
import com.tt.handsomeman.util.CustomDividerItemDecoration;
import com.tt.handsomeman.util.SharedPreferencesUtils;
import com.tt.handsomeman.viewmodel.HandymanViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class JobsChildWishListFragment extends BaseFragment<HandymanViewModel, FragmentJobsChildWishListBinding> {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    SharedPreferencesUtils sharedPreferencesUtils;
    private JobFilterAdapter jobAdapter;
    private List<Job> jobArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        HandymanApp.getComponent().inject(this);
        baseViewModel = new ViewModelProvider(this, viewModelFactory).get(HandymanViewModel.class);
        viewBinding = FragmentJobsChildWishListBinding.inflate(inflater, container, false);
        return viewBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        createJobRecycleView(view);

        fetchData();
    }

    private void fetchData() {
        String authorizationCode = sharedPreferencesUtils.get("token", String.class);
        baseViewModel.fetchJobsWishList(authorizationCode);

        baseViewModel.getJobLiveData().observe(getViewLifecycleOwner(), data -> {
            jobArrayList.clear();
            jobArrayList.addAll(data);
            jobAdapter.notifyDataSetChanged();
        });
    }

    private void createJobRecycleView(@NonNull View view) {
        RecyclerView rcvJob = viewBinding.recycleViewJobsWishList;
        jobAdapter = new JobFilterAdapter(getContext(), jobArrayList);
        jobAdapter.setOnItemClickListener(new JobFilterAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), JobDetail.class);
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
}
