package com.tt.handsomeman.ui.handyman.messages;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tt.handsomeman.HandymanApp;
import com.tt.handsomeman.R;
import com.tt.handsomeman.adapter.ConversationAdapter;
import com.tt.handsomeman.response.ConversationResponse;
import com.tt.handsomeman.util.CustomDividerItemDecoration;
import com.tt.handsomeman.util.SharedPreferencesUtils;
import com.tt.handsomeman.viewmodel.MessageViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import jp.wasabeef.recyclerview.animators.FadeInLeftAnimator;

public class MessagesChildMessagesFragment extends Fragment {
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    SharedPreferencesUtils sharedPreferencesUtils;
    private MessageViewModel messageViewModel;
    private ConversationAdapter conversationAdapter;
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
        HandymanApp.getComponent().inject(this);
        messageViewModel = ViewModelProviders.of(this, viewModelFactory).get(MessageViewModel.class);
        return inflater.inflate(R.layout.fragment_messages_child_messages, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        createMessageRecycleView(view);
        fetchData();

        isScroll.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    conversationAdapter.closeSwipeLayout();
                }
            }
        });
    }

    private void createMessageRecycleView(View view) {
        RecyclerView rcvMessage = view.findViewById(R.id.recycleViewMessages);
        conversationAdapter = new ConversationAdapter(conversationResponseList, getContext());
        conversationAdapter.setOnItemClickListener(new ConversationAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ConversationResponse conversationResponse = conversationResponseList.get(position);
                Intent intent = new Intent(getContext(), Conversation.class);
                intent.putExtra("addressName", conversationResponse.getAccountName());
                intent.putExtra("conversationId", conversationResponse.getConversationId());
                startActivity(intent);
            }

            @Override
            public void onItemDelete(int position) {
                String authorizationCode = sharedPreferencesUtils.get("token", String.class);
                ConversationResponse conversationResponse = conversationResponseList.get(position);
                new DeleteConversationDialog(getActivity(), R.style.PauseDialog, new DeleteConversationDialog.OnItemClickListener() {
                    @Override
                    public void onItemClickYes() {
                        messageViewModel.deleteConversationById(authorizationCode, conversationResponse.getConversationId());
                        messageViewModel.getMessageResponse().observe(getViewLifecycleOwner(), s -> Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show());
                        conversationAdapter.deleteConversation(position);
                    }
                }).show();
            }
        });
        RecyclerView.LayoutManager layoutManagerMessage = new LinearLayoutManager(getContext());
        rcvMessage.setLayoutManager(layoutManagerMessage);
        rcvMessage.setItemAnimator(new FadeInLeftAnimator());
        rcvMessage.addItemDecoration(new CustomDividerItemDecoration(getResources().getDrawable(R.drawable.recycler_view_divider)));
        rcvMessage.setAdapter(conversationAdapter);
        rcvMessage.addOnScrollListener(onScrollListener);
    }

    private void fetchData() {

        String authorizationCode = sharedPreferencesUtils.get("token", String.class);

        messageViewModel.fetchAllConversationByAccountId(authorizationCode);
        messageViewModel.getConversationResponseMutableLiveData().observe(this, new Observer<List<ConversationResponse>>() {
            @Override
            public void onChanged(List<ConversationResponse> conversationResponses) {
                conversationResponseList.clear();
                conversationResponseList.addAll(conversationResponses);
                conversationAdapter.notifyItemRangeInserted(1, conversationResponseList.size());
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isScroll.removeObservers(this);
        messageViewModel.clearSubscriptions();
    }
}
