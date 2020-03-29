package com.tt.handsomeman.request;

public class SendMessageRequest {
    private Integer receiveId;
    private String body;
    private String sendTime;

    public SendMessageRequest(Integer receiveId, String body, String sendTime) {
        this.receiveId = receiveId;
        this.body = body;
        this.sendTime = sendTime;
    }

    public Integer getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(Integer receiveId) {
        this.receiveId = receiveId;
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
