package com.tt.handsomeman.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tt.handsomeman.R;
import com.tt.handsomeman.model.Skill;

import java.util.List;

public class SkillEditAdapter extends RecyclerView.Adapter<SkillEditAdapter.SkillEditViewHolder> {

    private List<Skill> skillList;
    private LayoutInflater layoutInflater;
    private Context context;

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void deleteSkill(int position);
    }

    public SkillEditAdapter(List<Skill> skillList, Context context) {
        this.skillList = skillList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public SkillEditAdapter.SkillEditViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = layoutInflater.inflate(R.layout.item_skill_edit, parent, false);
        return new SkillEditViewHolder(item, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillEditAdapter.SkillEditViewHolder holder, int position) {
        Skill skill = skillList.get(position);
        holder.tvSkillName.setText(skill.getName());
    }

    @Override
    public int getItemCount() {
        return skillList.size();
    }

    public class SkillEditViewHolder extends RecyclerView.ViewHolder {

        private TextView tvSkillName;
        private ImageButton ibDeleteSkill;

        public SkillEditViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            tvSkillName = itemView.findViewById(R.id.textViewItemSkillNameEdit);
            ibDeleteSkill = itemView.findViewById(R.id.imageButtonDeleteSkillEdit);

            ibDeleteSkill.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.deleteSkill(position);
                        }
                    }
                }
            });
        }
    }
}
