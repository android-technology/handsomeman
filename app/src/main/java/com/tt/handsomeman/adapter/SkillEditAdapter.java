package com.tt.handsomeman.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SkillEditAdapter extends RecyclerView.Adapter<SkillEditAdapter.SkillEditViewHolder> {
    @NonNull
    @Override
    public SkillEditAdapter.SkillEditViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SkillEditAdapter.SkillEditViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class SkillEditViewHolder extends RecyclerView.ViewHolder {
        public SkillEditViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
