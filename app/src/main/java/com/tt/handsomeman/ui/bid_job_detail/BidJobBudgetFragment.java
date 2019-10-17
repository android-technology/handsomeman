package com.tt.handsomeman.ui.bid_job_detail;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.tt.handsomeman.R;

import java.text.DecimalFormat;

public class BidJobBudgetFragment extends Fragment {

    private int myBid, highestBid, lowestBid, myBidValue;
    private double budgetReceive, serviceFee;
    private ImageButton ibCheckButtonBudget;

    public static BidJobBudgetFragment newInstance(int myBid, int highestBid, int lowestBid, double budgetReceive, double serviceFee) {
        BidJobBudgetFragment bidJobBudgetFragment = new BidJobBudgetFragment();
        Bundle args = new Bundle();
        args.putInt("myBid", myBid);
        args.putDouble("budgetReceive", budgetReceive);
        args.putDouble("serviceFee", serviceFee);
        args.putInt("highestBid", highestBid);
        args.putInt("lowestBid", lowestBid);
        bidJobBudgetFragment.setArguments(args);
        return bidJobBudgetFragment;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myBid = getArguments().getInt("myBid");
        budgetReceive = getArguments().getDouble("budgetReceive");
        serviceFee = getArguments().getDouble("serviceFee");
        highestBid = getArguments().getInt("highestBid");
        lowestBid = getArguments().getInt("lowestBid");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.item_bid_job_detail_budget, container, false);

        EditText edtMyBid = rootView.findViewById(R.id.myBidBidJobDetail);
        TextView tvBudgetReceive = rootView.findViewById(R.id.budgetReceiveBidJobDetail);
        TextView tvServiceFee = rootView.findViewById(R.id.serviceFeeBidJobDetail);
        TextView tvHighestBid = rootView.findViewById(R.id.highestBidJobDetail);
        TextView tvLowestBid = rootView.findViewById(R.id.lowestBidJobDetail);
        ibCheckButtonBudget = getActivity().findViewById(R.id.imageButtonCheckBudgetBidJobDetail);

        edtMyBid.setText(String.valueOf(myBid));
        tvBudgetReceive.setText("$" + String.format("%d", (long) this.budgetReceive) + " ");
        tvServiceFee.setText(String.format("%d", (long) this.serviceFee));
        tvHighestBid.setText(String.valueOf(highestBid));
        tvLowestBid.setText(String.valueOf(lowestBid));

        edtMyBid.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                DecimalFormat df = new DecimalFormat("#.00");
                if (!edtMyBid.getText().toString().trim().equals("")) {
                    myBidValue = Integer.parseInt(edtMyBid.getText().toString().trim());
                    if (myBidValue > highestBid) {
                        ibCheckButtonBudget.setEnabled(false);
                        edtMyBid.setError(getResources().getString(R.string.lower_price_waring));
                    } else if (myBidValue < lowestBid) {
                        ibCheckButtonBudget.setEnabled(false);
                        edtMyBid.setError(getResources().getString(R.string.higher_price_warning));
                    } else {
                        tvBudgetReceive.setText("$" + df.format(myBidValue * 0.9) + " ");
                        tvServiceFee.setText(df.format(myBidValue * 0.1));
                        ibCheckButtonBudget.setEnabled(true);
                    }
                } else {
                    ibCheckButtonBudget.setEnabled(false);
                    edtMyBid.setError(getResources().getString(R.string.please_enter));
                }
            }
        });

        ibCheckButtonBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BidJobLetterReviewFragment.setTextViewMyBidValue(edtMyBid.getText().toString().trim(), Double.parseDouble(tvServiceFee.getText().toString()));
                if (BidJobDetail.mPager.getCurrentItem() < BidJobDetail.NUM_PAGES - 1) {
                    BidJobDetail.mPager.setCurrentItem(BidJobDetail.mPager.getCurrentItem() + 1);
                }
            }
        });

        return rootView;
    }
}
