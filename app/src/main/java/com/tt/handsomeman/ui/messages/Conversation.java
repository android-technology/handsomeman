package com.tt.handsomeman.ui.messages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.messaging.FirebaseMessaging;
import com.tt.handsomeman.R;

public class Conversation extends AppCompatActivity {
    private TextView tvAddressName;
    private BroadcastReceiver receiver;
    private int conversationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        tvAddressName = findViewById(R.id.textViewConversationAccountName);

        findViewById(R.id.conversationBackButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        String addressName = getIntent().getStringExtra("addressName");
        tvAddressName.setText(addressName);

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

            }
        };
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
