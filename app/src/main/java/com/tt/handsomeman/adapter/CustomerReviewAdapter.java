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
import com.tt.handsomeman.response.CustomerReviewResponse;

import java.util.List;

public class CustomerReviewAdapter extends RecyclerView.Adapter<CustomerReviewAdapter.CustomerReviewViewHolder> {

    private List<CustomerReviewResponse> customerReviewResponses;
    private LayoutInflater layoutInflater;
    private Context context;
    private ItemReviewBinding binding;

    public CustomerReviewAdapter(Context context,
                                 List<CustomerReviewResponse> customerReviewResponses) {
        this.customerReviewResponses = customerReviewResponses;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CustomerReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                       int viewType) {
        binding = ItemReviewBinding.inflate(layoutInflater, parent, false);
        View item = binding.getRoot();
        return new CustomerReviewViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerReviewViewHolder holder,
                                 int position) {
        CustomerReviewResponse customerReviewResponse = customerReviewResponses.get(position);

//        holder.handymenAvatar.setImageResource();
        holder.handymenName.setText(customerReviewResponse.getHandymanName());
        holder.handymenRating.setRating(customerReviewResponse.getVote());
        holder.handymenComment.setText(customerReviewResponse.getComment());
    }

    @Override
    public int getItemCount() {
        return customerReviewResponses.size();
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
