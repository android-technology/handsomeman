package com.tt.handsomeman.response;

import java.util.List;

public class CustomerReviewProfile {
    private Integer countReviewers;
    private Float averageReviewPoint;
    private List<CustomerReviewResponse> customerReviewResponses;

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
