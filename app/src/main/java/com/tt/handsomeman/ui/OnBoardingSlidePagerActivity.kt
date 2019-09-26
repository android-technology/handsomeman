package com.tt.handsomeman.ui

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.view.View

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

import com.tt.handsomeman.HandymanApp
import com.tt.handsomeman.R
import com.tt.handsomeman.util.Constants
import com.tt.handsomeman.util.SharedPreferencesUtils

import javax.inject.Inject

import me.relex.circleindicator.CircleIndicator

class OnBoardingSlidePagerActivity : FragmentActivity() {

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private var mPager: ViewPager? = null

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private var pagerAdapter: PagerAdapter? = null

    @Inject
    internal var sharedPreferencesUtils: SharedPreferencesUtils? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme_Launcher)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_slide_pager)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        HandymanApp.component!!.inject(this)

        val state = sharedPreferencesUtils!!.get<Int>("state", Int::class.java)

        findViewById<View>(R.id.skipOnBoarding).setOnClickListener {
            if (state == Constants.NOT_ACTIVE_ACCOUNT) {
                startActivity(Intent(this@OnBoardingSlidePagerActivity, SignUpAddPayout::class.java))
                finish()
            } else {
                startActivity(Intent(this@OnBoardingSlidePagerActivity, Start::class.java))
                finish()
            }
        }

        if (state == Constants.NOT_ACTIVE_ACCOUNT) {
            startActivity(Intent(this@OnBoardingSlidePagerActivity, SignUpAddPayout::class.java))
            finish()
        } else if (state == Constants.STATE_REGISTER_ADDED_PAYOUT) {
            startActivity(Intent(this@OnBoardingSlidePagerActivity, HandyManMainScreen::class.java))
            finish()
        }

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = findViewById(R.id.boardingPager)

        pagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager)

        mPager!!.adapter = pagerAdapter

        val indicator = findViewById<CircleIndicator>(R.id.boardingIndicator)
        indicator.setViewPager(mPager)
    }

    override fun onBackPressed() {
        if (mPager!!.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            mPager!!.currentItem = mPager!!.currentItem - 1
        }
    }

    /**
     * A simple pager adapter that represents 5 OnBoardingSlidePageFragment objects, in
     * sequence.
     */
    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            when (position) {
                0 // Fragment # 0 - This will show FirstFragment
                -> return OnBoardingSlidePageFragment.newInstance(R.drawable.on_boarding_1, R.string.boarding_des_1)
                1 // Fragment # 0 - This will show FirstFragment different title
                -> return OnBoardingSlidePageFragment.newInstance(R.drawable.on_boarding_2, R.string.boarding_des_2)
                2 // Fragment # 1 - This will show SecondFragment
                -> return OnBoardingSlidePageFragment.newInstance(R.drawable.on_boarding_3, R.string.boarding_des_3)
                else -> return null
            }
        }

        override fun getCount(): Int {
            return NUM_PAGES
        }
    }

    companion object {
        /**
         * The number of pages (wizard steps) to show in this demo.
         */
        private val NUM_PAGES = 3
    }
}