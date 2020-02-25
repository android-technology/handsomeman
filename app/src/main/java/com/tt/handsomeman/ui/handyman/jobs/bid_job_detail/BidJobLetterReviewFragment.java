package com.tt.handsomeman.ui.handyman.jobs.bid_job_detail;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.tt.handsomeman.HandymanApp;
import com.tt.handsomeman.R;
import com.tt.handsomeman.databinding.FragmentBidJobDetailLetterReviewBinding;
import com.tt.handsomeman.model.PaymentMilestone;
import com.tt.handsomeman.ui.BaseFragment;
import com.tt.handsomeman.ui.handyman.HandyManMainScreen;
import com.tt.handsomeman.util.DimensionConverter;
import com.tt.handsomeman.util.MessageConstant;
import com.tt.handsomeman.util.SharedPreferencesUtils;
import com.tt.handsomeman.viewmodel.JobsViewModel;

import java.util.List;

import javax.inject.Inject;

public class BidJobLetterReviewFragment extends BaseFragment<JobsViewModel, FragmentBidJobDetailLetterReviewBinding> {

    private static String introduceValue, myBidValue;
    private static double serviceFeeValue;
    @SuppressLint("StaticFieldLeak")
    private static TextView tvLetter, tvMyBudget;
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    SharedPreferencesUtils sharedPreferencesUtils;
    private String jobTitle;
    private int jobId, paymentMileStoneCount;
    private FragmentBidJobDetailLetterReviewBinding binding;

    static BidJobLetterReviewFragment newInstance(Integer jobId, String jobTitle, int paymentMileStoneCount) {
        BidJobLetterReviewFragment bidJobLetterReviewFragment = new BidJobLetterReviewFragment();
        Bundle args = new Bundle();
        args.putInt("jobId", jobId);
        args.putString("jobTitle", jobTitle);
        args.putInt("paymentMileStoneCount", paymentMileStoneCount);
        bidJobLetterReviewFragment.setArguments(args);
        return bidJobLetterReviewFragment;
    }

    static void setTextViewIntroduceValue(String edtValue) {
        introduceValue = edtValue;
        tvLetter.setText(introduceValue);
    }

    static void setTextViewMyBidValue(String edtMyBidValue, double tvServiceFeeValue) {
        myBidValue = edtMyBidValue;
        serviceFeeValue = tvServiceFeeValue;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        jobId = getArguments().getInt("jobId");
        jobTitle = getArguments().getString("jobTitle");
        paymentMileStoneCount = getArguments().getInt("paymentMileStoneCount");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        HandymanApp.getComponent().inject(this);
        baseViewModel = new ViewModelProvider(this, viewModelFactory).get(JobsViewModel.class);
        binding = FragmentBidJobDetailLetterReviewBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tvJobTitle = binding.jobTitleBidJobDetail;
        tvMyBudget = binding.myBudgetBidJobDetail;
        TextView tvPaymentMileStoneCount = binding.paymentMileStoneCountBidJobDetail;
        tvLetter = binding.introduceYourSelfTextView;
        TableLayout tblPaymentMileStoneBidJobDetail = binding.paymentMileStoneTableLayoutBidJobDetail;

        BidJobDetail bidJobDetail = (BidJobDetail) getActivity();
        Button btnSubmit = bidJobDetail.activityBidJobDetailBinding.submitBidJobDetail;

        tvJobTitle.setText(jobTitle);
        tvMyBudget.setText("$" + myBidValue);
        tvPaymentMileStoneCount.setText(String.valueOf(paymentMileStoneCount));

        List<PaymentMilestone> listPaymentMilestone = BidJobDetail.jobDetail.getListPaymentMilestone();
        for (int i = 0; i < listPaymentMilestone.size(); i++) {
            TableRow tr = new TableRow(getContext());
            tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1));

            TextView b = new TextView(getContext());
            b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
            b.setTextColor(getResources().getColor(R.color.text_white_bg));
            b.setTextSize(DimensionConverter.spToPx(getResources().getDimension(R.dimen.design_3_3sp), view.getContext()));
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

            TextView b2 = new TextView(getContext());
            b2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1));
            b2.setTextColor(getResources().getColor(R.color.text_white_bg));
            b2.setTextSize(DimensionConverter.spToPx(getResources().getDimension(R.dimen.design_3_3sp), view.getContext()));
            b2.setGravity(Gravity.END);
            b2.setText(listPaymentMilestone.get(i).getPercentage() + "%");

            tr.addView(b);
            tr.addView(b2);

            tblPaymentMileStoneBidJobDetail.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String authorizationCode = sharedPreferencesUtils.get("token", String.class);
                baseViewModel.addJobBid(authorizationCode, Double.parseDouble(myBidValue), introduceValue, null, jobId, serviceFeeValue);
                baseViewModel.getMessageResponse().observe(getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                        if (s.equals(MessageConstant.JOB_IS_BIDDEN_SUCCESSFULLY)) {
                            Intent intent = new Intent(getActivity(), HandyManMainScreen.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("radioButtonChoice", 1);
                            startActivity(intent);
                        }
                    }
                });
            }
        });
    }

    @Override
    public void onDetach() {
        tvLetter = null;
        tvMyBudget = null;
        super.onDetach();
    }
}
