package com.tt.handsomeman.model;

import java.io.Serializable;
import java.util.List;

public class JobDetail implements Serializable {
    private Job job;

    private boolean isBid;

    private List<PaymentMilestone> listPaymentMilestone;

    private CustomerResponse customer;

    private Float averageReviewPoint;

    private Integer countReviewers;


    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public boolean isBid() {
        return isBid;
    }

    public void setBid(boolean bid) {
        isBid = bid;
    }

    public List<PaymentMilestone> getListPaymentMilestone() {
        return listPaymentMilestone;
    }

    public void setListPaymentMilestone(List<PaymentMilestone> listPaymentMilestone) {
        this.listPaymentMilestone = listPaymentMilestone;
    }

    public CustomerResponse getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerResponse customer) {
        this.customer = customer;
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
