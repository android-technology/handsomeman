package com.tt.handsomeman.ui.bid_job_detail;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tt.handsomeman.R;
import com.tt.handsomeman.model.JobDetail;
import com.tt.handsomeman.model.PaymentMilestone;
import com.tt.handsomeman.ui.HandyManMainScreen;

import java.util.List;

public class BidJobLetterReviewFragment extends Fragment {

    private static String introduceValue, myBidValue;
    private static double serviceFeeValue;
    @SuppressLint("StaticFieldLeak")
    private static TextView tvLetter, tvMyBudget;
    private String jobTitle;
    private int jobId, paymentMileStoneCount;
    private TableLayout tblPaymentMileStoneBidJobDetail;
    private Button btnSubmit;

    public static BidJobLetterReviewFragment newInstance(Integer jobId, String jobTitle, int paymentMileStoneCount) {
        BidJobLetterReviewFragment bidJobLetterReviewFragment = new BidJobLetterReviewFragment();
        Bundle args = new Bundle();
        args.putInt("jobId", jobId);
        args.putString("jobTitle", jobTitle);
        args.putInt("paymentMileStoneCount", paymentMileStoneCount);
        bidJobLetterReviewFragment.setArguments(args);
        return bidJobLetterReviewFragment;
    }

    public static void setTextViewIntroduceValue(String edtValue) {
        introduceValue = edtValue;
    }

    public static void setTextViewMyBidValue(String edtMyBidValue, double tvServiceFeeValue) {
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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.item_bid_job_detail_letter_review, container, false);

        TextView tvJobTitle = rootView.findViewById(R.id.jobTitleBidJobDetail);
        tvMyBudget = rootView.findViewById(R.id.myBudgetBidJobDetail);
        TextView tvPaymentMileStoneCount = rootView.findViewById(R.id.paymentMileStoneCountBidJobDetail);
        tvLetter = rootView.findViewById(R.id.introduceYourSelfTextView);
        tblPaymentMileStoneBidJobDetail = rootView.findViewById(R.id.paymentMileStoneTableLayoutBidJobDetail);
        btnSubmit = getActivity().findViewById(R.id.submitBidJobDetail);

        tvJobTitle.setText(jobTitle);
        tvMyBudget.setText("$" + myBidValue);
        tvLetter.setText(introduceValue);
        tvPaymentMileStoneCount.setText(String.valueOf(paymentMileStoneCount));

        List<PaymentMilestone> listPaymentMilestone = BidJobDetail.jobDetail.getListPaymentMilestone();
        for (int i = 0; i < listPaymentMilestone.size(); i++) {
            TableRow tr = new TableRow(getContext());
            tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1));

            TextView b = new TextView(getContext());
            b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
            b.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
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

            TextView b2 = new TextView(getContext());
            b2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1));
            b2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            b2.setTextColor(getResources().getColor(R.color.textColor));
            b2.setGravity(Gravity.END);
            b2.setText(listPaymentMilestone.get(i).getPercentage() + "%");

            tr.addView(b);
            tr.addView(b2);

            tblPaymentMileStoneBidJobDetail.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), HandyManMainScreen.class));
                getActivity().finish();
            }
        });

        return rootView;
    }
}
