package com.tt.handsomeman.adapter;

import android.content.Context;
import android.widget.Toast;

import com.tt.handsomeman.HandymanApp;
import com.tt.handsomeman.R;
import com.tt.handsomeman.model.Category;

import java.util.List;

public class CategorySelectionAdapter extends RadioAdapter<Category> {
    public CategorySelectionAdapter(Context context,
                                    List<Category> items) {
        super(context, items);
    }

    @Override
    public void onBindViewHolder(RadioAdapter.RadioViewHolder viewHolder,
                                 int position) {
        super.onBindViewHolder(viewHolder, position);
        viewHolder.mText.setText(mItems.get(position).getName());
    }

    public Category getSelected() {
        if (mSelectedItem < 0) {
            Toast.makeText(mContext, HandymanApp.getInstance().getString(R.string.please_choose_category), Toast.LENGTH_SHORT).show();
            return null;
        } else {
            return mItems.get(mSelectedItem);
        }
    }
}