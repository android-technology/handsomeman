package com.tt.handsomeman.response;

import java.util.List;

public class ListConversation {
    private List<ConversationResponse> conversationList;

    public List<ConversationResponse> getConversationList() {
        return conversationList;
    }

    public void setConversationList(List<ConversationResponse> conversationList) {
        this.conversationList = conversationList;
    }
}
