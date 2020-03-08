package com.tt.handsomeman.response;

import com.tt.handsomeman.model.Customer;

public class CustomerProfileResponse {
    private Customer customer;
    private int allProject;
    private int successedProject;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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
}
