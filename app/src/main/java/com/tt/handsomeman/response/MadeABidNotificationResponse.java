package com.tt.handsomeman.response;

import java.util.List;

public class MadeABidNotificationResponse {
    private Integer jobId;
    private double bid;
    private String bidDescription;
    private List<BidFileResponse> bidFileList;

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public double getBid() {
        return bid;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }

    public String getBidDescription() {
        return bidDescription;
    }

    public void setBidDescription(String bidDescription) {
        this.bidDescription = bidDescription;
    }

    public List<BidFileResponse> getBidFileList() {
        return bidFileList;
    }

    public void setBidFileList(List<BidFileResponse> bidFileList) {
        this.bidFileList = bidFileList;
    }
}