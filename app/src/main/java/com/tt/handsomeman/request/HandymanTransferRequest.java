package com.tt.handsomeman.request;

public class HandymanTransferRequest {
    private int payOutId;
    private double balance;
    private String dateTransfer;

    public HandymanTransferRequest(int payOutId,
                                   double balance,
                                   String dateTransfer) {
        this.payOutId = payOutId;
        this.balance = balance;
        this.dateTransfer = dateTransfer;
    }

    public int getPayOutId() {
        return payOutId;
    }

    public void setPayOutId(int payOutId) {
        this.payOutId = payOutId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getDateTransfer() {
        return dateTransfer;
    }

    public void setDateTransfer(String dateTransfer) {
        this.dateTransfer = dateTransfer;
    }
}

