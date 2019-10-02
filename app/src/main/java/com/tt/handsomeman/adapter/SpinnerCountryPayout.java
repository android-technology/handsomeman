package com.tt.handsomeman.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tt.handsomeman.R;

public class SpinnerCountryPayout extends BaseAdapter {

    private Context context;
    private String[] country;
    private LayoutInflater inflater;

    public SpinnerCountryPayout(Context context, String[] country) {
        this.context = context;
        this.country = country;
        inflater = LayoutInflater.from(context);
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.spinner_item_standard, null);
        TextView countryName = view.findViewById(R.id.textViewSpinnerPayout);
        countryName.setText(country[i]);
        return view;
    }
}
