package com.tt.handsomeman.ui.handyman.jobs.bid_job_detail;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tt.handsomeman.R;
import com.tt.handsomeman.databinding.FragmentBidJobDetailBudgetBinding;
import com.tt.handsomeman.util.DecimalFormat;

public class BidJobBudgetFragment extends Fragment {

    private int myBid, highestBid, lowestBid, myBidValue;
    private double budgetReceive, serviceFee;
    private ImageButton ibCheckButtonBudget;
    private FragmentBidJobDetailBudgetBinding binding;

    static BidJobBudgetFragment newInstance(int myBid, int highestBid, int lowestBid, double budgetReceive, double serviceFee) {
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
        binding = FragmentBidJobDetailBudgetBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText edtMyBid = binding.myBidBidJobDetail;
        TextView tvBudgetReceive = binding.budgetReceiveBidJobDetail;
        TextView tvServiceFee = binding.serviceFeeBidJobDetail;
        TextView tvHighestBid = binding.highestBidJobDetail;
        TextView tvLowestBid = binding.lowestBidJobDetail;

        BidJobDetail bidJobDetail = (BidJobDetail) getActivity();
        ibCheckButtonBudget = bidJobDetail.activityBidJobDetailBinding.imageButtonCheckBudgetBidJobDetail;

        edtMyBid.setText(String.valueOf(myBid));
        tvBudgetReceive.setText(getString(R.string.money_currency_string, DecimalFormat.format(this.budgetReceive)));
        tvServiceFee.setText(DecimalFormat.format(this.serviceFee));
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
                if (!edtMyBid.getText().toString().trim().equals("")) {
                    myBidValue = Integer.parseInt(edtMyBid.getText().toString().trim());
                    if (myBidValue > highestBid) {
                        ibCheckButtonBudget.setEnabled(false);
                        edtMyBid.setError(getResources().getString(R.string.lower_price_waring));
                    } else if (myBidValue < lowestBid) {
                        ibCheckButtonBudget.setEnabled(false);
                        edtMyBid.setError(getResources().getString(R.string.higher_price_warning));
                    } else {
                        tvBudgetReceive.setText(getString(R.string.money_currency_string, DecimalFormat.format(myBidValue * 0.9)));
                        tvServiceFee.setText(DecimalFormat.format(myBidValue * 0.1));
                        ibCheckButtonBudget.setEnabled(true);
                    }
                } else {
                    ibCheckButtonBudget.setEnabled(false);
                    edtMyBid.setError(getResources().getString(R.string.please_input));
                }
            }
        });

        ibCheckButtonBudget.setOnClickListener(v -> {
            BidJobLetterReviewFragment.setTextViewMyBidValue(edtMyBid.getText().toString().trim(), Double.parseDouble(tvServiceFee.getText().toString()));
            if (BidJobDetail.mPager.getCurrentItem() < BidJobDetail.NUM_PAGES - 1) {
                BidJobDetail.mPager.setCurrentItem(BidJobDetail.mPager.getCurrentItem() + 1);
            }
        });

    }
}
