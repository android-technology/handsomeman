package com.tt.handsomeman.response;

import com.tt.handsomeman.model.Job;
import com.tt.handsomeman.model.PaymentMilestone;

import java.util.List;

public class CustomerJobDetail {
    private Job job;
    private List<PaymentMilestone> listPaymentMilestone;
    private HandymanResponse handyman;
    private boolean accepted;
    private boolean succeed;

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public List<PaymentMilestone> getListPaymentMilestone() {
        return listPaymentMilestone;
    }

    public void setListPaymentMilestone(List<PaymentMilestone> listPaymentMilestone) {
        this.listPaymentMilestone = listPaymentMilestone;
    }

    public HandymanResponse getHandyman() {
        return handyman;
    }

    public void setHandyman(HandymanResponse handyman) {
        this.handyman = handyman;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public boolean isSucceed() {
        return succeed;
    }

    public void setSucceed(boolean succeed) {
        this.succeed = succeed;
    }
}
