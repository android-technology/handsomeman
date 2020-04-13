package com.tt.handsomeman.response;

import java.util.List;

public class TransactionDetailResponse {
    private String jobTitle;
    private double totalBalance;
    private double willReceive;
    private double fee;
    private double haveReceive;
    private double bonus;
    private List<PaymentPaid> paymentPaidList;

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public double getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(double totalBalance) {
        this.totalBalance = totalBalance;
    }

    public double getWillReceive() {
        return willReceive;
    }

    public void setWillReceive(double willReceive) {
        this.willReceive = willReceive;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public double getHaveReceive() {
        return haveReceive;
    }

    public void setHaveReceive(double haveReceive) {
        this.haveReceive = haveReceive;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public List<PaymentPaid> getPaymentPaidList() {
        return paymentPaidList;
    }

    public void setPaymentPaidList(List<PaymentPaid> paymentPaidList) {
        this.paymentPaidList = paymentPaidList;
    }
}
