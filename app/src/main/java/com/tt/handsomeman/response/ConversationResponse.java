package com.tt.handsomeman.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class ConversationResponse {
    @SerializedName("conversationId")
    @Expose
    private int conversationId;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("accountName")
    @Expose
    private String accountName;
    @SerializedName("latestMessage")
    @Expose
    private String latestMessage;
    @SerializedName("sendTime")
    @Expose
    private Date sendTime;

    public ConversationResponse(String accountName, String latestMessage) {
        this.accountName = accountName;
        this.latestMessage = latestMessage;
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