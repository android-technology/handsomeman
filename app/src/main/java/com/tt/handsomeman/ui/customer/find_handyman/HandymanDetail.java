package com.tt.handsomeman.ui.customer.find_handyman;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tt.handsomeman.HandymanApp;
import com.tt.handsomeman.R;
import com.tt.handsomeman.adapter.HandymanReviewAdapter;
import com.tt.handsomeman.adapter.SkillAdapter;
import com.tt.handsomeman.databinding.ActivityHandymanDetailBinding;
import com.tt.handsomeman.model.Skill;
import com.tt.handsomeman.request.HandymanDetailRequest;
import com.tt.handsomeman.response.HandymanDetailResponse;
import com.tt.handsomeman.response.HandymanReviewResponse;
import com.tt.handsomeman.ui.BaseAppCompatActivity;
import com.tt.handsomeman.util.Constants;
import com.tt.handsomeman.util.CustomDividerItemDecoration;
import com.tt.handsomeman.util.DecimalFormat;
import com.tt.handsomeman.util.SharedPreferencesUtils;
import com.tt.handsomeman.viewmodel.CustomerViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class HandymanDetail extends BaseAppCompatActivity<CustomerViewModel> {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    SharedPreferencesUtils sharedPreferencesUtils;
    private TextView tvDistance, accountName, education, about, allProjects, successedProject, countReviews;
    private RatingBar rtCountPoint;
    private Button btnInviteToProject;
    private SkillAdapter skillAdapter;
    private RecyclerView rcvSkill, rcvReview;
    private List<Skill> skillList = new ArrayList<>();
    private HandymanReviewAdapter handymanReviewAdapter;
    private List<HandymanReviewResponse> handymanReviewResponses = new ArrayList<>();
    private ActivityHandymanDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHandymanDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        HandymanApp.getComponent().inject(this);
        baseViewModel = new ViewModelProvider(this, viewModelFactory).get(CustomerViewModel.class);

        tvDistance = binding.textViewHandymanDistance;
        accountName = binding.accountName;
        education = binding.education;
        about = binding.about;
        allProjects = binding.allProjects;
        successedProject = binding.successedProjects;
        btnInviteToProject = binding.buttonInviteToProject;
        rcvSkill = binding.mySkillRecyclerView;
        rcvReview = binding.reviewHandymanDetailRecycleView;
        countReviews = binding.reviewCountHandymanDetail;
        rtCountPoint = binding.ratingBarHandymanDetail;

        goBack();
        createSkillRecyclerView();
        createHandymanReviewRecycleView();
        fetchHandymanDetail();
    }

    private void fetchHandymanDetail() {
        String authorizationCode = sharedPreferencesUtils.get("token", String.class);

        Double lat = Constants.Latitude.getValue();
        Double lng = Constants.Longitude.getValue();
        Integer handymanId = getIntent().getIntExtra("handymanId", 0);

        baseViewModel.fetchHandymanDetail(authorizationCode, new HandymanDetailRequest(lat, lng, handymanId));
        baseViewModel.getHandymanDetailResponseMutableLiveData().observe(this, new Observer<HandymanDetailResponse>() {
            @Override
            public void onChanged(HandymanDetailResponse handymanDetailResponse) {
                int distance = Integer.parseInt(DecimalFormat.formatGetDistance(handymanDetailResponse.getDistance() * 1000));
                tvDistance.setText(HandymanApp.getInstance().getResources().getQuantityString(R.plurals.handymanDistance, distance, distance));

                accountName.setText(handymanDetailResponse.getHandymanName());
                education.setText(handymanDetailResponse.getEducation());
                about.setText(handymanDetailResponse.getDetail());
                allProjects.setText(String.valueOf(handymanDetailResponse.getAllProject()));
                successedProject.setText(String.valueOf(handymanDetailResponse.getSuccessedProject()));
                countReviews.setText(getResources().getQuantityString(R.plurals.numberOfReview, handymanDetailResponse.getCountReviewers(), handymanDetailResponse.getCountReviewers()));
                rtCountPoint.setRating(handymanDetailResponse.getAverageReviewPoint());

                handymanReviewResponses.clear();
                handymanReviewResponses.addAll(handymanDetailResponse.getHandymanReviewResponseList());
                handymanReviewAdapter.notifyDataSetChanged();

                skillList.clear();
                skillList.addAll(handymanDetailResponse.getSkillList());
                skillAdapter.notifyDataSetChanged();
            }
        });
    }

    private void goBack() {
        binding.handymanDetailBackButton.setOnClickListener(v -> onBackPressed());
    }

    private void createHandymanReviewRecycleView() {
        handymanReviewAdapter = new HandymanReviewAdapter(handymanReviewResponses, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcvReview.setLayoutManager(layoutManager);
        rcvReview.setItemAnimator(new DefaultItemAnimator());
        rcvReview.addItemDecoration(new CustomDividerItemDecoration(getResources().getDrawable(R.drawable.recycler_view_divider)));
        rcvReview.setAdapter(handymanReviewAdapter);
    }

    private void createSkillRecyclerView() {
        skillAdapter = new SkillAdapter(skillList, this);
        RecyclerView.LayoutManager layoutManagerPayout = new LinearLayoutManager(this);
        rcvSkill.setLayoutManager(layoutManagerPayout);
        rcvSkill.setItemAnimator(new DefaultItemAnimator());
        rcvSkill.addItemDecoration(new CustomDividerItemDecoration(getResources().getDrawable(R.drawable.recycler_view_divider)));
        rcvSkill.setAdapter(skillAdapter);
    }
}
