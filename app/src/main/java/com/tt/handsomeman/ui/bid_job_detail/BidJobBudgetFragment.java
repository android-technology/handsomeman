package com.tt.handsomeman.ui.bid_job_detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.tt.handsomeman.R;

public class BidJobBudgetFragment extends Fragment {

    private String myBid, budgetReceive, serviceFee, highestBid, lowestBid;

    public static BidJobBudgetFragment newInstance(String myBid, String budgetReceive, String serviceFee, String highestBid, String lowestBid) {
        BidJobBudgetFragment bidJobBudgetFragment = new BidJobBudgetFragment();
        Bundle args = new Bundle();
        args.putString("myBid", myBid);
        args.putString("budgetReceive", budgetReceive);
        args.putString("serviceFee", serviceFee);
        args.putString("highestBid", highestBid);
        args.putString("lowestBid", lowestBid);
        bidJobBudgetFragment.setArguments(args);
        return bidJobBudgetFragment;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myBid = getArguments().getString("myBid");
        budgetReceive = getArguments().getString("budgetReceive");
        serviceFee = getArguments().getString("serviceFee");
        highestBid = getArguments().getString("highestBid");
        lowestBid = getArguments().getString("lowestBid");
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

        myBid.setText(this.myBid);
        budgetReceive.setText(this.budgetReceive);
        serviceFee.setText(this.serviceFee);
        highestBid.setText(this.highestBid);
        lowestBid.setText(this.lowestBid);

        return rootView;
    }
}
