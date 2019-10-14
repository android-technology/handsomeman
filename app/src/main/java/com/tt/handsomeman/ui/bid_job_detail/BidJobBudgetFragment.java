package com.tt.handsomeman.ui.bid_job_detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.tt.handsomeman.R;

public class BidJobBudgetFragment extends Fragment {

    private int myBid, highestBid, lowestBid;
    private double budgetReceive, serviceFee;

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

        TextView myBid = rootView.findViewById(R.id.myBidBidJobDetail);
        TextView budgetReceive = rootView.findViewById(R.id.budgetReceiveBidJobDetail);
        TextView serviceFee = rootView.findViewById(R.id.serviceFeeBidJobDetail);
        TextView highestBid = rootView.findViewById(R.id.highestBidJobDetail);
        TextView lowestBid = rootView.findViewById(R.id.lowestBidJobDetail);

        myBid.setText("$" + this.myBid + " ");
        budgetReceive.setText("$" + String.format("%d", (long) this.budgetReceive) + " ");
        serviceFee.setText("$" + String.format("%d", (long) this.serviceFee));
        highestBid.setText(String.valueOf(this.highestBid));
        lowestBid.setText(String.valueOf(this.lowestBid));

        return rootView;
    }
}
