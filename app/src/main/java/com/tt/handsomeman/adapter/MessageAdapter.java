package com.tt.handsomeman.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.tt.handsomeman.R;
import com.tt.handsomeman.response.ConversationResponse;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class MessageAdapter extends RecyclerSwipeAdapter<MessageAdapter.MyViewHolder> {

    private List<ConversationResponse> conversationResponsesList;
    private LayoutInflater layoutInflater;
    private Context context;

    public MessageAdapter(List<ConversationResponse> conversationResponsesList, Context context) {
        this.conversationResponsesList = conversationResponsesList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MessageAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = layoutInflater.inflate(R.layout.item_conversation, parent, false);
        return new MessageAdapter.MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.MyViewHolder holder, int position) {
        ConversationResponse conversationResponse = conversationResponsesList.get(position);

        String myFormat = "dd.MM.yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
//        String sendTime = sdf.format(conversationResponse.getSendTime());

        holder.tvAccountName.setText(conversationResponse.getAccountName());
        holder.tvLatestMessage.setText(conversationResponse.getLatestMessage());
        holder.tvLatestMessageSendTime.setText("Today");

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

        public MyViewHolder(@NonNull View itemView) {
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
                    Toast.makeText(context, "Click: " + tvAccountName.getText().toString(), Toast.LENGTH_SHORT).show();
                    closeSwipeLayout();
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
