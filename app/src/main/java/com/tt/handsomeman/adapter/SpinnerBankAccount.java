package com.tt.handsomeman.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tt.handsomeman.HandymanApp;
import com.tt.handsomeman.R;
import com.tt.handsomeman.databinding.SpinnerItemStandardBinding;
import com.tt.handsomeman.response.PayoutResponse;

import java.util.List;

public class SpinnerBankAccount extends BaseAdapter {

    private Context context;
    private List<PayoutResponse> payoutResponseList;
    private LayoutInflater layoutInflater;
    private SpinnerItemStandardBinding binding;

    public SpinnerBankAccount(Context context, List<PayoutResponse> payoutResponseList) {
        this.context = context;
        this.payoutResponseList = payoutResponseList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return payoutResponseList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        binding = SpinnerItemStandardBinding.inflate(layoutInflater, parent, false);
        convertView = binding.getRoot();
        TextView tvLastPayoutNumber = binding.textViewSpinnerItemName;
        tvLastPayoutNumber.setText(HandymanApp.getInstance().getString(R.string.account_ends_with, payoutResponseList.get(position).getPayoutLastNumber()));
        return convertView;
    }
}
