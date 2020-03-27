package com.tt.handsomeman.ui.customer.notification;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tt.handsomeman.HandymanApp;
import com.tt.handsomeman.R;
import com.tt.handsomeman.adapter.FileNameAdapter;
import com.tt.handsomeman.databinding.ActivityViewMadeBidBinding;
import com.tt.handsomeman.request.AcceptBidRequest;
import com.tt.handsomeman.response.BidFileResponse;
import com.tt.handsomeman.response.DataBracketResponse;
import com.tt.handsomeman.response.MadeABidNotificationResponse;
import com.tt.handsomeman.response.StandardResponse;
import com.tt.handsomeman.ui.BaseAppCompatActivity;
import com.tt.handsomeman.util.CustomDividerItemDecoration;
import com.tt.handsomeman.util.DecimalFormat;
import com.tt.handsomeman.util.SharedPreferencesUtils;
import com.tt.handsomeman.util.StatusConstant;
import com.tt.handsomeman.viewmodel.NotificationViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

public class ViewMadeBid extends BaseAppCompatActivity<NotificationViewModel> {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    SharedPreferencesUtils sharedPreferencesUtils;
    private TextView jobTitle, bid, letter, whoBid;
    private Button btnAcceptBid;
    private RecyclerView rcvFileName;
    private LinearLayout listFileLayout;
    private FileNameAdapter fileNameAdapter;
    private List<BidFileResponse> bidFileResponseList = new ArrayList<>();
    private boolean isRead, isReadForFirstTime = false;
    private int notificationPos;
    private int jobId;
    private ActivityViewMadeBidBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewMadeBidBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        HandymanApp.getComponent().inject(this);
        baseViewModel = new ViewModelProvider(this, viewModelFactory).get(NotificationViewModel.class);

        bindView();

        Intent intent = getIntent();
        Integer handymanId = intent.getIntExtra("handymanId", 0);
        Integer notificationId = intent.getIntExtra("notificationId", 0);
        String jobTitle = intent.getStringExtra("jobTitle");
        Integer jobBidId = intent.getIntExtra("jobBidId", 0);
        String personBid = intent.getStringExtra("personBid");
        isRead = intent.getBooleanExtra("isRead", false);
        notificationPos = intent.getIntExtra("notificationPos", 0);

        this.jobTitle.setText(jobTitle);
        whoBid.setText(getString(R.string.who_just_made_bid, personBid));
        String token = sharedPreferencesUtils.get("token", String.class);

        getNotificationData(notificationId, jobBidId, token);
        acceptBid(handymanId, token);
    }

    private void markAsRead(Integer notificationId, String token) {
        if (!isRead) {
            baseViewModel.markNotificationAsRead(token, notificationId);
            baseViewModel.getStandardResponseMarkReadMutableLiveData().observe(this, new Observer<StandardResponse>() {
                @Override
                public void onChanged(StandardResponse standardResponse) {
                    if (standardResponse.getStatus().equals(StatusConstant.OK)) {
                        isRead = true;
                        ViewMadeBid.this.isReadForFirstTime = true;
                    }
                }
            });
        }
    }

    private void acceptBid(Integer handymanId, String token) {
        btnAcceptBid.setOnClickListener(v -> {
            Calendar now = Calendar.getInstance();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ZZ", Locale.getDefault());
            String sendTime = formatter.format(now.getTime());

            baseViewModel.acceptBid(token, new AcceptBidRequest(this.jobId, handymanId, sendTime));
            baseViewModel.getStandardResponseAcceptBidMutableLiveData().observe(this, new Observer<StandardResponse>() {
                @Override
                public void onChanged(StandardResponse standardResponse) {
                    Toast.makeText(ViewMadeBid.this, standardResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    if (standardResponse.getStatus().equals(StatusConstant.OK)) {
                        Intent intent = new Intent();
                        intent.putExtra("isRead", isRead);
                        intent.putExtra("notificationPos", notificationPos);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }
            });
        });
    }

    private void getNotificationData(Integer notificationId, Integer jobBidId, String token) {
        baseViewModel.fetchMadeBidNotification(token, jobBidId);
        baseViewModel.getMadeABidNotificationResponseMutableLiveData().observe(this, new Observer<DataBracketResponse<MadeABidNotificationResponse>>() {
            @Override
            public void onChanged(DataBracketResponse<MadeABidNotificationResponse> madeABidNotificationResponseDataBracketResponse) {
                if (madeABidNotificationResponseDataBracketResponse.getStatus().equals(StatusConstant.OK)) {
                    MadeABidNotificationResponse madeABidNotificationResponse = madeABidNotificationResponseDataBracketResponse.getData();
                    bid.setText(getString(R.string.money_currency_string, DecimalFormat.format(madeABidNotificationResponse.getBid())));
                    letter.setText(madeABidNotificationResponse.getBidDescription());

                    jobId = madeABidNotificationResponse.getJobId();

                    if (madeABidNotificationResponse.getBidFileList().size() > 0) {
                        listFileLayout.setVisibility(View.VISIBLE);
                        createRecyclerView();
                        bidFileResponseList.clear();
                        bidFileResponseList.addAll(madeABidNotificationResponse.getBidFileList());
                        fileNameAdapter.notifyDataSetChanged();
                    }

                    if (!isRead) {
                        markAsRead(notificationId, token);
                    }
                } else {
                    Toast.makeText(ViewMadeBid.this, madeABidNotificationResponseDataBracketResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
            }
        });
    }

    private void createRecyclerView() {
        fileNameAdapter = new FileNameAdapter(this, bidFileResponseList);
        RecyclerView.LayoutManager layoutManagerJob = new LinearLayoutManager(this);
        rcvFileName.setLayoutManager(layoutManagerJob);
        rcvFileName.setItemAnimator(new DefaultItemAnimator());
        rcvFileName.addItemDecoration(new CustomDividerItemDecoration(getResources().getDrawable(R.drawable.recycler_view_divider)));
        rcvFileName.setAdapter(fileNameAdapter);
    }

    private void bindView() {
        jobTitle = binding.jobTitle;
        whoBid = binding.whoMadeBid;
        bid = binding.bid;
        letter = binding.bidLetter;
        btnAcceptBid = binding.acceptBid;
        rcvFileName = binding.recyclerFileName;
        listFileLayout = binding.listFileLayout;

        binding.backButton.setOnClickListener(v -> onBackPressed());
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("isRead", isRead);
        intent.putExtra("isReadForFirstTime", this.isReadForFirstTime);
        intent.putExtra("notificationPos", notificationPos);
        setResult(RESULT_OK, intent);
        finish();
    }
}
