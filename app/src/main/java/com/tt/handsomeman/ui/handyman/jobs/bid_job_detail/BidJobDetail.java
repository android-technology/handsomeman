package com.tt.handsomeman.ui.handyman.jobs.bid_job_detail;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.tt.handsomeman.R;
import com.tt.handsomeman.databinding.ActivityBidJobDetailBinding;
import com.tt.handsomeman.model.HandymanJobDetail;
import com.tt.handsomeman.util.CustomViewPager;

import me.relex.circleindicator.CircleIndicator;

public class BidJobDetail extends FragmentActivity {

    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    static final int NUM_PAGES = 3;
    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    static CustomViewPager mPager;
    static HandymanJobDetail handymanJobDetail;
    ActivityBidJobDetailBinding activityBidJobDetailBinding;
    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter pagerAdapter;
    private TextView tvViewPagerName;
    private Button btnSubmit;
    private ImageButton ibCheckButtonBudget, ibCheckButtonLetter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBidJobDetailBinding = ActivityBidJobDetailBinding.inflate(getLayoutInflater());
        setContentView(activityBidJobDetailBinding.getRoot());

        tvViewPagerName = activityBidJobDetailBinding.bidJobDetailViewPagerName;
        ibCheckButtonBudget = activityBidJobDetailBinding.imageButtonCheckBudgetBidJobDetail;
        ibCheckButtonLetter = activityBidJobDetailBinding.imageButtonCheckLetterBidJobDetail;
        btnSubmit = activityBidJobDetailBinding.submitBidJobDetail;
        mPager = activityBidJobDetailBinding.bidJobDetailPager;

        handymanJobDetail = (HandymanJobDetail) getIntent().getSerializableExtra("handymanJobDetail");

        generateViewPager();
        viewPagerUILogic();

        activityBidJobDetailBinding.bidJobDetailBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
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

        CircleIndicator indicator = activityBidJobDetailBinding.bidJobDetailIndicator;
        indicator.setViewPager(mPager);

        mPager.disableScroll(true);
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            handymanJobDetail = null;
            mPager = null;
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    @Override
    public void onDestroy() {
        activityBidJobDetailBinding = null;
        super.onDestroy();
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return BidJobBudgetFragment.newInstance(handymanJobDetail.getJob().getBudgetMin(),
                            handymanJobDetail.getJob().getBudgetMax(),
                            handymanJobDetail.getJob().getBudgetMin(),
                            handymanJobDetail.getJob().getBudgetMin() * 0.9,
                            handymanJobDetail.getJob().getBudgetMin() * 0.1);
                case 1: // Fragment # 0 - This will show FirstFragment
                    return new BidJobLetterFragment();
                case 2:
                    return BidJobLetterReviewFragment.newInstance(handymanJobDetail.getJob().getId(), handymanJobDetail.getJob().getTitle(), handymanJobDetail.getListPaymentMilestone().size());
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
