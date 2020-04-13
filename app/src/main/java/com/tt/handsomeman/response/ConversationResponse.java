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
    private int accountTwoId;
    private String avatar;
    private String accountName;
    private String latestMessage;
    private Date sendTime;

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public int getAccountTwoId() {
        return accountTwoId;
    }

    public void setAccountTwoId(int accountTwoId) {
        this.accountTwoId = accountTwoId;
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