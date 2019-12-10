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
import com.tt.handsomeman.response.HandymanReviewResponse;

import java.util.List;

public class HandymanReviewAdapter extends RecyclerView.Adapter<HandymanReviewAdapter.HandymanReviewViewHolder> {

    private List<HandymanReviewResponse> handymanReviewResponseList;
    private LayoutInflater layoutInflater;
    private Context context;

    public HandymanReviewAdapter(List<HandymanReviewResponse> handymanReviewResponseList, Context context) {
        this.handymanReviewResponseList = handymanReviewResponseList;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public HandymanReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = layoutInflater.inflate(R.layout.item_review, parent, false);
        return new HandymanReviewViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull HandymanReviewViewHolder holder, int position) {
        HandymanReviewResponse handymanReviewResponse = handymanReviewResponseList.get(position);

        holder.customerName.setText(handymanReviewResponse.getCustomerName());
        holder.customerRating.setRating(handymanReviewResponse.getHandymanReview().getVote());
        holder.customerComment.setText(handymanReviewResponse.getHandymanReview().getComment());
    }

    @Override
    public int getItemCount() {
        return handymanReviewResponseList.size();
    }

    public class HandymanReviewViewHolder extends RecyclerView.ViewHolder {

        final ImageView customerAvatar;
        final TextView customerName, customerComment;
        final RatingBar customerRating;

        public HandymanReviewViewHolder(@NonNull View itemView) {
            super(itemView);

            customerAvatar = itemView.findViewById(R.id.reviewAvatar);
            customerName = itemView.findViewById(R.id.reviewNameItem);
            customerRating = itemView.findViewById(R.id.ratingBarReviewItem);
            customerComment = itemView.findViewById(R.id.reviewCommentItem);
        }
    }
}
