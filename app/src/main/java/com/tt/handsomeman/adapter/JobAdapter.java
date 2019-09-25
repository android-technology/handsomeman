package com.tt.handsomeman.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.tt.handsomeman.R;
import com.tt.handsomeman.model.Job;

import java.util.List;

public class JobAdapter extends BaseRecycleViewAdapter {

    private List<Job> jobList;
    private Context context;

    public JobAdapter(Context context, List<Job> jobList) {
        this.jobList = jobList;
        this.context = context;
    }

    @Override
    protected Object getObjForPosition(int position) {
        return jobList.get(position);
    }

    @Override
    protected int getLayoutIdForPosition(int position) {
        return R.layout.item_job;
    }

    @Override
    protected View.OnClickListener getOnItemClickListener(int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, jobList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }
}
