package com.tt.handsomeman.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tt.handsomeman.databinding.ItemLoadingProgressBinding;
import com.tt.handsomeman.databinding.ItemMessageReceiverBinding;
import com.tt.handsomeman.databinding.ItemMessageSenderBinding;
import com.tt.handsomeman.response.MessageResponse;
import com.tt.handsomeman.util.TimeParseUtil;

import java.text.ParseException;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int SENDER = 1;
    private static final int RECEIVER = 2;
    private int RANDOM = 3;

    private List<MessageResponse> messageResponseList;
    private LayoutInflater layoutInflater;
    private Context context;
    private ItemMessageSenderBinding senderBinding;
    private ItemMessageReceiverBinding receiverBinding;

    public MessageAdapter(List<MessageResponse> messageResponseList, Context context) {
        this.messageResponseList = messageResponseList;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case SENDER:
                senderBinding = ItemMessageSenderBinding.inflate(layoutInflater, parent, false);
                view = senderBinding.getRoot();
                return new SenderViewHolder(view);
            case RECEIVER:
                receiverBinding = ItemMessageReceiverBinding.inflate(layoutInflater, parent, false);
                view = receiverBinding.getRoot();
                return new ReceiverViewHolder(view);
            default:
                ItemLoadingProgressBinding progressBinding = ItemLoadingProgressBinding.inflate(layoutInflater, parent, false);
                view = progressBinding.getRoot();
                return new ProgressViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MessageResponse messageResponse = messageResponseList.get(position);

        switch (holder.getItemViewType()) {
            case SENDER:
                SenderViewHolder senderViewHolder = (SenderViewHolder) holder;
                try {
                    senderViewHolder.tvSendTime.setText(TimeParseUtil.setSendTimeManipulate(messageResponse.getSendTime()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                senderViewHolder.tvMessageBody.setText(messageResponse.getBody());
                break;
            case RECEIVER:
                ReceiverViewHolder receiverViewHolder = (ReceiverViewHolder) holder;
                try {
                    receiverViewHolder.tvSendTime.setText(TimeParseUtil.setSendTimeManipulate(messageResponse.getSendTime()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                receiverViewHolder.tvMessageBody.setText(messageResponse.getBody());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return messageResponseList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return messageResponseList.get(position).getType();
    }

    public void addLoading() {
        messageResponseList.add(new MessageResponse(RANDOM++));
        notifyItemInserted(messageResponseList.size() - 1);
    }

    public void removeLoading() {
        int position = messageResponseList.size() - 1;
        MessageResponse item = messageResponseList.get(position);
        if (item.getType() != SENDER && item.getType() != RECEIVER) {
            messageResponseList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        ProgressViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public class SenderViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imgAvatar;
        private final TextView tvSendTime;
        private final TextView tvMessageBody;

        SenderViewHolder(@NonNull View itemView) {
            super(itemView);

            imgAvatar = senderBinding.avatarMessage;
            tvSendTime = senderBinding.sendTimeMessage;
            tvMessageBody = senderBinding.messageBody;
        }
    }

    public class ReceiverViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imgAvatar;
        private final TextView tvSendTime;
        private final TextView tvMessageBody;

        ReceiverViewHolder(@NonNull View itemView) {
            super(itemView);

            imgAvatar = receiverBinding.avatarMessage;
            tvSendTime = receiverBinding.sendTimeMessage;
            tvMessageBody = receiverBinding.messageBody;
        }
    }
}
