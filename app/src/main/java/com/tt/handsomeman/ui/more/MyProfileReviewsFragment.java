package com.tt.handsomeman.ui.more;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tt.handsomeman.HandymanApp;
import com.tt.handsomeman.R;
import com.tt.handsomeman.adapter.HandymanReviewAdapter;
import com.tt.handsomeman.response.HandymanReviewProfile;
import com.tt.handsomeman.response.HandymanReviewResponse;
import com.tt.handsomeman.response.JobDetailProfile;
import com.tt.handsomeman.util.SharedPreferencesUtils;
import com.tt.handsomeman.viewmodel.JobsViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MyProfileReviewsFragment extends Fragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    SharedPreferencesUtils sharedPreferencesUtils;
    private JobsViewModel jobsViewModel;
    private TextView countReviewers;
    private RatingBar countPoint;
    private HandymanReviewAdapter handymanReviewAdapter;
    private List<HandymanReviewResponse> handymanReviewResponseList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        HandymanApp.getComponent().inject(this);
        jobsViewModel = ViewModelProviders.of(this, viewModelFactory).get(JobsViewModel.class);
        return inflater.inflate(R.layout.fragment_my_profile_reviews, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        countReviewers = view.findViewById(R.id.reviewCountHandymanProfile);
        countPoint = view.findViewById(R.id.ratingBarHandymanProfile);

        createReviewRecyclerView(view);
        fetchData();
    }

    private void createReviewRecyclerView(@NonNull View view) {
        RecyclerView rcvReview = view.findViewById(R.id.reviewHandymanProfile);
        handymanReviewAdapter = new HandymanReviewAdapter(handymanReviewResponseList, getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvReview.setLayoutManager(layoutManager);
        rcvReview.setItemAnimator(new DefaultItemAnimator());

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rcvReview.getContext(), ((LinearLayoutManager) layoutManager).getOrientation());
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.recycler_view_divider));
        rcvReview.addItemDecoration(dividerItemDecoration);

        rcvReview.setAdapter(handymanReviewAdapter);
    }

    private void fetchData() {
        String authorizationCode = sharedPreferencesUtils.get("token", String.class);

        jobsViewModel.fetchHandymanReview(authorizationCode);
        jobsViewModel.getHandymanReviewProfileLiveData().observe(this, new Observer<HandymanReviewProfile>() {
            @Override
            public void onChanged(HandymanReviewProfile handymanReviewProfile) {
                countReviewers.setText("(" + handymanReviewProfile.getCountReviewers() + " Reviewers)");
                countPoint.setRating(handymanReviewProfile.getAverageReviewPoint());

                handymanReviewResponseList.clear();
                handymanReviewResponseList.addAll(handymanReviewProfile.getHandymanReviewResponseList());
                handymanReviewAdapter.notifyDataSetChanged();
            }
        });
    }
}
