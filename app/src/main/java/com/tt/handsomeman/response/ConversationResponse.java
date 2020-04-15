package com.tt.handsomeman.response;

import java.util.Date;

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