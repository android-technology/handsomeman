package com.tt.handsomeman.ui.jobs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tt.handsomeman.HandymanApp;
import com.tt.handsomeman.R;
import com.tt.handsomeman.adapter.CategoryAdapter;
import com.tt.handsomeman.adapter.JobAdapter;
import com.tt.handsomeman.model.Category;
import com.tt.handsomeman.model.Job;
import com.tt.handsomeman.response.StartScreenData;
import com.tt.handsomeman.service.StartScreenService;
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
    StartScreenService startScreenService;

    @Inject
    SharedPreferencesUtils sharedPreferencesUtils;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

//        jobsViewModel = ViewModelProviders.of(this).get(JobsViewModel.class);

        HandymanApp.getComponent().inject(this);

        jobsViewModel = new JobsViewModel(Objects.requireNonNull(getActivity()).getApplication(), startScreenService);

        View view = inflater.inflate(R.layout.fragment_job_child, container, false);

        pgJob = view.findViewById(R.id.progressBarJobs);
        pgCategory = view.findViewById(R.id.progressBarCategory);

        RecyclerView rcvJob = view.findViewById(R.id.recycleViewJobs);
        jobAdapter = new JobAdapter(getContext(), jobArrayList);

        RecyclerView rcvCategory = view.findViewById(R.id.recycleViewCategories);
        categoryAdapter = new CategoryAdapter(getContext(), categoryArrayList);

        RecyclerView.LayoutManager layoutManagerJob = new LinearLayoutManager(getContext());
        rcvJob.setLayoutManager(layoutManagerJob);
        rcvJob.setItemAnimator(new DefaultItemAnimator());
        rcvJob.setAdapter(jobAdapter);

        RecyclerView.LayoutManager layoutManagerCategory = new LinearLayoutManager(getContext());
        rcvCategory.setLayoutManager(layoutManagerCategory);
        rcvCategory.setItemAnimator(new DefaultItemAnimator());
        rcvCategory.setAdapter(categoryAdapter);

        String authorizationCode = sharedPreferencesUtils.get("token", String.class);

        double lat = 40.80549240112305;
        double lng = -96.7181396484375;
        double radius = 10d;

        jobsViewModel.getListJob(authorizationCode, lat, lng, radius);

        jobsViewModel.getStartScreenData().observe(this, new Observer<StartScreenData>() {
            @Override
            public void onChanged(StartScreenData data) {
                pgJob.setVisibility(View.GONE);
                jobArrayList.clear();
                jobArrayList.addAll(data.getJobList());

                pgCategory.setVisibility(View.GONE);
                categoryArrayList.clear();
                categoryArrayList.addAll(data.getCategoryList());

                jobAdapter.notifyDataSetChanged();
                categoryAdapter.notifyDataSetChanged();
            }
        });

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        jobsViewModel.clearSubscriptions();
    }
}
