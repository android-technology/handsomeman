package com.tt.handsomeman.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.tt.handsomeman.HandymanApp;
import com.tt.handsomeman.R;
import com.tt.handsomeman.util.Constants;
import com.tt.handsomeman.util.CustomViewPager;
import com.tt.handsomeman.util.SharedPreferencesUtils;

import javax.inject.Inject;

import me.relex.circleindicator.CircleIndicator;

public class OnBoardingSlidePagerActivity extends FragmentActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 3;
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

    private ConstraintLayout skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_Launcher);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding_slide_pager);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        HandymanApp.getComponent().inject(this);

        Integer state = sharedPreferencesUtils.get("state", Integer.class);

        skip = findViewById(R.id.skipOnBoarding);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (state.equals(Constants.NOT_ACTIVE_ACCOUNT)) {
                    startActivity(new Intent(OnBoardingSlidePagerActivity.this, SignUpAddPayout.class));
                    finish();
                } else {
                    startActivity(new Intent(OnBoardingSlidePagerActivity.this, Start.class));
                    finish();
                }
            }
        });

        if (state.equals(Constants.NOT_ACTIVE_ACCOUNT)) {
            startActivity(new Intent(OnBoardingSlidePagerActivity.this, SignUpAddPayout.class));
            finish();
        } else if (state.equals(Constants.STATE_REGISTER_ADDED_PAYOUT)) {
            startActivity(new Intent(OnBoardingSlidePagerActivity.this, HandyManMainScreen.class));
            finish();
        }

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = findViewById(R.id.boardingPager);

        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());

        mPager.setAdapter(pagerAdapter);

        CircleIndicator indicator = findViewById(R.id.boardingIndicator);
        indicator.setViewPager(mPager);

        mPager.disableScroll(false);
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

    /**
     * A simple pager adapter that represents 5 OnBoardingSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return OnBoardingSlidePageFragment.newInstance(R.drawable.on_boarding_1, R.string.boarding_des_1);
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return OnBoardingSlidePageFragment.newInstance(R.drawable.on_boarding_2, R.string.boarding_des_2);
                case 2: // Fragment # 1 - This will show SecondFragment
                    return OnBoardingSlidePageFragment.newInstance(R.drawable.on_boarding_3, R.string.boarding_des_3);
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