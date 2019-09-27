package com.tt.handsomeman.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tt.handsomeman.R;
import com.tt.handsomeman.model.Job;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.MyViewHolder> {

    private List<Job> jobList;
    private LayoutInflater layoutInflater;
    private Context context;

    private OnItemClickListener mListener;

    public JobAdapter(Context context, List<Job> jobList) {
        this.jobList = jobList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = layoutInflater.inflate(R.layout.item_job, parent, false);
        return new MyViewHolder(item, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Job job = jobList.get(position);

        holder.tvJobTitle.setText(job.getTitle());
        holder.tvCreateTime.setText(job.setCreateTimeBinding(job.getCreateTime()));
        holder.tvBudget.setText(job.setBudgetRange(job.getBudgetMin(), job.getBudgetMax()));
        holder.tvLocationName.setText(job.setLocationBinding(job.getLocation()));
        holder.tvDeadline.setText(job.setDeadlineBinding(job.getDeadline()));
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvJobTitle, tvCreateTime, tvBudget, tvLocationName, tvDeadline;
        ImageButton btnJobDetail;
        MyViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            tvJobTitle = itemView.findViewById(R.id.textViewJobName);
            tvCreateTime = itemView.findViewById(R.id.textViewCreateTime);
            tvBudget = itemView.findViewById(R.id.textViewBudgetRange);
            tvLocationName = itemView.findViewById(R.id.textViewLocationName);
            tvDeadline = itemView.findViewById(R.id.textViewDeadLine);

            btnJobDetail = itemView.findViewById(R.id.imageButtonJobDetail);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

            btnJobDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}