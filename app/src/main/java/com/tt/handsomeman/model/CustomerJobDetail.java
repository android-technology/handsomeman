package com.tt.handsomeman.model;

import java.io.Serializable;

public class CustomerJobDetail implements Serializable {
    private Integer accountId;
    private String customerName;
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
