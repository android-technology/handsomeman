package com.tt.handsomeman.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tt.handsomeman.model.HandymanReview;

public class HandymanReviewResponse {
    @SerializedName("customerName")
    @Expose
    private String customerName;
    @SerializedName("handymanReview")
    @Expose
    private HandymanReview handymanReview;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public HandymanReview getHandymanReview() {
        return handymanReview;
    }

    public void setHandymanReview(HandymanReview handymanReview) {
        this.handymanReview = handymanReview;
    }
}
