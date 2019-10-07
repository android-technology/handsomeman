package com.tt.handsomeman.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JobDetail {
    @SerializedName("job")
    @Expose
    private Job job;
    @SerializedName("listPaymentMilestone")
    @Expose
    private List<ListPaymentMilestone> listPaymentMilestone;
    @SerializedName("customer")
    @Expose
    private CustomerJobDetail customerJobDetail;
    @SerializedName("averageReviewPoint")
    @Expose
    private Object averageReviewPoint;
    @SerializedName("countReviewers")
    @Expose
    private Integer countReviewers;

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public List<ListPaymentMilestone> getListPaymentMilestone() {
        return listPaymentMilestone;
    }

    public void setListPaymentMilestone(List<ListPaymentMilestone> listPaymentMilestone) {
        this.listPaymentMilestone = listPaymentMilestone;
    }

    public CustomerJobDetail getCustomerJobDetail() {
        return customerJobDetail;
    }

    public void setCustomerJobDetail(CustomerJobDetail customerJobDetail) {
        this.customerJobDetail = customerJobDetail;
    }

    public Object getAverageReviewPoint() {
        return averageReviewPoint;
    }

    public void setAverageReviewPoint(Object averageReviewPoint) {
        this.averageReviewPoint = averageReviewPoint;
    }

    public Integer getCountReviewers() {
        return countReviewers;
    }

    public void setCountReviewers(Integer countReviewers) {
        this.countReviewers = countReviewers;
    }
}
