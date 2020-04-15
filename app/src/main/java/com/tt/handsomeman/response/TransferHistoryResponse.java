package com.tt.handsomeman.response;

import java.util.Date;

public class TransferHistoryResponse {
    private String lastPayoutNumber;
    private double balance;
    private Date dateTransfer;

    public String getLastPayoutNumber() {
        return lastPayoutNumber;
    }

    public void setLastPayoutNumber(String lastPayoutNumber) {
        this.lastPayoutNumber = lastPayoutNumber;
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

