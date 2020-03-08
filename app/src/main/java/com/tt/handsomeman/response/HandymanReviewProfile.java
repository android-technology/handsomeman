package com.tt.handsomeman.response;

import java.util.List;

public class HandymanReviewProfile {
    private Integer countReviewers;
    private Float averageReviewPoint;
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
