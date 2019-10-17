package com.tt.handsomeman.ui.jobs;

import android.content.Intent;
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
        RadioButton rdJobs = view.findViewById(R.id.radioButtonJobs);
        RadioButton rdWishList = view.findViewById(R.id.radioButtonWishList);

        final FragmentManager fm = getChildFragmentManager();
        fm.beginTransaction().add(R.id.jobsFragmentParent, jobsChildWishListFragment).hide(jobsChildWishListFragment).commit();
        fm.beginTransaction().add(R.id.jobsFragmentParent, jobsChildJobsFragment).commit();

        int choice = getActivity().getIntent().getIntExtra("radioButtonChoice", 0);

        if(choice == 1){
            rdWishList.setChecked(true);
            fm.beginTransaction().hide(active).show(jobsChildWishListFragment).commit();
            active = jobsChildWishListFragment;
        }

        rdJobs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    fm.beginTransaction().hide(active).show(jobsChildJobsFragment).commit();
                    active = jobsChildJobsFragment;
                }
            }
        });

        rdWishList.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    fm.beginTransaction().hide(active).show(jobsChildWishListFragment).commit();
                    active = jobsChildWishListFragment;
                }
            }
        });
    }
}