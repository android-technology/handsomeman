package com.tt.handsomeman.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tt.handsomeman.R;

import java.util.List;

public abstract class RadioAdapter<T> extends RecyclerView.Adapter<RadioAdapter.RadioViewHolder> {
    List<T> mItems;
    int mSelectedItem = -1;
    Context mContext;
    private LayoutInflater inflater;

    RadioAdapter(Context context, List<T> items) {
        mContext = context;
        mItems = items;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public void onBindViewHolder(RadioAdapter.RadioViewHolder viewHolder, final int position) {
        viewHolder.mRadio.setChecked(position == mSelectedItem);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @NonNull
    @Override
    public RadioViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = inflater.inflate(R.layout.item_skill_category_name, viewGroup, false);
        return new RadioViewHolder(view);
    }

    class RadioViewHolder extends RecyclerView.ViewHolder {

        RadioButton mRadio;
        TextView mText;

        RadioViewHolder(final View inflate) {
            super(inflate);
            mText = inflate.findViewById(R.id.textViewAddSkillCategoryName);
            mRadio = inflate.findViewById(R.id.radioButtonSkillChosen);
            View.OnClickListener clickListener = (view)->{
                mSelectedItem = getAdapterPosition();
                notifyItemRangeChanged(0, mItems.size());
            };
            itemView.setOnClickListener(clickListener);
            mRadio.setOnClickListener(clickListener);
        }
    }

}