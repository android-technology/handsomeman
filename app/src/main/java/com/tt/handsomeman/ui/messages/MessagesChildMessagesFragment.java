package com.tt.handsomeman.ui.messages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tt.handsomeman.R;
import com.tt.handsomeman.adapter.MessageAdapter;
import com.tt.handsomeman.response.ConversationResponse;
import com.tt.handsomeman.util.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.FadeInLeftAnimator;

public class MessagesChildMessagesFragment extends Fragment {
    private MessageAdapter messageAdapter;
    private List<ConversationResponse> conversationResponseList = new ArrayList<>();
    private MutableLiveData<Boolean> isScroll = new MutableLiveData<>();

    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            switch (newState) {
                case RecyclerView.SCROLL_STATE_DRAGGING:
                    isScroll.setValue(true);
                    break;
                case RecyclerView.SCROLL_STATE_SETTLING:
                    isScroll.setValue(false);
                    break;

            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_messages_child_messages, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        for (int i = 0; i < 7; i++) {
            ConversationResponse conversationResponse = new ConversationResponse("Test " + (i + 1), "This is the first message");
            conversationResponseList.add(conversationResponse);
        }

        createMessageRecycleView(view);

        isScroll.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    messageAdapter.closeSwipeLayout();
                }
            }
        });
    }

    private void createMessageRecycleView(View view) {
        RecyclerView rcvMessage = view.findViewById(R.id.recycleViewMessages);
        messageAdapter = new MessageAdapter(conversationResponseList, getContext());
        RecyclerView.LayoutManager layoutManagerMessage = new LinearLayoutManager(getContext());
        rcvMessage.setLayoutManager(layoutManagerMessage);
        rcvMessage.setItemAnimator(new FadeInLeftAnimator());
        rcvMessage.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(R.drawable.recycler_view_divider)));
        rcvMessage.setAdapter(messageAdapter);
        rcvMessage.addOnScrollListener(onScrollListener);
    }
}
