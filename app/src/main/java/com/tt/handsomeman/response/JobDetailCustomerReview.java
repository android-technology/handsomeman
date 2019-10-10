package com.tt.handsomeman.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tt.handsomeman.model.CustomerReview;

public class JobDetailCustomerReview {
    @SerializedName("handymanName")
    @Expose
    private String handymanName;
    @SerializedName("customerReview")
    @Expose
    private CustomerReview customerReview;

    public String getHandymanName() {
        return handymanName;
    }

    public void setHandymanName(String handymanName) {
        this.handymanName = handymanName;
    }

    public CustomerReview getCustomerReview() {
        return customerReview;
    }

    public void setCustomerReview(CustomerReview customerReview) {
        this.customerReview = customerReview;
    }
}
