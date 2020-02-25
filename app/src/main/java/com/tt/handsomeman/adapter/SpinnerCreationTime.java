package com.tt.handsomeman.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tt.handsomeman.databinding.SpinnerItemStandardBinding;

public class SpinnerCreationTime extends BaseAdapter {
    private Context context;
    private String[] time;
    private LayoutInflater layoutInflater;
    private SpinnerItemStandardBinding binding;

    public SpinnerCreationTime(Context context, String[] time) {
        this.context = context;
        this.time = time;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return time.length;
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
        binding = SpinnerItemStandardBinding.inflate(layoutInflater, viewGroup, false);
        view = binding.getRoot();
        TextView typeName = binding.textViewSpinnerPayout;
        typeName.setText(time[i]);
        return view;
    }
}
