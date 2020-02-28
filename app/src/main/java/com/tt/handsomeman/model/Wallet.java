package com.tt.handsomeman.model;

import com.tt.handsomeman.util.DecimalFormat;

public class Wallet {
    private int id;
    private Handyman handyman;
    private double balance;

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

    public String getBalance() {
        return DecimalFormat.format(balance);
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
