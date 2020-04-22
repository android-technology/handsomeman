package com.tt.handsomeman.response;

import com.tt.handsomeman.model.Customer;

public class CustomerProfileResponse {
    private Customer customer;
    private String avatar;
    private int allProject;
    private int successedProject;
    private long updateDate;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getAllProject() {
        return allProject;
    }

    public void setAllProject(int allProject) {
        this.allProject = allProject;
    }

    public int getSuccessedProject() {
        return successedProject;
    }

    public void setSuccessedProject(int successedProject) {
        this.successedProject = successedProject;
    }

    public long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(long updateDate) {
        this.updateDate = updateDate;
    }
}
