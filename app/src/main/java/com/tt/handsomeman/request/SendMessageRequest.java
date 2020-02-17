package com.tt.handsomeman.request;

public class SendMessageRequest {
    private Integer conversationId;
    private String body;
    private String sendTime;

    public SendMessageRequest(Integer conversationId, String body, String sendTime) {
        this.conversationId = conversationId;
        this.body = body;
        this.sendTime = sendTime;
    }

    public Integer getConversationId() {
        return conversationId;
    }

    public void setConversationId(Integer conversationId) {
        this.conversationId = conversationId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }
}
