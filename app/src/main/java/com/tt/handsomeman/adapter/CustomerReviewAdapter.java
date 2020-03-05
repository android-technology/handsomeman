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

import com.tt.handsomeman.databinding.ItemReviewBinding;
import com.tt.handsomeman.response.JobDetailCustomerReview;

import java.util.List;

public class CustomerReviewAdapter extends RecyclerView.Adapter<CustomerReviewAdapter.CustomerReviewViewHolder> {

    private List<JobDetailCustomerReview> jobDetailCustomerReviews;
    private LayoutInflater layoutInflater;
    private Context context;
    private ItemReviewBinding binding;

    public CustomerReviewAdapter(Context context, List<JobDetailCustomerReview> jobDetailCustomerReviews) {
        this.jobDetailCustomerReviews = jobDetailCustomerReviews;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CustomerReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemReviewBinding.inflate(layoutInflater, parent, false);
        View item = binding.getRoot();
        return new CustomerReviewViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerReviewViewHolder holder, int position) {
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

    class CustomerReviewViewHolder extends RecyclerView.ViewHolder {

        private final ImageView handymenAvatar;
        private final TextView handymenName, handymenComment;
        private final RatingBar handymenRating;

        CustomerReviewViewHolder(@NonNull View itemView) {
            super(itemView);

            handymenAvatar = binding.reviewAvatar;
            handymenName = binding.reviewNameItem;
            handymenRating = binding.ratingBarReviewItem;
            handymenComment = binding.reviewCommentItem;
        }
    }
}
