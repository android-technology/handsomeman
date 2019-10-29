package com.tt.handsomeman.ui.messages;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.messaging.FirebaseMessaging;
import com.tt.handsomeman.HandymanApp;
import com.tt.handsomeman.R;
import com.tt.handsomeman.adapter.MessageAdapter;
import com.tt.handsomeman.response.MessageResponse;
import com.tt.handsomeman.util.SharedPreferencesUtils;
import com.tt.handsomeman.viewmodel.MessageViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import jp.wasabeef.recyclerview.animators.FadeInUpAnimator;

public class Conversation extends AppCompatActivity {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    SharedPreferencesUtils sharedPreferencesUtils;
    private MessageViewModel messageViewModel;

    private MessageAdapter messageAdapter;
    private List<MessageResponse> messageResponseList = new ArrayList<>();

    private TextView tvAddressName;
    private BroadcastReceiver receiver;
    private int conversationId;
    private RecyclerView rcvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        HandymanApp.getComponent().inject(this);
        messageViewModel = ViewModelProviders.of(this, viewModelFactory).get(MessageViewModel.class);

        tvAddressName = findViewById(R.id.textViewConversationAccountName);

        findViewById(R.id.conversationBackButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        createRecyclerViewMessage();

        String addressName = getIntent().getStringExtra("addressName");
        tvAddressName.setText(addressName);

        fetchData();

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

            }
        };
    }

    private void fetchData() {
        String authorizationCode = sharedPreferencesUtils.get("token", String.class);
        int conversationId = getIntent().getIntExtra("conversationId", 0);
        messageViewModel.fetchAllMessageInConversation(authorizationCode, conversationId);
        messageViewModel.getMessageResponseListMutableLiveData().observe(this, new Observer<List<MessageResponse>>() {
            @Override
            public void onChanged(List<MessageResponse> messageResponses) {
                messageResponseList.clear();
                messageResponseList.addAll(messageResponses);
                messageAdapter.notifyItemRangeInserted(1, messageResponseList.size());
                rcvMessage.scrollToPosition(messageResponseList.size() - 1);
            }
        });
    }

    private void createRecyclerViewMessage() {
        rcvMessage = findViewById(R.id.messageRecyclerView);
        messageAdapter = new MessageAdapter(messageResponseList, this);
        RecyclerView.LayoutManager layoutManagerMessage = new LinearLayoutManager(this);
        rcvMessage.setLayoutManager(layoutManagerMessage);
        rcvMessage.setItemAnimator(new FadeInUpAnimator());

        rcvMessage.setAdapter(messageAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        conversationId = getIntent().getIntExtra("conversationId", 0);
        FirebaseMessaging.getInstance().subscribeToTopic(String.valueOf(conversationId));
        LocalBroadcastManager.getInstance(this).registerReceiver((receiver),
                new IntentFilter(FCMService.REQUEST_ACCEPT)
        );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        FirebaseMessaging.getInstance().unsubscribeFromTopic(String.valueOf(conversationId));
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }
}
