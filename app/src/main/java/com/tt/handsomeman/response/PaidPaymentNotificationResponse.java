package com.tt.handsomeman.response;

public class PaidPaymentNotificationResponse {
    private String jobTitle;
    private String customerName;
    private int paymentMilestoneOrder;
    private double balanceTransfer;
    private double balanceReceive;
    private double fee;
    private double bonus;

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getPaymentMilestoneOrder() {
        return paymentMilestoneOrder;
    }

    public void setPaymentMilestoneOrder(int paymentMilestoneOrder) {
        this.paymentMilestoneOrder = paymentMilestoneOrder;
    }

    public double getBalanceTransfer() {
        return balanceTransfer;
    }

    public void setBalanceTransfer(double balanceTransfer) {
        this.balanceTransfer = balanceTransfer;
    }

    public double getBalanceReceive() {
        return balanceReceive;
    }

    public void setBalanceReceive(double balanceReceive) {
        this.balanceReceive = balanceReceive;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }
}
