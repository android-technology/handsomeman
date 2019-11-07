package com.tt.handsomeman.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HandymanReviewProfile {
    @SerializedName("countReviewers")
    @Expose
    private Integer countReviewers;
    @SerializedName("averageReviewPoint")
    @Expose
    private Float averageReviewPoint;
    @SerializedName("handymanReviewResponseList")
    @Expose
    private List<HandymanReviewResponse> handymanReviewResponseList;

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

    public List<HandymanReviewResponse> getHandymanReviewResponseList() {
        return handymanReviewResponseList;
    }

    public void setHandymanReviewResponseList(List<HandymanReviewResponse> handymanReviewResponseList) {
        this.handymanReviewResponseList = handymanReviewResponseList;
    }
}
