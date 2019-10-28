package com.tt.handsomeman.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tt.handsomeman.HandymanApp;
import com.tt.handsomeman.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MessageResponse {
    @SerializedName("messageId")
    @Expose
    private int messageId;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("accountId")
    @Expose
    private int accountId;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("sendTime")
    @Expose
    private Date sendTime;
    @SerializedName("type")
    @Expose
    // type = 1 mean this is sender; type = 2 mean this is receiver
    private byte type;

    public String setSendTimeManipulate(Date sendTimeInput) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.US);
        String result;
        Calendar now = Calendar.getInstance();
        Date today, yesterday, todayTime;
        todayTime = sendTimeInput;

        sendTimeInput = formatter.parse(formatter.format(sendTimeInput));
        today = formatter.parse(formatter.format(now.getTime()));
        now.add(Calendar.DATE, -1);
        yesterday = formatter.parse(formatter.format(now.getTime()));

        if (sendTimeInput.compareTo(today) == 0) {
            SimpleDateFormat todayFormat = new SimpleDateFormat("HH:mm", Locale.US);
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
}
