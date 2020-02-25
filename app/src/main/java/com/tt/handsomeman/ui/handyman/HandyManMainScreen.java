package com.tt.handsomeman.ui.handyman;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.tt.handsomeman.R;
import com.tt.handsomeman.databinding.ActivityHandyManMainScreenBinding;
import com.tt.handsomeman.ui.handyman.jobs.JobsFragment;
import com.tt.handsomeman.ui.handyman.messages.MessagesFragment;
import com.tt.handsomeman.ui.handyman.more.MoreFragment;
import com.tt.handsomeman.ui.handyman.my_projects.MyProjectsFragment;
import com.tt.handsomeman.ui.handyman.notifications.NotificationsFragment;
import com.tt.handsomeman.util.Constants;

public class HandyManMainScreen extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_LOCATION = 0;
    private static final long MIN_TIME_TO_REQUEST_LOCATION = 0; //30s
    private static final float MIN_DISTANCE_TO_REQUEST_LOCATION = 5; //5 meters
    final Fragment fragment1 = new JobsFragment();
    final Fragment fragment2 = new MessagesFragment();
    final Fragment fragment3 = new MyProjectsFragment();
    final Fragment fragment4 = new NotificationsFragment();
    final Fragment fragment5 = new MoreFragment();
    final FragmentManager fm = getSupportFragmentManager();
    private final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
            Constants.Latitude.postValue(location.getLatitude());
            Constants.Longitude.postValue(location.getLongitude());
            Toast.makeText(HandyManMainScreen.this, "Location changed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };
    private ActivityHandyManMainScreenBinding binding;
    private Fragment active = fragment1;
    private LocationManager locationManager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_jobs:
                    fm.beginTransaction().hide(active).show(fragment1).commit();
                    active = fragment1;
                    return true;

                case R.id.navigation_messages:
                    fm.beginTransaction().hide(active).show(fragment2).commit();
                    active = fragment2;
                    return true;

                case R.id.navigation_my_projects:
                    fm.beginTransaction().hide(active).show(fragment3).commit();
                    active = fragment3;
                    return true;

                case R.id.navigation_notifications:
                    fm.beginTransaction().hide(active).show(fragment4).commit();
                    active = fragment4;
                    return true;

                case R.id.navigation_more:
                    fm.beginTransaction().hide(active).show(fragment5).commit();
                    active = fragment5;
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHandyManMainScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = binding.navView;

//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupWithNavController(navView, navController);

        getLastKnownLocation();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        startLocationService();

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        FragmentManipulate();
    }

    private void getLastKnownLocation() {
        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            Constants.Latitude.postValue(location.getLatitude());
                            Constants.Longitude.postValue(location.getLongitude());
                        }
                    }
                });
    }

    private void FragmentManipulate() {
        fm.beginTransaction().add(R.id.nav_host_fragment, fragment5, "5").hide(fragment5).commit();
        fm.beginTransaction().add(R.id.nav_host_fragment, fragment4, "4").hide(fragment4).commit();
        fm.beginTransaction().add(R.id.nav_host_fragment, fragment3, "3").hide(fragment3).commit();
        fm.beginTransaction().add(R.id.nav_host_fragment, fragment2, "2").hide(fragment2).commit();
        fm.beginTransaction().add(R.id.nav_host_fragment, fragment1, "1").commit();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        // BEGIN_INCLUDE(onRequestPermissionsResult)
        if (requestCode == PERMISSION_REQUEST_LOCATION) {
            // Request for camera permission.
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission has been granted. Start camera preview Activity.
                startLocationService();
            } else {
                // Permission request was denied.
                Snackbar.make(binding.container, "Permission is denied",
                        Snackbar.LENGTH_SHORT)
                        .show();
            }
        }
        // END_INCLUDE(onRequestPermissionsResult)
    }

    private void startLocationService() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            // Permission is already available, start camera preview
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_TO_REQUEST_LOCATION, MIN_DISTANCE_TO_REQUEST_LOCATION, locationListener);

            if (locationManager.getAllProviders().contains(LocationManager.NETWORK_PROVIDER)) {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_TO_REQUEST_LOCATION, MIN_DISTANCE_TO_REQUEST_LOCATION, locationListener);
            }
        } else {
            // Permission is missing and must be requested.
            requestLocationPermission();
        }
    }

    private void requestLocationPermission() {
        // Permission has not been granted and must be requested.
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Provide an additional rationale to the user if the permission was not granted
            // and the user would benefit from additional context for the use of the permission.
            // Display a SnackBar with cda button to request the missing permission.
            Snackbar.make(binding.container, "Permission is needed",
                    Snackbar.LENGTH_INDEFINITE).setAction("GRANT IT", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Request the permission
                    ActivityCompat.requestPermissions(HandyManMainScreen.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            PERMISSION_REQUEST_LOCATION);
                }
            }).show();

        } else {
            Snackbar.make(binding.container, "Location is unavailable", Snackbar.LENGTH_SHORT).show();
            // Request the permission. The result will be received in onRequestPermissionResult().
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_LOCATION);
        }
    }

    @Override
    protected void onDestroy() {
        Log.d("Location", "Destroy");
        locationManager.removeUpdates(locationListener);
        Constants.Latitude.removeObservers(this);
        Constants.Longitude.removeObservers(this);
        super.onDestroy();
    }
}
