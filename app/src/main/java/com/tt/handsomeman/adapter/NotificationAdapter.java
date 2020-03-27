package com.tt.handsomeman.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tt.handsomeman.HandymanApp;
import com.tt.handsomeman.R;
import com.tt.handsomeman.databinding.ItemNotificationBinding;
import com.tt.handsomeman.response.NotificationResponse;
import com.tt.handsomeman.util.NotificationType;

import java.text.ParseException;
import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int MADE_A_BID = 1;
    private static final int PAID_PAYMENT = 2;
    private static final int ACCEPT_BID = 3;

    private Context context;
    private List<NotificationResponse> notificationList;
    private LayoutInflater layoutInflater;
    private ItemNotificationBinding binding;
    private OnItemClickListener listener;

    public NotificationAdapter(Context context, List<NotificationResponse> notificationList) {
        this.context = context;
        this.notificationList = notificationList;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemNotificationBinding.inflate(layoutInflater, parent, false);
        View view = binding.getRoot();
        switch (viewType) {
            case MADE_A_BID:
                return new MadeBidViewHolder(view, listener);
            case PAID_PAYMENT:
                return new PaidPaymentViewHolder(view, listener);
            case ACCEPT_BID:
                return new AcceptBidViewHolder(view, listener);
            default:
                throw new IllegalStateException("unsupported item type");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NotificationResponse notification = notificationList.get(position);

        switch (holder.getItemViewType()) {
            case MADE_A_BID:
                MadeBidViewHolder madeBidViewHolder = (MadeBidViewHolder) holder;
                madeBidViewHolder.tvNotificationBody.setText(HandymanApp.getInstance().getResources().getString(R.string.made_bid_notification, notification.getSenderName(), notification.getNotificationDescription()));
                try {
                    madeBidViewHolder.tvSendTime.setText(notification.setSendTimeManipulate(notification.getCreationTime()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if (!notification.getRead()) {
                    madeBidViewHolder.tvNotificationBody.setTypeface(madeBidViewHolder.tvNotificationBody.getTypeface(), Typeface.BOLD);
                }
                break;
            case ACCEPT_BID:
                AcceptBidViewHolder acceptBidViewHolder = (AcceptBidViewHolder) holder;
                acceptBidViewHolder.tvNotificationBody.setText(HandymanApp.getInstance().getResources().getString(R.string.accept_bid_notification, notification.getSenderName(), notification.getNotificationDescription()));
                try {
                    acceptBidViewHolder.tvSendTime.setText(notification.setSendTimeManipulate(notification.getCreationTime()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if (!notification.getRead()) {
                    acceptBidViewHolder.tvNotificationBody.setTypeface(acceptBidViewHolder.tvNotificationBody.getTypeface(), Typeface.BOLD);
                }
                break;

            case PAID_PAYMENT:
                PaidPaymentViewHolder paidPaymentViewHolder = (PaidPaymentViewHolder) holder;
                paidPaymentViewHolder.tvNotificationBody.setText(HandymanApp.getInstance().getResources().getString(R.string.paid_payment_notification, notification.getSenderName(), notification.getNotificationDescription()));
                try {
                    paidPaymentViewHolder.tvSendTime.setText(notification.setSendTimeManipulate(notification.getCreationTime()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if (!notification.getRead()) {
                    paidPaymentViewHolder.tvNotificationBody.setTypeface(paidPaymentViewHolder.tvNotificationBody.getTypeface(), Typeface.BOLD);
                }

                paidPaymentViewHolder.itemView.setOnClickListener(v -> {
                    Toast.makeText(context, String.valueOf(notification.getContentId()), Toast.LENGTH_SHORT).show();
                });
                break;
        }
    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    @Override
    public int getItemViewType(int position) {
        String notificationType = notificationList.get(position).getNotificationType();
        switch (NotificationType.valueOf(notificationType)) {
            case MADE_A_BID:
                return MADE_A_BID;
            case PAID_PAYMENT:
                return PAID_PAYMENT;
            case ACCEPT_BID:
                return ACCEPT_BID;
            default:
                return 0;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class MadeBidViewHolder extends RecyclerView.ViewHolder {
        private final ImageView accountAvatar;
        private final TextView tvSendTime;
        private final TextView tvNotificationBody;

        MadeBidViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            accountAvatar = binding.accountAvatarNotification;
            tvSendTime = binding.pushTimeNotification;
            tvNotificationBody = binding.bodyNotification;

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
        }
    }

    public class AcceptBidViewHolder extends RecyclerView.ViewHolder {
        private final ImageView accountAvatar;
        private final TextView tvSendTime;
        private final TextView tvNotificationBody;

        AcceptBidViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            accountAvatar = binding.accountAvatarNotification;
            tvSendTime = binding.pushTimeNotification;
            tvNotificationBody = binding.bodyNotification;

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
        }
    }

    public class PaidPaymentViewHolder extends RecyclerView.ViewHolder {
        private final ImageView accountAvatar;
        private final TextView tvSendTime;
        private final TextView tvNotificationBody;

        PaidPaymentViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            accountAvatar = binding.accountAvatarNotification;
            tvSendTime = binding.pushTimeNotification;
            tvNotificationBody = binding.bodyNotification;

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
        }
    }
}
