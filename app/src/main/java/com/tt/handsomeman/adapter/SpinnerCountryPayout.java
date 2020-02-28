package com.tt.handsomeman.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tt.handsomeman.databinding.SpinnerItemStandardBinding;

public class SpinnerCountryPayout extends BaseAdapter {

    private Context context;
    private String[] country;
    private LayoutInflater layoutInflater;
    private SpinnerItemStandardBinding binding;

    public SpinnerCountryPayout(Context context, String[] country) {
        this.context = context;
        this.country = country;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return country.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        binding = SpinnerItemStandardBinding.inflate(layoutInflater, viewGroup, false);
        view = binding.getRoot();
        TextView countryName = binding.textViewSpinnerItemName;
        countryName.setText(country[position]);
        return view;
    }
}
