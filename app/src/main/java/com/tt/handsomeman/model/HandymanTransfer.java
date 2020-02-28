package com.tt.handsomeman.model;

import java.util.Date;

public class HandymanTransfer {
    private int id;
    private Handyman handyman;
    private int payoutId;
    private double balance;
    private Date dateTransfer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Handyman getHandyman() {
        return handyman;
    }

    public void setHandyman(Handyman handyman) {
        this.handyman = handyman;
    }

    public int getPayoutId() {
        return payoutId;
    }

    public void setPayoutId(int payoutId) {
        this.payoutId = payoutId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Date getDateTransfer() {
        return dateTransfer;
    }

    public void setDateTransfer(Date dateTransfer) {
        this.dateTransfer = dateTransfer;
    }
}
