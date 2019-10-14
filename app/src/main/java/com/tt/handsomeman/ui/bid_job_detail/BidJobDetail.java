package com.tt.handsomeman.ui.bid_job_detail;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.tt.handsomeman.HandymanApp;
import com.tt.handsomeman.R;
import com.tt.handsomeman.model.Job;
import com.tt.handsomeman.util.CustomViewPager;
import com.tt.handsomeman.util.SharedPreferencesUtils;
import com.tt.handsomeman.viewmodel.JobsViewModel;

import javax.inject.Inject;

import me.relex.circleindicator.CircleIndicator;

public class BidJobDetail extends FragmentActivity {

    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 3;
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    SharedPreferencesUtils sharedPreferencesUtils;
    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private CustomViewPager mPager;
    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter pagerAdapter;

    private TextView tvViewPagerName;
    private Button btnSubmit;
    private JobsViewModel jobsViewModel;
    private ImageButton ibCheckButtonBudget, ibCheckButtonLetter;
    private Job job;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bid_job_detail);

        HandymanApp.getComponent().inject(this);
        jobsViewModel = ViewModelProviders.of(this, viewModelFactory).get(JobsViewModel.class);

        tvViewPagerName = findViewById(R.id.bidJobDetailViewPagerName);
        ibCheckButtonBudget = findViewById(R.id.imageButtonCheckBudgetBidJobDetail);
        ibCheckButtonLetter = findViewById(R.id.imageButtonCheckLetterBidJobDetail);
        btnSubmit = findViewById(R.id.submitBidJobDetail);
        mPager = findViewById(R.id.bidJobDetailPager);

        job = (Job) getIntent().getSerializableExtra("job");

        generateViewPager();
        viewPagerUILogic();

        findViewById(R.id.bidJobDetailBackButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ibCheckButtonBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPager.getCurrentItem() < NUM_PAGES - 1) {
                    mPager.setCurrentItem(mPager.getCurrentItem() + 1);
                }
            }
        });

        ibCheckButtonLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPager.getCurrentItem() < NUM_PAGES - 1) {
                    mPager.setCurrentItem(mPager.getCurrentItem() + 1);
                }
            }
        });
    }

    private void viewPagerUILogic() {
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        ibCheckButtonBudget.setVisibility(View.VISIBLE);
                        ibCheckButtonLetter.setVisibility(View.GONE);
                        btnSubmit.setVisibility(View.GONE);
                        tvViewPagerName.setText(getResources().getText(R.string.budget));
                        break;
                    case 1:
                        ibCheckButtonBudget.setVisibility(View.GONE);
                        ibCheckButtonLetter.setVisibility(View.VISIBLE);
                        btnSubmit.setVisibility(View.GONE);
                        tvViewPagerName.setText(getResources().getText(R.string.letter));
                        break;
                    case 2:
                        ibCheckButtonBudget.setVisibility(View.GONE);
                        ibCheckButtonLetter.setVisibility(View.GONE);
                        btnSubmit.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void generateViewPager() {
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());

        mPager.setAdapter(pagerAdapter);

        CircleIndicator indicator = findViewById(R.id.bidJobDetailIndicator);
        indicator.setViewPager(mPager);

        mPager.disableScroll(true);
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return BidJobBudgetFragment.newInstance(job.getBudgetMin(),
                            job.getBudgetMax(),
                            job.getBudgetMin(),
                            job.getBudgetMin() * 0.9,
                            job.getBudgetMin() * 0.1);
                case 1: // Fragment # 0 - This will show FirstFragment
                    return new BidJobLetterFragment();
                case 2:
                    return new BidJobLetterReviewFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
