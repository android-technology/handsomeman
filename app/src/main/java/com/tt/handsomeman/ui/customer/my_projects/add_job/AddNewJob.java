package com.tt.handsomeman.ui.customer.my_projects.add_job;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback;

import com.tt.handsomeman.HandymanApp;
import com.tt.handsomeman.databinding.ActivityAddNewJobBinding;
import com.tt.handsomeman.request.AddJobRequest;
import com.tt.handsomeman.response.StandardResponse;
import com.tt.handsomeman.util.SharedPreferencesUtils;
import com.tt.handsomeman.util.StatusConstant;
import com.tt.handsomeman.viewmodel.JobsViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.inject.Inject;

import me.relex.circleindicator.CircleIndicator3;

public class AddNewJob extends FragmentActivity {

    private static final int NUM_PAGES = 3;
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    SharedPreferencesUtils sharedPreferencesUtils;
    ViewPager2 viewPager;
    AddJobRequest addJobRequest;
    ActivityAddNewJobBinding binding;
    private JobsViewModel jobsViewModel;
    private Button btnSubmit;
    private FragmentStateAdapter pagerAdapter;
    private OnPageChangeCallback pageChangeCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNewJobBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        addJobRequest = new AddJobRequest();
        btnSubmit = binding.buttonSubmit;

        HandymanApp.getComponent().inject(this);
        jobsViewModel = new ViewModelProvider(this, viewModelFactory).get(JobsViewModel.class);

        generateViewPager();
        goBackward();
        setViewPagerUILogic();
        addNewJob();
    }

    private void addNewJob() {
        binding.buttonSubmit.setOnClickListener(v -> {
            Calendar now = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ZZ", Locale.getDefault());
            String token = sharedPreferencesUtils.get("token", String.class);
            addJobRequest.setCreateTime(simpleDateFormat.format(now.getTime()));

            jobsViewModel.addNewJob(token, addJobRequest);
            jobsViewModel.getStandardResponseMutableLiveData().observe(this, new Observer<StandardResponse>() {
                @Override
                public void onChanged(StandardResponse standardResponse) {
                    Toast.makeText(AddNewJob.this, standardResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    if (standardResponse.getStatus().equals(StatusConstant.OK)) {
                        Intent intent = new Intent();
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }
            });
        });
    }

    private void setViewPagerUILogic() {
        binding.imageButtonCheckFirst.setEnabled(false);
        binding.imageButtonCheckSecond.setEnabled(false);

        viewPager.registerOnPageChangeCallback(pageChangeCallback = new OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        binding.imageButtonCheckFirst.setVisibility(View.VISIBLE);
                        binding.imageButtonCheckSecond.setVisibility(View.INVISIBLE);
                        btnSubmit.setVisibility(View.GONE);
                        break;
                    case 1:
                        binding.imageButtonCheckFirst.setVisibility(View.INVISIBLE);
                        binding.imageButtonCheckSecond.setVisibility(View.VISIBLE);
                        btnSubmit.setVisibility(View.GONE);
                        break;
                    case 2:
                        binding.imageButtonCheckFirst.setVisibility(View.INVISIBLE);
                        binding.imageButtonCheckSecond.setVisibility(View.INVISIBLE);
                        btnSubmit.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
    }

    private void generateViewPager() {
        viewPager = binding.viewPager;
        viewPager.setUserInputEnabled(false);
        pagerAdapter = new ScreenSlidePagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);

        CircleIndicator3 indicator = binding.circleIndicator;
        indicator.setViewPager(viewPager);
    }

    private void goBackward() {
        binding.backButton.setOnClickListener(v -> onBackPressed());
    }

    @Override
    protected void onDestroy() {
        binding = null;
        viewPager.unregisterOnPageChangeCallback(pageChangeCallback);
        jobsViewModel.clearSubscriptions(this.getClass().getName());
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    private static class ScreenSlidePagerAdapter extends FragmentStateAdapter {
        ScreenSlidePagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new AddNewJobChildFirstFragment();
                case 1:
                    return new AddNewJobChildSecondFragment();
                case 2:
                    return new AddNewJobChildThirdFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }
}