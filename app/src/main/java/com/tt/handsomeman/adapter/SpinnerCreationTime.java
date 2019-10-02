package com.tt.handsomeman.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tt.handsomeman.R;

public class SpinnerCreationTime extends BaseAdapter {
    private Context context;
    private String[] time;
    private LayoutInflater inflater;

    public SpinnerCreationTime(Context context, String[] time) {
        this.context = context;
        this.time = time;
        inflater = LayoutInflater.from(context);
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
        view = inflater.inflate(R.layout.spinner_item_standard, null);
        TextView typeName = view.findViewById(R.id.textViewSpinnerPayout);
        typeName.setText(time[i]);
        return view;
    }
}
