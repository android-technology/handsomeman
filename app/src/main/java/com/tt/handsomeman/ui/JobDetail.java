package com.tt.handsomeman.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tt.handsomeman.HandymanApp;
import com.tt.handsomeman.R;
import com.tt.handsomeman.model.CustomerJobDetail;
import com.tt.handsomeman.model.Job;
import com.tt.handsomeman.model.PaymentMilestone;
import com.tt.handsomeman.ui.bid_job_detail.BidJobDetail;
import com.tt.handsomeman.util.SharedPreferencesUtils;
import com.tt.handsomeman.viewmodel.JobsViewModel;

import java.util.List;

import javax.inject.Inject;

public class JobDetail extends AppCompatActivity {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    SharedPreferencesUtils sharedPreferencesUtils;
    private ImageView clientAvatar;
    private RatingBar rtReview;
    private TextView tvJobTitle, tvJobId, tvJobCreateTime, tvJobDetail,
            tvBudgetRange, tvJobDeadline, tvJobLocation, tvBidRange,
            tvInterviewing, tvHired, tvPaymentMilestoneCount, tvClientName, tvReviewCount, tvShowClientProfile;
    private Button btnPlaceABid;
    private JobsViewModel jobsViewModel;
    private TableLayout tlMileStone;
    private GoogleMap mMap;
    private Job job;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail);

        rtReview = findViewById(R.id.ratingBarJobDetail);
        tvJobTitle = findViewById(R.id.jobTitleJobDetail);
        tvJobId = findViewById(R.id.jobIdJobDetail);
        tvJobCreateTime = findViewById(R.id.jobCreateTimeJobDetail);
        tvJobDetail = findViewById(R.id.jobDetailJobDetail);
        tvBudgetRange = findViewById(R.id.budgetRangeJobDetail);
        tvJobDeadline = findViewById(R.id.deadlineJobDetail);
        tvJobLocation = findViewById(R.id.locationJobDetail);
        tvBidRange = findViewById(R.id.bidRangeJobDetail);
        tvInterviewing = findViewById(R.id.interviewingJobDetail);
        tvHired = findViewById(R.id.hiredJobDetail);
        tvPaymentMilestoneCount = findViewById(R.id.paymentMileStoneCountJobDetail);
        tvClientName = findViewById(R.id.clientNameJobDetail);
        tvReviewCount = findViewById(R.id.reviewCountJobDetail);
        tvShowClientProfile = findViewById(R.id.showClientProfileJobDetail);
        tlMileStone = findViewById(R.id.paymentMileStoneTableLayoutJobDetail);
        clientAvatar = findViewById(R.id.clientAvatarJobDetail);
        btnPlaceABid = findViewById(R.id.buttonPlaceBidJobDetail);

        HandymanApp.getComponent().inject(this);

        jobsViewModel = ViewModelProviders.of(this, viewModelFactory).get(JobsViewModel.class);

        findViewById(R.id.jobDetailBackButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Integer jobId = getIntent().getIntExtra("jobId", 0);
        fetchData(jobId);
        showClientProfile();

        btnPlaceABid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JobDetail.this, BidJobDetail.class);
                intent.putExtra("job", job);
                startActivity(intent);
            }
        });
    }

    private void showClientProfile() {
        tvShowClientProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JobDetail.this, CustomerProfileJobDetail.class);
                intent.putExtra("customerId", jobsViewModel.getJobDetailLiveData().getValue().getJob().getCustomerId());
                startActivity(intent);
            }
        });
    }

    private void fetchData(Integer jobId) {
        String authorizationCode = sharedPreferencesUtils.get("token", String.class);
        jobsViewModel.fetchJobDetail(authorizationCode, jobId);

        jobsViewModel.getJobDetailLiveData().observe(this, new Observer<com.tt.handsomeman.model.JobDetail>() {
            @Override
            public void onChanged(com.tt.handsomeman.model.JobDetail jobDetail) {
                job = jobDetail.getJob();
                tvJobTitle.setText(job.getTitle());
                tvJobId.setText(" " + job.getId());
                tvJobCreateTime.setText(job.setCreateTimeBinding(job.getCreateTime()));
                tvJobDetail.setText(job.getDetail());
                tvBudgetRange.setText(job.setBudgetRange(job.getBudgetMin(), job.getBudgetMax()));
                tvJobDeadline.setText(job.setDeadlineBinding(job.getDeadline()));
                tvJobLocation.setText(job.getLocation());
                tvBidRange.setText(job.setBidRange(job.getBidMin(), job.getBidMax()));
                tvInterviewing.setText(String.valueOf(job.getInterviewing()));

                if (job.getStatus().equals("A")) {
                    tvHired.setText("No");
                } else {
                    tvHired.setText("Yes");
                }

                List<PaymentMilestone> listPaymentMilestone = jobDetail.getListPaymentMilestone();
                tvPaymentMilestoneCount.setText(String.valueOf(listPaymentMilestone.size()));
                for (int i = 0; i < listPaymentMilestone.size(); i++) {
                    TableRow tr = new TableRow(JobDetail.this);
                    tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1));

                    TextView b = new TextView(JobDetail.this);
                    b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                    b.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
                    b.setTextColor(getResources().getColor(R.color.textColor));
                    b.setGravity(Gravity.START);

                    switch ((i + 1) % 10) {
                        case 1:
                            b.setText(i + 1 + "st milestone");
                            break;
                        case 2:
                            b.setText(i + 1 + "nd milestone");
                            break;
                        case 3:
                            b.setText(i + 1 + "rd milestone");
                            break;
                        default:
                            b.setText(i + 1 + "th milestone");
                            break;
                    }

                    TextView b2 = new TextView(JobDetail.this);
                    b2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1));
                    b2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
                    b2.setTextColor(getResources().getColor(R.color.textColor));
                    b2.setGravity(Gravity.END);
                    b2.setText(listPaymentMilestone.get(i).getPercentage() + "%");

                    tr.addView(b);
                    tr.addView(b2);

                    tlMileStone.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
                }

                CustomerJobDetail customerJobDetail = jobDetail.getCustomerJobDetail();
                tvClientName.setText(customerJobDetail.getCustomerName());

                Integer countReview = jobDetail.getCountReviewers();
                tvReviewCount.setText(String.valueOf(countReview));

                Float averageReviewPoint = jobDetail.getAverageReviewPoint();
                if (averageReviewPoint == null) {
                    rtReview.setRating(0);
                } else {
                    rtReview.setRating(averageReviewPoint);
                }

                Double lat = job.getLat();
                Double lng = job.getLng();

                SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.map);
                mapFragment.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap googleMap) {
                        mMap = googleMap;

                        LatLng jobLocation = new LatLng(lat, lng);
                        mMap.addMarker(new MarkerOptions().position(jobLocation).title(job.getLocation()));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(jobLocation, 15));
                        mMap.getUiSettings().setScrollGesturesEnabled(false);
                    }
                });
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        jobsViewModel.clearSubscriptions();
    }
}
