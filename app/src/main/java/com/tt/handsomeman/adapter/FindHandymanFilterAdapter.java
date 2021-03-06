package com.tt.handsomeman.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.signature.MediaStoreSignature;
import com.tt.handsomeman.HandymanApp;
import com.tt.handsomeman.R;
import com.tt.handsomeman.databinding.ItemHandymanFilterBinding;
import com.tt.handsomeman.response.HandymanResponse;
import com.tt.handsomeman.util.DecimalFormat;

import java.util.List;

public class FindHandymanFilterAdapter extends RecyclerView.Adapter<FindHandymanFilterAdapter.FindHandymanFilterViewHolder> {

    private Context context;
    private List<HandymanResponse> handymanResponseList;
    private LayoutInflater inflater;
    private String authorizationCode;
    private OnItemClickListener mListener;
    private ItemHandymanFilterBinding binding;

    public FindHandymanFilterAdapter(Context context,
                                     List<HandymanResponse> handymanResponseList,
                                     String authorizationCode) {
        this.context = context;
        this.handymanResponseList = handymanResponseList;
        this.authorizationCode = authorizationCode;
        this.inflater = LayoutInflater.from(context);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public FindHandymanFilterViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                           int viewType) {
        binding = ItemHandymanFilterBinding.inflate(inflater, parent, false);
        View view = binding.getRoot();
        return new FindHandymanFilterViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull FindHandymanFilterViewHolder holder,
                                 int position) {
        HandymanResponse handymanResponse = handymanResponseList.get(position);
        int distance = Integer.parseInt(DecimalFormat.formatGetDistance(handymanResponse.getDistance() * 1000));

        holder.tvHandymanName.setText(handymanResponse.getName());
        holder.tvDistance.setText(HandymanApp.getInstance().getResources().getQuantityString(R.plurals.handymanDistance, distance, distance));
        holder.rtHandymanReviewPoint.setRating(handymanResponse.getAverageReviewPoint());

        GlideUrl glideUrl = new GlideUrl((handymanResponse.getAvatar()),
                new LazyHeaders.Builder().addHeader("Authorization", authorizationCode).build());

        Glide.with(context)
                .load(glideUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .circleCrop()
                .placeholder(R.drawable.custom_progressbar)
                .error(R.drawable.logo)
                .signature(new MediaStoreSignature("", handymanResponse.getUpdateDate(), 0))
                .into(holder.imgAvatar);
    }

    @Override
    public int getItemCount() {
        return handymanResponseList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    class FindHandymanFilterViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imgAvatar;
        private final TextView tvHandymanName, tvDistance;
        private final RatingBar rtHandymanReviewPoint;
        private final ImageButton ibViewHandymanDetail;

        FindHandymanFilterViewHolder(@NonNull View itemView,
                                     final OnItemClickListener listener) {
            super(itemView);
            imgAvatar = binding.imageButtonHandymanAvatar;
            tvHandymanName = binding.textViewHandymanNameItem;
            tvDistance = binding.textViewHandymanDistanceItem;
            rtHandymanReviewPoint = binding.ratingBarReviewHandymanItem;
            ibViewHandymanDetail = binding.imageButtonHandymanDetail;

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

            ibViewHandymanDetail.setOnClickListener(new View.OnClickListener() {
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
