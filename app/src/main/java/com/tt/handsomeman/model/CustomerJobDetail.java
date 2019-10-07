package com.tt.handsomeman.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerJobDetail {
    @SerializedName("accountId")
    @Expose
    private Integer accountId;
    @SerializedName("customerName")
    @Expose
    private String customerName;
    @SerializedName("customerAvatar")
    @Expose
    private Object customerAvatar;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Object getCustomerAvatar() {
        return customerAvatar;
    }

    public void setCustomerAvatar(Object customerAvatar) {
        this.customerAvatar = customerAvatar;
    }
}
