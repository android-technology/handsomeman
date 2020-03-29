package com.tt.handsomeman.response;

import com.tt.handsomeman.HandymanApp;
import com.tt.handsomeman.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class MessageResponse {
    private int messageId;
    private String avatar;
    private int accountId;
    private String body;
    private Date sendTime;
    // type = 1 mean this is sender; type = 2 mean this is receiver
    private byte type;

    public MessageResponse(String avatar, int accountId, String body, Date sendTime, byte type) {
        this.avatar = avatar;
        this.accountId = accountId;
        this.body = body;
        this.sendTime = sendTime;
        this.type = type;
    }

    public String setSendTimeManipulate(Date sendTimeInput) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String result;
        Calendar now = Calendar.getInstance();
        Date today, yesterday, todayTime;
        todayTime = sendTimeInput;

        sendTimeInput = formatter.parse(formatter.format(sendTimeInput));
        today = formatter.parse(formatter.format(now.getTime()));
        now.add(Calendar.DATE, -1);
        yesterday = formatter.parse(formatter.format(now.getTime()));

        if (sendTimeInput.compareTo(today) == 0) {
            SimpleDateFormat todayFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
            result = todayFormat.format(todayTime);
        } else if (sendTimeInput.compareTo(yesterday) == 0) {
            result = HandymanApp.getInstance().getString(R.string.yesterday);
        } else {
            result = formatter.format(sendTimeInput);
        }

        return result;
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
