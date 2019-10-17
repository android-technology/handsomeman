package com.tt.handsomeman.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class JobDetail implements Serializable {
    @SerializedName("job")
    @Expose
    private Job job;
    @SerializedName("isBid")
    @Expose
    private boolean isBid;
    @SerializedName("listPaymentMilestone")
    @Expose
    private List<PaymentMilestone> listPaymentMilestone;
    @SerializedName("customer")
    @Expose
    private CustomerJobDetail customerJobDetail;
    @SerializedName("averageReviewPoint")
    @Expose
    private Float averageReviewPoint;
    @SerializedName("countReviewers")
    @Expose
    private Integer countReviewers;

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public boolean getIsBid() {
        return isBid;
    }

    public void setIsBid(boolean isBid) {
        this.isBid = isBid;
    }

    public List<PaymentMilestone> getListPaymentMilestone() {
        return listPaymentMilestone;
    }

    public void setListPaymentMilestone(List<PaymentMilestone> paymentMilestone) {
        this.listPaymentMilestone = paymentMilestone;
    }

    public CustomerJobDetail getCustomerJobDetail() {
        return customerJobDetail;
    }

    public void setCustomerJobDetail(CustomerJobDetail customerJobDetail) {
        this.customerJobDetail = customerJobDetail;
    }

    public Float getAverageReviewPoint() {
        return averageReviewPoint;
    }

    public void setAverageReviewPoint(Float averageReviewPoint) {
        this.averageReviewPoint = averageReviewPoint;
    }

    public Integer getCountReviewers() {
        return countReviewers;
    }

    public void setCountReviewers(Integer countReviewers) {
        this.countReviewers = countReviewers;
    }
}