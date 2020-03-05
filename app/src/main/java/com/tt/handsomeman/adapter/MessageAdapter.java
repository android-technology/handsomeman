package com.tt.handsomeman.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tt.handsomeman.databinding.ItemMessageReceiverBinding;
import com.tt.handsomeman.databinding.ItemMessageSenderBinding;
import com.tt.handsomeman.response.MessageResponse;

import java.text.ParseException;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int SENDER = 1;
    private static final int RECEIVER = 2;


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
                throw new IllegalStateException("unsupported item type");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MessageResponse messageResponse = messageResponseList.get(position);

        switch (holder.getItemViewType()) {
            case SENDER:
                SenderViewHolder senderViewHolder = (SenderViewHolder) holder;
                try {
                    senderViewHolder.tvSendTime.setText(messageResponse.setSendTimeManipulate(messageResponse.getSendTime()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                senderViewHolder.tvMessageBody.setText(messageResponse.getBody());
                break;
            case RECEIVER:
                ReceiverViewHolder receiverViewHolder = (ReceiverViewHolder) holder;
                try {
                    receiverViewHolder.tvSendTime.setText(messageResponse.setSendTimeManipulate(messageResponse.getSendTime()));
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
