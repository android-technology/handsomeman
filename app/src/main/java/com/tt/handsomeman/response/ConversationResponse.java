package com.tt.handsomeman.response;

import com.tt.handsomeman.HandymanApp;
import com.tt.handsomeman.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ConversationResponse {
    private int conversationId;
    private String avatar;
    private String accountName;
    private String latestMessage;
    private Date sendTime;

    public String setSendTimeManipulate(Date sendTimeInput) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String result;
        Calendar now = Calendar.getInstance();
        Date today, yesterday;

        sendTimeInput = formatter.parse(formatter.format(sendTimeInput));
        today = formatter.parse(formatter.format(now.getTime()));
        now.add(Calendar.DATE, -1);
        yesterday = formatter.parse(formatter.format(now.getTime()));

        if (sendTimeInput.compareTo(today) == 0) {
            result = HandymanApp.getInstance().getString(R.string.today);
        } else if (sendTimeInput.compareTo(yesterday) == 0) {
            result = HandymanApp.getInstance().getString(R.string.yesterday);
        } else {
            result = formatter.format(sendTimeInput);
        }

        return result;
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getLatestMessage() {
        return latestMessage;
    }

    public void setLatestMessage(String latestMessage) {
        this.latestMessage = latestMessage;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}