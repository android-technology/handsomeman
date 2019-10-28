package com.tt.handsomeman.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.tt.handsomeman.R;
import com.tt.handsomeman.response.ConversationResponse;

import java.text.ParseException;
import java.util.List;

public class ConversationAdapter extends RecyclerSwipeAdapter<ConversationAdapter.MyViewHolder> {

    private List<ConversationResponse> conversationResponsesList;
    private LayoutInflater layoutInflater;
    private Context context;

    private OnItemClickListener mListener;

    public ConversationAdapter(List<ConversationResponse> conversationResponsesList, Context context) {
        this.conversationResponsesList = conversationResponsesList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setOnItemClickListener(ConversationAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    @NonNull
    @Override
    public ConversationAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = layoutInflater.inflate(R.layout.item_conversation, parent, false);
        return new ConversationAdapter.MyViewHolder(item, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ConversationAdapter.MyViewHolder holder, int position) {
        ConversationResponse conversationResponse = conversationResponsesList.get(position);

        holder.tvAccountName.setText(conversationResponse.getAccountName());
        holder.tvLatestMessage.setText(conversationResponse.getLatestMessage());
        try {
            holder.tvLatestMessageSendTime.setText(conversationResponse.setSendTimeManipulate(conversationResponse.getSendTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.conversationId = conversationResponse.getConversationId();

        mItemManger.bindView(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return conversationResponsesList.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipeLayoutMessage;
    }

    public void closeSwipeLayout() {
        mItemManger.closeAllItems();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvAccountName, tvLatestMessage, tvLatestMessageSendTime;
        SwipeLayout swipeLayout;
        LinearLayout layoutConversation, layoutDelete;
        int conversationId;

        public MyViewHolder(@NonNull View itemView, final ConversationAdapter.OnItemClickListener listener) {
            super(itemView);
            tvAccountName = itemView.findViewById(R.id.accountNameConversation);
            tvLatestMessage = itemView.findViewById(R.id.latestMessageConversation);
            tvLatestMessageSendTime = itemView.findViewById(R.id.latestMessageSendTimeConversation);
            layoutConversation = itemView.findViewById(R.id.linearLayoutConversation);
            layoutDelete = itemView.findViewById(R.id.linearLayoutDeleteConversation);
            swipeLayout = itemView.findViewById(R.id.swipeLayoutMessage);
            swipeLayout.setClickToClose(true);

            layoutConversation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    closeSwipeLayout();
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

            layoutDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemManger.removeShownLayouts(swipeLayout);
                    conversationResponsesList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    notifyItemRangeChanged(getAdapterPosition(), getItemCount());
                    mItemManger.closeAllItems();
                }
            });
        }
    }
}
