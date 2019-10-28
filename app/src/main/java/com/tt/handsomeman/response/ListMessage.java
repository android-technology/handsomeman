package com.tt.handsomeman.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListMessage {
    @SerializedName("messageResponseList")
    @Expose
    private List<MessageResponse> messageResponseList;

    public List<MessageResponse> getMessageResponseList() {
        return messageResponseList;
    }

    public void setMessageResponseList(List<MessageResponse> messageResponseList) {
        this.messageResponseList = messageResponseList;
    }
}
