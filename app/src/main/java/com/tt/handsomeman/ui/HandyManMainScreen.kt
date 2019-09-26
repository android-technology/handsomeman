package com.tt.handsomeman.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tt.handsomeman.R
import com.tt.handsomeman.ui.jobs.JobsFragment
import com.tt.handsomeman.ui.messages.MessagesFragment
import com.tt.handsomeman.ui.more.MoreFragment
import com.tt.handsomeman.ui.my_projects.MyProjectsFragment
import com.tt.handsomeman.ui.notifications.NotificationsFragment

class HandyManMainScreen : AppCompatActivity() {

    internal val fragment1: Fragment = JobsFragment()
    internal val fragment2: Fragment = MessagesFragment()
    internal val fragment3: Fragment = MyProjectsFragment()
    internal val fragment4: Fragment = NotificationsFragment()
    internal val fragment5: Fragment = MoreFragment()
    internal val fm = supportFragmentManager
    internal var active = fragment1

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_jobs -> {
                fm.beginTransaction().hide(active).show(fragment1).commit()
                active = fragment1
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_messages -> {
                fm.beginTransaction().hide(active).show(fragment2).commit()
                active = fragment2
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_my_projects -> {
                fm.beginTransaction().hide(active).show(fragment3).commit()
                active = fragment3
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_notifications -> {
                fm.beginTransaction().hide(active).show(fragment4).commit()
                active = fragment4
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_more -> {
                fm.beginTransaction().hide(active).show(fragment5).commit()
                active = fragment5
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handy_man_main_screen)

        val navView = findViewById<BottomNavigationView>(R.id.nav_view)

        //        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //        NavigationUI.setupWithNavController(navView, navController);

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        FragmentManipulate()
    }

    private fun FragmentManipulate() {
        fm.beginTransaction().add(R.id.nav_host_fragment, fragment5, "5").hide(fragment5).commit()
        fm.beginTransaction().add(R.id.nav_host_fragment, fragment4, "4").hide(fragment4).commit()
        fm.beginTransaction().add(R.id.nav_host_fragment, fragment3, "3").hide(fragment3).commit()
        fm.beginTransaction().add(R.id.nav_host_fragment, fragment2, "2").hide(fragment2).commit()
        fm.beginTransaction().add(R.id.nav_host_fragment, fragment1, "1").commit()
    }

}
