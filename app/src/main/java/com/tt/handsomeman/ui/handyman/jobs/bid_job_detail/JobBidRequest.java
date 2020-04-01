package com.tt.handsomeman.ui.handyman.jobs.bid_job_detail;

import com.tt.handsomeman.model.FileRequest;

import java.util.ArrayList;
import java.util.List;

public class JobBidRequest {
    private String jobId;
    private String bid;
    private String serviceFee;
    private String description;
    private String bidTime;
    private List<FileRequest> fileRequestList;

    public JobBidRequest() {
        fileRequestList = new ArrayList<>();
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(String serviceFee) {
        this.serviceFee = serviceFee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBidTime() {
        return bidTime;
    }

    public void setBidTime(String bidTime) {
        this.bidTime = bidTime;
    }

    public List<FileRequest> getFileRequestList() {
        return fileRequestList;
    }

    public void setFileRequestList(List<FileRequest> fileRequestList) {
        this.fileRequestList = fileRequestList;
    }
}
