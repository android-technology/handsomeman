package com.tt.handsomeman.response;

import java.util.List;

public class ListConversation {
    private List<ConversationResponse> conversationList;
    private List<Contact> contactList;

    public List<ConversationResponse> getConversationList() {
        return conversationList;
    }

    public void setConversationList(List<ConversationResponse> conversationList) {
        this.conversationList = conversationList;
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }
}
