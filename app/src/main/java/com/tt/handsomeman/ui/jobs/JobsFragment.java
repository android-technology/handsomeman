package com.tt.handsomeman.ui.jobs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.tt.handsomeman.R;

public class JobsFragment extends Fragment {

    private RadioButton rdJobs, rdWishList;

    private Fragment jobsChildJobsFragment = new JobsChildJobsFragment();
    private Fragment jobsChildWishListFragment = new JobsChildWishListFragment();
    private Fragment active = jobsChildJobsFragment;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_jobs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        rdJobs = view.findViewById(R.id.radioButtonWishList);
        rdWishList = view.findViewById(R.id.radioButtonJobs);

        final FragmentManager fm = getChildFragmentManager();
        fm.beginTransaction().add(R.id.jobsFragmentParent, jobsChildWishListFragment).hide(jobsChildWishListFragment).commit();
        fm.beginTransaction().add(R.id.jobsFragmentParent, jobsChildJobsFragment).commit();

        rdJobs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                fm.beginTransaction().hide(active).show(jobsChildJobsFragment).commit();
                active = jobsChildJobsFragment;
            }
        });

        rdWishList.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                fm.beginTransaction().hide(active).show(jobsChildWishListFragment).commit();
                active = jobsChildWishListFragment;
            }
        });
    }
}