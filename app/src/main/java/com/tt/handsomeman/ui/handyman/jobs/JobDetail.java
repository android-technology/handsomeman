package com.tt.handsomeman.ui.handyman.jobs;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tt.handsomeman.HandymanApp;
import com.tt.handsomeman.R;
import com.tt.handsomeman.databinding.ActivityJobDetailBinding;
import com.tt.handsomeman.model.CustomerResponse;
import com.tt.handsomeman.model.Job;
import com.tt.handsomeman.model.PaymentMilestone;
import com.tt.handsomeman.ui.BaseAppCompatActivity;
import com.tt.handsomeman.ui.handyman.jobs.bid_job_detail.BidJobDetail;
import com.tt.handsomeman.util.DimensionConverter;
import com.tt.handsomeman.util.SharedPreferencesUtils;
import com.tt.handsomeman.viewmodel.JobsViewModel;

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
            tvBudgetRange, tvJobDeadline, tvJobLocation,
            tvHired, tvPaymentMilestoneCount, tvClientName, tvReviewCount, tvShowClientProfile;
    private Button btnPlaceABid;
    private TableLayout tlMileStone;
    private GoogleMap mMap;
    private ActivityJobDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJobDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        HandymanApp.getComponent().inject(this);
        baseViewModel = new ViewModelProvider(this, viewModelFactory).get(JobsViewModel.class);

        rtReview = binding.ratingBarJobDetail;
        tvJobTitle = binding.jobTitleJobDetail;
        tvJobId = binding.jobIdJobDetail;
        tvJobCreateTime = binding.jobCreateTimeJobDetail;
        tvJobDetail = binding.jobDetailJobDetail;
        tvBudgetRange = binding.budgetRangeJobDetail;
        tvJobDeadline = binding.deadlineJobDetail;
        tvJobLocation = binding.locationJobDetail;
        tvHired = binding.hiredJobDetail;
        tvPaymentMilestoneCount = binding.paymentMileStoneCountJobDetail;
        tvClientName = binding.clientNameJobDetail;
        tvReviewCount = binding.reviewCountJobDetail;
        tvShowClientProfile = binding.showClientProfileJobDetail;
        tlMileStone = binding.paymentMileStoneTableLayoutJobDetail;
        imClientAvatar = binding.clientAvatarJobDetail;
        imIsWish = binding.isWishListImage;
        btnPlaceABid = binding.buttonPlaceBidJobDetail;

        goBack();
        Integer jobId = getIntent().getIntExtra("jobId", 0);
        fetchData(jobId);
        showClientProfile();
        bidJob();

    }

    private void goBack() {
        binding.jobDetailBackButton.setOnClickListener(v -> {
            jobDetail = null;
            onBackPressed();
        });
    }

    private void bidJob() {
        btnPlaceABid.setOnClickListener(v -> {
            Intent intent = new Intent(JobDetail.this, BidJobDetail.class);
            intent.putExtra("jobDetail", jobDetail);
            startActivity(intent);
        });
    }

    private void showClientProfile() {
        tvShowClientProfile.setOnClickListener(v -> {
            Intent intent = new Intent(JobDetail.this, CustomerProfileJobDetail.class);
            intent.putExtra("customerId", jobDetail.getCustomer().getAccountId());
            startActivity(intent);
        });
    }

    private void fetchData(Integer jobId) {
        String authorizationCode = sharedPreferencesUtils.get("token", String.class);
        baseViewModel.fetchJobDetail(authorizationCode, jobId);

        baseViewModel.getJobDetailLiveData().observe(this, jobDetail -> {
            JobDetail.jobDetail = jobDetail;
            if (jobDetail.isBid()) {
                imIsWish.setImageResource(R.drawable.ic_hearted);
            }
            Job job = jobDetail.getJob();
            tvJobTitle.setText(job.getTitle());
            tvJobId.setText(String.valueOf(job.getId()));

            tvJobCreateTime.setText(getResources().getQuantityString(R.plurals.numberOfHour, job.setCreateTimeBinding(), job.setCreateTimeBinding()));
            tvJobDeadline.setText(getResources().getQuantityString(R.plurals.numberOfDay, job.setDeadlineBinding(), job.setDeadlineBinding()));
            tvJobDetail.setText(job.getDetail());
            tvBudgetRange.setText(job.setBudgetRange());
            tvJobLocation.setText(job.getLocation());

            if (job.getStatus().equals("A")) {
                tvHired.setText(getString(R.string.no));
            } else {
                tvHired.setText(getString(R.string.yes));
            }

            List<PaymentMilestone> listPaymentMilestone = jobDetail.getListPaymentMilestone();
            tvPaymentMilestoneCount.setText(String.valueOf(listPaymentMilestone.size()));
            for (int i = 0; i < listPaymentMilestone.size(); i++) {
                TableRow tr = new TableRow(JobDetail.this);
                tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1));

                TextView b = new TextView(JobDetail.this);
                b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                b.setTextColor(getResources().getColor(R.color.text_white_bg));
                b.setTextSize(DimensionConverter.spToPx(getResources().getDimension(R.dimen.design_3_3sp), JobDetail.this));
                b.setGravity(Gravity.START);
                switch ((i + 1) % 10) {
                    case 1:
                        b.setText(getString(R.string.first_milestone, i + 1));
                        break;
                    case 2:
                        b.setText(getString(R.string.second_milestone, i + 1));
                        break;
                    case 3:
                        b.setText(getString(R.string.third_milestone, i + 1));
                        break;
                    default:
                        b.setText(getString(R.string.default_milestone, i + 1));
                        break;
                }

                TextView b2 = new TextView(JobDetail.this);
                b2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1));
                b2.setTextColor(getResources().getColor(R.color.text_white_bg));
                b2.setTextSize(DimensionConverter.spToPx(getResources().getDimension(R.dimen.design_3_3sp), getApplicationContext()));
                b2.setGravity(Gravity.END);
                b2.setText(getString(R.string.percentage, listPaymentMilestone.get(i).getPercentage()));
                tr.addView(b);
                tr.addView(b2);

                tlMileStone.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
            }

            CustomerResponse customerResponse = jobDetail.getCustomer();
            tvClientName.setText(customerResponse.getCustomerName());

            Integer countReview = jobDetail.getCountReviewers();
            tvReviewCount.setText(getResources().getQuantityString(R.plurals.numberOfReview, countReview, countReview));

            rtReview.setRating(jobDetail.getAverageReviewPoint());

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
        });
    }
}