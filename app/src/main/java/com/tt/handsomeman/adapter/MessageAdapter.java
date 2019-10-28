package com.tt.handsomeman.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tt.handsomeman.R;
import com.tt.handsomeman.response.MessageResponse;

import java.text.ParseException;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MessageResponse> messageResponseList;
    private LayoutInflater layoutInflater;
    private Context context;

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
            case 1:
                view = layoutInflater.inflate(R.layout.item_message_sender, parent, false);
                return new SenderViewHolder(view);
            case 2:
                view = layoutInflater.inflate(R.layout.item_message_receiver, parent, false);
                return new ReceiverViewHolder(view);
            default:
                throw new IllegalStateException("unsupported item type");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MessageResponse messageResponse = messageResponseList.get(position);

        switch (holder.getItemViewType()) {
            case 1:
                SenderViewHolder senderViewHolder = (SenderViewHolder) holder;
                try {
                    senderViewHolder.tvSendTime.setText(messageResponse.setSendTimeManipulate(messageResponse.getSendTime()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                senderViewHolder.tvMessageBody.setText(messageResponse.getBody());
                break;
            case 2:
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

        ImageView imgAvatar;
        TextView tvSendTime;
        TextView tvMessageBody;

        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);

            tvSendTime = itemView.findViewById(R.id.sendTimeMessage);
            tvMessageBody = itemView.findViewById(R.id.messageBody);
        }
    }

    public class ReceiverViewHolder extends RecyclerView.ViewHolder {

        ImageView imgAvatar;
        TextView tvSendTime;
        TextView tvMessageBody;

        public ReceiverViewHolder(@NonNull View itemView) {
            super(itemView);

            tvSendTime = itemView.findViewById(R.id.sendTimeMessage);
            tvMessageBody = itemView.findViewById(R.id.messageBody);
        }
    }
}
