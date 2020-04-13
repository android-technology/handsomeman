package com.tt.handsomeman.response;

import com.tt.handsomeman.HandymanApp;
import com.tt.handsomeman.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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

