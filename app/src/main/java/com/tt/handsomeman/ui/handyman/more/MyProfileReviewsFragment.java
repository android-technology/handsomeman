package com.tt.handsomeman.ui.handyman.more;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tt.handsomeman.HandymanApp;
import com.tt.handsomeman.R;
import com.tt.handsomeman.adapter.HandymanReviewAdapter;
import com.tt.handsomeman.response.HandymanReviewProfile;
import com.tt.handsomeman.response.HandymanReviewResponse;
import com.tt.handsomeman.ui.BaseFragment;
import com.tt.handsomeman.util.CustomDividerItemDecoration;
import com.tt.handsomeman.util.SharedPreferencesUtils;
import com.tt.handsomeman.viewmodel.HandymanViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MyProfileReviewsFragment extends BaseFragment<HandymanViewModel> {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    SharedPreferencesUtils sharedPreferencesUtils;
    private TextView countReviewers;
    private RatingBar rtCountPoint;
    private HandymanReviewAdapter handymanReviewAdapter;
    private List<HandymanReviewResponse> handymanReviewResponseList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        HandymanApp.getComponent().inject(this);
        baseViewModel = new ViewModelProvider(this, viewModelFactory).get(HandymanViewModel.class);
        return inflater.inflate(R.layout.fragment_my_profile_reviews, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        countReviewers = view.findViewById(R.id.reviewCountHandymanProfile);
        rtCountPoint = view.findViewById(R.id.ratingBarHandymanProfile);

        createReviewRecyclerView(view);
        fetchData();
    }

    private void createReviewRecyclerView(@NonNull View view) {
        RecyclerView rcvReview = view.findViewById(R.id.reviewHandymanProfile);
        handymanReviewAdapter = new HandymanReviewAdapter(handymanReviewResponseList, getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvReview.setLayoutManager(layoutManager);
        rcvReview.setItemAnimator(new DefaultItemAnimator());
        rcvReview.addItemDecoration(new CustomDividerItemDecoration(getResources().getDrawable(R.drawable.recycler_view_divider)));
        rcvReview.setAdapter(handymanReviewAdapter);
    }

    private void fetchData() {
        String authorizationCode = sharedPreferencesUtils.get("token", String.class);

        baseViewModel.fetchHandymanReview(authorizationCode);
        baseViewModel.getHandymanReviewProfileLiveData().observe(getViewLifecycleOwner(), new Observer<HandymanReviewProfile>() {
            @Override
            public void onChanged(HandymanReviewProfile handymanReviewProfile) {
                countReviewers.setText(getResources().getQuantityString(R.plurals.numberOfReview, handymanReviewProfile.getCountReviewers(), handymanReviewProfile.getCountReviewers()));
                Float averageReviewPoint = handymanReviewProfile.getAverageReviewPoint();
                if (averageReviewPoint == null) {
                    rtCountPoint.setRating(0);
                } else {
                    rtCountPoint.setRating(averageReviewPoint);
                }

                handymanReviewResponseList.clear();
                handymanReviewResponseList.addAll(handymanReviewProfile.getHandymanReviewResponseList());
                handymanReviewAdapter.notifyDataSetChanged();
            }
        });
    }
}
