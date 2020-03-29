package com.tt.handsomeman.ui.handyman.messages;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

import com.tt.handsomeman.HandymanApp;
import com.tt.handsomeman.adapter.MessageAdapter;
import com.tt.handsomeman.databinding.ActivityConversationBinding;
import com.tt.handsomeman.request.SendMessageRequest;
import com.tt.handsomeman.response.DataBracketResponse;
import com.tt.handsomeman.response.ListMessage;
import com.tt.handsomeman.response.MessageResponse;
import com.tt.handsomeman.response.StandardResponse;
import com.tt.handsomeman.ui.BaseAppCompatActivity;
import com.tt.handsomeman.ui.FCMService;
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

    public static Integer receiveDefaultId;
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
    private RecyclerView rcvMessage;
    private int sendId, receiveId;
    private boolean isAtBottom = true;
    private ActivityConversationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConversationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        HandymanApp.getComponent().inject(this);
        baseViewModel = new ViewModelProvider(this, viewModelFactory).get(MessageViewModel.class);

        tvAddressName = binding.textViewConversationAccountName;
        ibSendMessage = binding.imageButtonSendMessage;
        edtMessageBody = binding.editTextMessageConversation;
        ibSendMessage.setEnabled(false);

        binding.conversationBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        String authorizationCode = sharedPreferencesUtils.get("token", String.class);
        sendId = Integer.parseInt(sharedPreferencesUtils.get("userId", String.class));

        String addressName = getIntent().getStringExtra("addressName");
        receiveId = getIntent().getIntExtra("receiveId", 0);
        tvAddressName.setText(addressName);
        receiveDefaultId = receiveId;

        createRecyclerViewMessage();
        fetchData(authorizationCode, receiveId);
        addRecyclerViewBottomListener();
        listenEditChange();
        sendMessage(authorizationCode, receiveId);
        listenToFireBaseService(receiveId);
    }

    private void listenToFireBaseService(int receiveId) {
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Bundle bundle = intent.getExtras();

                if (receiveId == Integer.parseInt(bundle.getString("accountId")) && Integer.parseInt(bundle.getString("accountId")) != sendId) {
                    Date sendTime = null;
                    try {
                        sendTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ZZ", Locale.getDefault()).parse(bundle.getString("sendTime"));
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
                                , URLDecoder.decode(bundle.getString("body"), "UTF-8"), sendTime, type);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                    if (!messageResponseList.contains(messageResponse)) {
                        messageResponseList.add(messageResponse);
                        messageAdapter.notifyItemInserted(messageResponseList.size() - 1);
                        if (isAtBottom) {
                            rcvMessage.scrollToPosition(messageResponseList.size() - 1);
                        }
                    }
                }
            }
        };
    }

    private void sendMessage(String authorizationCode, int receiveId) {
        ibSendMessage.setOnClickListener(view -> {
            String bodyMessage = edtMessageBody.getText().toString().trim();
            sendMessage(authorizationCode, receiveId, bodyMessage);
            edtMessageBody.setText(null);
        });
    }

    private void listenEditChange() {
        edtMessageBody.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String bodyMessage = edtMessageBody.getText().toString().trim();
                ibSendMessage.setEnabled(!bodyMessage.matches(""));
            }
        });
    }

    private void sendMessage(String authorizationCode, int receiveId, String bodyMessage) {
        Calendar now = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ZZ", Locale.getDefault());
        String sendTime = formatter.format(now.getTime());

        baseViewModel.sendMessageToConversation(authorizationCode, new SendMessageRequest(receiveId, bodyMessage, sendTime));
        baseViewModel.getStandardResponseMutableLiveData().observe(this, new Observer<StandardResponse>() {
            @Override
            public void onChanged(StandardResponse standardResponse) {
                Toast.makeText(Conversation.this, standardResponse.getMessage(), Toast.LENGTH_SHORT).show();
                if (standardResponse.getStatus().equals(StatusConstant.OK)) {
                    MessageResponse messageResponse = new MessageResponse(null, sendId, bodyMessage, now.getTime(), (byte) 1);
                    if (!messageResponseList.contains(messageResponse)) {
                        messageResponseList.add(messageResponse);
                        messageAdapter.notifyItemInserted(messageResponseList.size() - 1);
                        rcvMessage.scrollToPosition(messageResponseList.size() - 1);
                    }
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

    private void fetchData(String authorizationCode, int accountId) {
        baseViewModel.fetchAllMessagesWithAccount(authorizationCode, accountId);
        baseViewModel.getListMessageResponse().observe(this, new Observer<DataBracketResponse<ListMessage>>() {
            @Override
            public void onChanged(DataBracketResponse<ListMessage> listMessageDataBracketResponse) {
                messageResponseList.clear();
                messageResponseList.addAll(listMessageDataBracketResponse.getData().getMessageResponseList());
                messageAdapter.notifyItemRangeInserted(1, messageResponseList.size());
                rcvMessage.scrollToPosition(messageResponseList.size() - 1);
            }
        });
    }

    private void createRecyclerViewMessage() {
        rcvMessage = binding.messageRecyclerView;
        messageAdapter = new MessageAdapter(messageResponseList, this);
        RecyclerView.LayoutManager layoutManagerMessage = new LinearLayoutManager(this);
        rcvMessage.setLayoutManager(layoutManagerMessage);
        rcvMessage.setItemAnimator(new FadeInUpAnimator());

        rcvMessage.setAdapter(messageAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(this).registerReceiver((receiver),
                new IntentFilter(FCMService.REQUEST_MESSAGE)
        );
    }

    @Override
    protected void onStop() {
        super.onStop();
        receiveDefaultId = 0;
    }

    @Override
    protected void onResume() {
        super.onResume();
        receiveDefaultId = receiveId;
    }

    @Override
    public void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
        receiveDefaultId = 0;
        super.onDestroy();
    }
}
