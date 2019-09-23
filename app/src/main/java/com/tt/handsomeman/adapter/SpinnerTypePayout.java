package com.tt.handsomeman.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tt.handsomeman.R;

public class SpinnerTypePayout extends BaseAdapter {
    private Context context;
    private String[] type;
    private LayoutInflater inflater;

    public SpinnerTypePayout(Context context, String[] type) {
        this.context = context;
        this.type = type;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return type.length;
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
        if (view == null) {
            view = inflater.inflate(R.layout.spinner_signup_payout, null);
            TextView typeName = view.findViewById(R.id.textViewSpinnerPayout);
            typeName.setText(type[i]);
        }
        return view;
    }
}
