package com.tt.handsomeman.ui.handyman.messages;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.messaging.FirebaseMessaging;
import com.tt.handsomeman.HandymanApp;
import com.tt.handsomeman.R;
import com.tt.handsomeman.adapter.MessageAdapter;
import com.tt.handsomeman.response.MessageResponse;
import com.tt.handsomeman.response.StandardResponse;
import com.tt.handsomeman.ui.BaseAppCompatActivity;
import com.tt.handsomeman.util.SharedPreferencesUtils;
import com.tt.handsomeman.util.StatusConstant;
import com.tt.handsomeman.viewmodel.MessageViewModel;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import jp.wasabeef.recyclerview.animators.FadeInUpAnimator;

public class Conversation extends BaseAppCompatActivity<MessageViewModel> {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    SharedPreferencesUtils sharedPreferencesUtils;

    private MessageAdapter messageAdapter;
    private List<MessageResponse> messageResponseList = new ArrayList<>();

    private TextView tvAddressName;
    private ImageButton ibSendMessage;
    private EditText edtMessageBody;

    private BroadcastReceiver receiver;
    private int conversationId;
    private RecyclerView rcvMessage;
    private boolean isAtBottom = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        HandymanApp.getComponent().inject(this);
        baseViewModel = new ViewModelProvider(this, viewModelFactory).get(MessageViewModel.class);

        tvAddressName = findViewById(R.id.textViewConversationAccountName);
        ibSendMessage = findViewById(R.id.imageButtonSendMessage);
        edtMessageBody = findViewById(R.id.editTextMessageConversation);

        findViewById(R.id.conversationBackButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        String authorizationCode = sharedPreferencesUtils.get("token", String.class);
        int conversationId = getIntent().getIntExtra("conversationId", 0);

        createRecyclerViewMessage();

        String addressName = getIntent().getStringExtra("addressName");
        tvAddressName.setText(addressName);

        fetchData(authorizationCode, conversationId);
        addRecyclerViewBottomListener();

        ibSendMessage.setOnClickListener(view -> {
            if (edtMessageBody.getText().toString().matches("")) {
                Toast.makeText(this, HandymanApp.getInstance().getString(R.string.please_write_something), Toast.LENGTH_SHORT).show();
            } else sendMessage(authorizationCode, conversationId);
        });

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Bundle bundle = intent.getExtras();

                Date sendTime = null;
                try {
                    sendTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(bundle.getString("sendTime"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                byte type;
                if (bundle.getString("accountId").equals(sharedPreferencesUtils.get("userId", String.class))) {
                    type = 1;
                } else {
                    type = 2;
                }
                MessageResponse messageResponse = null;
                try {
                    messageResponse = new MessageResponse(bundle.getString("avatar"), Integer.parseInt(bundle.getString("accountId"))
                            , URLDecoder.decode(bundle.getString("Body"), "UTF-8"), sendTime, type);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                messageResponseList.add(messageResponse);
                messageAdapter.notifyItemInserted(messageResponseList.size() - 1);
                if (isAtBottom) {
                    rcvMessage.scrollToPosition(messageResponseList.size() - 1);
                }
            }
        };
    }

    private void sendMessage(String authorizationCode, int conversationId) {
        Calendar now = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String sendTime = formatter.format(now.getTime());

        baseViewModel.sendMessageToConversation(authorizationCode, conversationId, edtMessageBody.getText().toString(), sendTime);
        baseViewModel.getStandardResponseMutableLiveData().observe(this, new Observer<StandardResponse>() {
            @Override
            public void onChanged(StandardResponse standardResponse) {
                if (standardResponse != null && standardResponse.getStatus().equals(StatusConstant.OK)) {
                    Toast.makeText(Conversation.this, standardResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    baseViewModel.clearStandardResponseLiveDate();
                    edtMessageBody.setText(null);
                }
            }
        });
    }

    private void addRecyclerViewBottomListener() {
        rcvMessage.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                isAtBottom = !recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE;
            }
        });
    }

    private void fetchData(String authorizationCode, int conversationId) {
        baseViewModel.fetchAllMessageInConversation(authorizationCode, conversationId);
        baseViewModel.getMessageResponseListMutableLiveData().observe(this, new Observer<List<MessageResponse>>() {
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
        FirebaseMessaging.getInstance().unsubscribeFromTopic(String.valueOf(conversationId));
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
        super.onDestroy();
    }
}
