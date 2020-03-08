package com.tt.handsomeman.model;

import java.io.Serializable;
import java.util.List;

public class JobDetail implements Serializable {
    private Job job;
    private boolean isBid;
    private List<PaymentMilestone> listPaymentMilestone;
    private CustomerJobDetail customerJobDetail;
    private Float averageReviewPoint;
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
