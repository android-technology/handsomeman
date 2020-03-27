package com.tt.handsomeman.model;

import java.io.Serializable;
import java.util.List;

public class HandymanJobDetail implements Serializable {

    private Job job;
    private List<PaymentMilestone> listPaymentMilestone;
    private CustomerResponse customer;
    private boolean isBid;
    private boolean accepted;

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

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
