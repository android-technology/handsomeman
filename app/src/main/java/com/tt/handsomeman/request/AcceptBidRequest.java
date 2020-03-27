package com.tt.handsomeman.request;

public class AcceptBidRequest {
    private Integer jobId;
    private Integer handymanId;
    private String acceptBidTime;

    public AcceptBidRequest(Integer jobId, Integer handymanId, String acceptBidTime) {
        this.jobId = jobId;
        this.handymanId = handymanId;
        this.acceptBidTime = acceptBidTime;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getHandymanId() {
        return handymanId;
    }

    public void setHandymanId(Integer handymanId) {
        this.handymanId = handymanId;
    }

    public String getAcceptBidTime() {
        return acceptBidTime;
    }

    public void setAcceptBidTime(String acceptBidTime) {
        this.acceptBidTime = acceptBidTime;
    }
}
