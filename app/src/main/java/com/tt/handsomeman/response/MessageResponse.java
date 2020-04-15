package com.tt.handsomeman.response;

import java.util.Date;
import java.util.Objects;

public class MessageResponse {
    private int messageId;
    private String avatar;
    private int accountId;
    private String body;
    private Date sendTime;
    // type = 1 mean this is sender; type = 2 mean this is receiver
    private byte type;

    // Loading ViewHolder in MessageAdapter
    public MessageResponse(int type) {
        this.type = (byte) type;
    }

    public MessageResponse(String avatar,
                           int accountId,
                           String body,
                           Date sendTime,
                           byte type) {
        this.avatar = avatar;
        this.accountId = accountId;
        this.body = body;
        this.sendTime = sendTime;
        this.type = type;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageResponse messageResponse = (MessageResponse) o;
        return Objects.equals(sendTime, messageResponse.sendTime) &&
                Objects.equals(type, messageResponse.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sendTime, type);
    }
}
