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

public class ConversationAdapter extends RecyclerSwipeAdapter<ConversationAdapter.ConversationViewHolder> {

    private List<ConversationResponse> conversationResponsesList;
    private LayoutInflater layoutInflater;
    private Context context;
    private SwipeLayout deleteSwipeLayout;

    private OnItemClickListener mListener;

    public ConversationAdapter(List<ConversationResponse> conversationResponsesList, Context context) {
        this.conversationResponsesList = conversationResponsesList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setOnItemClickListener(ConversationAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public ConversationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = layoutInflater.inflate(R.layout.item_conversation, parent, false);
        return new ConversationViewHolder(item, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ConversationViewHolder holder, int position) {
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

    public void deleteConversation(int position) {
        mItemManger.removeShownLayouts(deleteSwipeLayout);
        conversationResponsesList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
        mItemManger.closeAllItems();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onItemDelete(int position);
    }

    public class ConversationViewHolder extends RecyclerView.ViewHolder {

        final TextView tvAccountName, tvLatestMessage, tvLatestMessageSendTime;
        final SwipeLayout swipeLayout;
        final LinearLayout layoutConversation, layoutDelete;
        int conversationId;

        public ConversationViewHolder(@NonNull View itemView, final ConversationAdapter.OnItemClickListener listener) {
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
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemDelete(position);
                            deleteSwipeLayout = swipeLayout;
                        }
                    }

                }
            });
        }
    }
}
