package com.tt.handsomeman.ui.handyman;

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
import com.tt.handsomeman.ui.BaseAppCompatActivity;
import com.tt.handsomeman.ui.handyman.bid_job_detail.BidJobDetail;
import com.tt.handsomeman.util.SharedPreferencesUtils;
import com.tt.handsomeman.viewmodel.JobsViewModel;

import java.text.ParseException;
import java.util.List;

import javax.inject.Inject;

public class JobDetail extends BaseAppCompatActivity<JobsViewModel> {

    private static com.tt.handsomeman.model.JobDetail jobDetail;
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    SharedPreferencesUtils sharedPreferencesUtils;
    private ImageView imClientAvatar, imIsWish;
    private RatingBar rtReview;
    private TextView tvJobTitle, tvJobId, tvJobCreateTime, tvJobDetail,
            tvBudgetRange, tvJobDeadline, tvJobLocation, tvBidRange,
            tvInterviewing, tvHired, tvPaymentMilestoneCount, tvClientName, tvReviewCount, tvShowClientProfile;
    private Button btnPlaceABid;
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
        imClientAvatar = findViewById(R.id.clientAvatarJobDetail);
        imIsWish = findViewById(R.id.isWishListImage);
        btnPlaceABid = findViewById(R.id.buttonPlaceBidJobDetail);

        HandymanApp.getComponent().inject(this);

        baseViewModel = ViewModelProviders.of(this, viewModelFactory).get(JobsViewModel.class);

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
                intent.putExtra("jobDetail", jobDetail);
                startActivity(intent);
            }
        });
    }

    private void showClientProfile() {
        tvShowClientProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JobDetail.this, CustomerProfileJobDetail.class);
                intent.putExtra("customerId", baseViewModel.getJobDetailLiveData().getValue().getJob().getCustomerId());
                startActivity(intent);
            }
        });
    }

    private void fetchData(Integer jobId) {
        String authorizationCode = sharedPreferencesUtils.get("token", String.class);
        baseViewModel.fetchJobDetail(authorizationCode, jobId);

        baseViewModel.getJobDetailLiveData().observe(this, new Observer<com.tt.handsomeman.model.JobDetail>() {
            @Override
            public void onChanged(com.tt.handsomeman.model.JobDetail jobDetail) {
                JobDetail.jobDetail = jobDetail;
                if (jobDetail.getIsBid()) {
                    imIsWish.setImageResource(R.drawable.ic_hearted);
                }
                job = jobDetail.getJob();
                tvJobTitle.setText(job.getTitle());
                tvJobId.setText(String.valueOf(job.getId()));

                tvJobCreateTime.setText(getResources().getQuantityString(R.plurals.numberOfHour, job.setCreateTimeBinding(job.getCreateTime()), job.setCreateTimeBinding(job.getCreateTime())));
                tvJobDetail.setText(job.getDetail());
                tvBudgetRange.setText(job.setBudgetRange(job.getBudgetMin(), job.getBudgetMax()));
                try {
                    tvJobDeadline.setText(getResources().getQuantityString(R.plurals.numberOfHour, job.setDeadlineBinding(job.getDeadline()), job.setDeadlineBinding(job.getDeadline())));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
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
                tvReviewCount.setText(getResources().getQuantityString(R.plurals.numberOfReview, countReview, countReview));

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
}