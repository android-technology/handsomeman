package com.tt.handsomeman.ui.bid_job_detail;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tt.handsomeman.R;
import com.tt.handsomeman.model.PaymentMilestone;

import java.util.List;

public class BidJobLetterReviewFragment extends Fragment {

    private static String edtIntroduceValue;
    private static TextView tvLetter;
    private String jobTitle;
    private int myBudget, paymentMileStoneCount;
    private TableLayout tblPaymentMileStoneBidJobDetail;

    public static BidJobLetterReviewFragment newInstance(String jobTitle, int myBudget, int paymentMileStoneCount) {
        BidJobLetterReviewFragment bidJobLetterReviewFragment = new BidJobLetterReviewFragment();
        Bundle args = new Bundle();
        args.putString("jobTitle", jobTitle);
        args.putInt("myBudget", myBudget);
        args.putInt("paymentMileStoneCount", paymentMileStoneCount);
        bidJobLetterReviewFragment.setArguments(args);
        return bidJobLetterReviewFragment;
    }

    public static void getEditTextValue(String edtValue) {
        edtIntroduceValue = edtValue;
        tvLetter.setText(edtIntroduceValue);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        jobTitle = getArguments().getString("jobTitle");
        myBudget = getArguments().getInt("myBudget");
        paymentMileStoneCount = getArguments().getInt("paymentMileStoneCount");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.item_bid_job_detail_letter_review, container, false);

        TextView jobTitle = rootView.findViewById(R.id.jobTitleBidJobDetail);
        TextView myBudget = rootView.findViewById(R.id.myBudgetBidJobDetail);
        TextView paymentMileStoneCount = rootView.findViewById(R.id.paymentMileStoneCountBidJobDetail);
        tvLetter = rootView.findViewById(R.id.introduceYourSelfTextView);
        tblPaymentMileStoneBidJobDetail = rootView.findViewById(R.id.paymentMileStoneTableLayoutBidJobDetail);

        jobTitle.setText(this.jobTitle);
        myBudget.setText("$" + this.myBudget + " ");
        paymentMileStoneCount.setText(String.valueOf(this.paymentMileStoneCount));

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

        return rootView;
    }
}
