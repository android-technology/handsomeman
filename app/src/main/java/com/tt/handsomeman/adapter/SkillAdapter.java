package com.tt.handsomeman.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tt.handsomeman.R;
import com.tt.handsomeman.model.Skill;

import java.util.List;

public class SkillAdapter extends RecyclerView.Adapter<SkillAdapter.SkillViewHolder> {

    private List<Skill> skillList;
    private LayoutInflater layoutInflater;
    private Context context;

    public SkillAdapter(List<Skill> skillList, Context context) {
        this.skillList = skillList;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public SkillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = layoutInflater.inflate(R.layout.item_skill, parent, false);
        return new SkillAdapter.SkillViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillViewHolder holder, int position) {
        Skill skill = skillList.get(position);

        holder.tvSkillName.setText(skill.getName());
    }

    @Override
    public int getItemCount() {
        return skillList.size();
    }

    public class SkillViewHolder extends RecyclerView.ViewHolder {

        final TextView tvSkillName;

        public SkillViewHolder(@NonNull View itemView) {
            super(itemView);

            tvSkillName = itemView.findViewById(R.id.textViewItemSkillName);
        }
    }
}
