package com.tt.handsomeman.response;

public class PayoutResponse {
    private double balance;
    private int payoutId;
    private String payoutLastNumber;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getPayoutId() {
        return payoutId;
    }

    public void setPayoutId(int payoutId) {
        this.payoutId = payoutId;
    }

    public String getPayoutLastNumber() {
        return payoutLastNumber;
    }

    public void setPayoutLastNumber(String payoutLastNumber) {
        this.payoutLastNumber = payoutLastNumber;
    }
}