package com.tt.handsomeman.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tt.handsomeman.R;
import com.tt.handsomeman.response.JobDetailCustomerReview;

import java.util.List;

public class CustomerReviewAdapter extends RecyclerView.Adapter<CustomerReviewAdapter.MyViewHolder> {

    private List<JobDetailCustomerReview> jobDetailCustomerReviews;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomerReviewAdapter(Context context, List<JobDetailCustomerReview> jobDetailCustomerReviews) {
        this.jobDetailCustomerReviews = jobDetailCustomerReviews;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = layoutInflater.inflate(R.layout.item_customer_review, parent, false);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerReviewAdapter.MyViewHolder holder, int position) {
        JobDetailCustomerReview jobDetailCustomerReview = jobDetailCustomerReviews.get(position);

//        holder.handymenAvatar.setImageResource();
        holder.handymenName.setText(jobDetailCustomerReview.getHandymanName());
        holder.handymenRating.setRating(jobDetailCustomerReview.getCustomerReview().getVote());
        holder.handymenComment.setText(jobDetailCustomerReview.getCustomerReview().getComment());
    }

    @Override
    public int getItemCount() {
        return jobDetailCustomerReviews.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView handymenAvatar;
        TextView handymenName, handymenComment;
        RatingBar handymenRating;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            handymenAvatar = itemView.findViewById(R.id.handymenReviewCustomerAvatar);
            handymenName = itemView.findViewById(R.id.handymenReviewCustomerName);
            handymenRating = itemView.findViewById(R.id.handymenRatingBarReviewCustomer);
            handymenComment = itemView.findViewById(R.id.handymenReviewCustomerComment);
        }
    }
}
