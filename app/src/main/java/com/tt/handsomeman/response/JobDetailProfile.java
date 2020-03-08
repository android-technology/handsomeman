package com.tt.handsomeman.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JobDetailProfile {
    @SerializedName("customerName")
    @Expose
    private String customerName;
    @SerializedName("customerAvatar")
    @Expose
    private String customerAvatar;
    @SerializedName("allProject")
    @Expose
    private Integer allProject;
    @SerializedName("successedProject")
    @Expose
    private Integer successedProject;
    @SerializedName("countReviewers")
    @Expose
    private Integer countReviewers;
    @SerializedName("averageReviewPoint")
    @Expose
    private Float averageReviewPoint;
    @SerializedName("customerReviewResponses")
    @Expose
    private List<CustomerReviewResponse> customerReviewResponses;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAvatar() {
        return customerAvatar;
    }

    public void setCustomerAvatar(String customerAvatar) {
        this.customerAvatar = customerAvatar;
    }

    public Integer getAllProject() {
        return allProject;
    }

    public void setAllProject(Integer allProject) {
        this.allProject = allProject;
    }

    public Integer getSuccessedProject() {
        return successedProject;
    }

    public void setSuccessedProject(Integer successedProject) {
        this.successedProject = successedProject;
    }

    public Integer getCountReviewers() {
        return countReviewers;
    }

    public void setCountReviewers(Integer countReviewers) {
        this.countReviewers = countReviewers;
    }

    public Float getAverageReviewPoint() {
        return averageReviewPoint;
    }

    public void setAverageReviewPoint(Float averageReviewPoint) {
        this.averageReviewPoint = averageReviewPoint;
    }

    public List<CustomerReviewResponse> getCustomerReviewResponses() {
        return customerReviewResponses;
    }

    public void setCustomerReviewResponses(List<CustomerReviewResponse> customerReviewResponses) {
        this.customerReviewResponses = customerReviewResponses;
    }
}
