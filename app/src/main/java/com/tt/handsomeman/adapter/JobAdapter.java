package com.tt.handsomeman.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    public JobAdapter(Context context, List<Job> jobList) {
        this.jobList = jobList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = layoutInflater.inflate(R.layout.item_job, parent, false);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String myFormat = "h"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        Job job = jobList.get(position);

        String location = job.getLocation();
        if (location.length() > 10) {
            location = job.getLocation().substring(0, 11) + "...";
        }

        holder.tvJobTitle.setText(job.getTitle());
        holder.tvCreateTime.setText(sdf.format(job.getCreateTime()) + " hours ago");
        holder.tvBudget.setText("$" + job.getBudgetMin() + " - $" + job.getBudgetMax());
        holder.tvLocationName.setText(location);
        holder.tvDeadline.setText(sdf.format(job.getDeadline()) + " days left");
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvJobTitle, tvCreateTime, tvBudget, tvLocationName, tvDeadline;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvJobTitle = itemView.findViewById(R.id.textViewJobName);
            tvCreateTime = itemView.findViewById(R.id.textViewCreateTime);
            tvBudget = itemView.findViewById(R.id.textViewBudgetRange);
            tvLocationName = itemView.findViewById(R.id.textViewLocationName);
            tvDeadline = itemView.findViewById(R.id.textViewDeadLine);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, tvJobTitle.getText(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
