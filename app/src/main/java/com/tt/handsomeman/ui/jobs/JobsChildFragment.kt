package com.tt.handsomeman.ui.jobs

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.content.PermissionChecker
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.tt.handsomeman.HandymanApp
import com.tt.handsomeman.R
import com.tt.handsomeman.adapter.CategoryAdapter
import com.tt.handsomeman.adapter.JobAdapter
import com.tt.handsomeman.model.Category
import com.tt.handsomeman.model.Job
import com.tt.handsomeman.service.StartScreenService
import com.tt.handsomeman.util.SharedPreferencesUtils
import com.tt.handsomeman.viewmodel.JobsViewModel

import java.util.ArrayList
import java.util.Objects

import javax.inject.Inject

import androidx.core.content.PermissionChecker.checkSelfPermission

class JobsChildFragment : Fragment(), LocationListener {

    private var pgJob: ProgressBar? = null
    private var pgCategory: ProgressBar? = null
    private var jobAdapter: JobAdapter? = null
    private var categoryAdapter: CategoryAdapter? = null
    private val jobArrayList = ArrayList<Job>()
    private val categoryArrayList = ArrayList<Category>()

    private var jobsViewModel: JobsViewModel? = null

    @Inject
    internal var startScreenService: StartScreenService? = null

    @Inject
    internal var sharedPreferencesUtils: SharedPreferencesUtils? = null

    private val mLocationCallback: LocationCallback? = null
    private val fusedLocationClient: FusedLocationProviderClient? = null
    protected var locationManager: LocationManager
    protected var locationListener: LocationListener? = null
    internal var lat: Double? = null
    internal var lng: Double? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {

        //        jobsViewModel = ViewModelProviders.of(this).get(JobsViewModel.class);

        HandymanApp.component!!.inject(this)

        jobsViewModel = JobsViewModel(Objects.requireNonNull<FragmentActivity>(activity).getApplication(), startScreenService)

        val view = inflater.inflate(R.layout.fragment_job_child, container, false)

        buildJobRecycleView(view)

        buildCategoryRecycleView(view)

        locationManager = activity!!.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        //        if (checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PermissionChecker.PERMISSION_GRANTED && checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PermissionChecker.PERMISSION_GRANTED) {
        //            // TODO: Consider calling
        //            //    Activity#requestPermissions
        //            // here to request the missing permissions, and then overriding
        //            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
        //            //                                          int[] grantResults)
        //            // to handle the case where the user grants the permission. See the documentation
        //            // for Activity#requestPermissions for more details.
        ////            return TODO;
        //        }
        //        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0L, 0f, this);


        initData()

        return view
    }

    override fun onLocationChanged(location: Location) {
        lat = location.latitude
        lng = location.longitude
    }

    override fun onProviderDisabled(provider: String) {
        Log.d("Latitude", "disable")
    }

    override fun onProviderEnabled(provider: String) {
        Log.d("Latitude", "enable")
    }

    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
        Log.d("Latitude", "status")
    }

    private fun initData() {
        val authorizationCode = sharedPreferencesUtils!!.get<String>("token", String::class.java)
        val radius = 10.0
        jobsViewModel!!.initData(authorizationCode, lat, lng, radius)

        jobsViewModel!!.startScreenData.observe(this, { data ->
            pgJob!!.visibility = View.GONE
            jobArrayList.clear()
            jobArrayList.addAll(data.jobList!!)
            jobAdapter!!.notifyDataSetChanged()

            pgCategory!!.visibility = View.GONE
            categoryArrayList.clear()
            categoryArrayList.addAll(data.categoryList!!)
            categoryAdapter!!.notifyDataSetChanged()
        })
    }

    private fun buildJobRecycleView(view: View) {
        pgJob = view.findViewById(R.id.progressBarJobs)
        val rcvJob = view.findViewById<RecyclerView>(R.id.recycleViewJobs)
        jobAdapter = JobAdapter(context, jobArrayList)
        jobAdapter!!.setOnItemClickListener { position -> Toast.makeText(activity, jobArrayList[position].title, Toast.LENGTH_SHORT).show() }
        val layoutManagerJob = LinearLayoutManager(context)
        rcvJob.layoutManager = layoutManagerJob
        rcvJob.itemAnimator = DefaultItemAnimator()
        rcvJob.adapter = jobAdapter
    }

    private fun buildCategoryRecycleView(view: View) {
        pgCategory = view.findViewById(R.id.progressBarCategory)
        val rcvCategory = view.findViewById<RecyclerView>(R.id.recycleViewCategories)
        categoryAdapter = CategoryAdapter(context, categoryArrayList)
        categoryAdapter!!.setOnItemClickListener { position -> Toast.makeText(activity, categoryArrayList[position].name, Toast.LENGTH_SHORT).show() }
        val layoutManagerCategory = LinearLayoutManager(context)
        rcvCategory.layoutManager = layoutManagerCategory
        rcvCategory.itemAnimator = DefaultItemAnimator()
        rcvCategory.adapter = categoryAdapter
    }

    override fun onStop() {
        super.onStop()
        jobsViewModel!!.clearSubscriptions()
    }
}
