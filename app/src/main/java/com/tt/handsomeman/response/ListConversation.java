package com.tt.handsomeman.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListConversation {
    @SerializedName("conversationList")
    @Expose
    private List<ConversationResponse> conversationList;

    public List<ConversationResponse> getConversationList() {
        return conversationList;
    }

    public void setConversationList(List<ConversationResponse> conversationList) {
        this.conversationList = conversationList;
    }
}
