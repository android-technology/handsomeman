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

    public String setSendTimeManipulate(Date dateTransfer) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String result;
        Calendar now = Calendar.getInstance();
        Date today, yesterday;

        dateTransfer = formatter.parse(formatter.format(dateTransfer));
        today = formatter.parse(formatter.format(now.getTime()));
        now.add(Calendar.DATE, -1);
        yesterday = formatter.parse(formatter.format(now.getTime()));

        if (dateTransfer.compareTo(today) == 0) {
            result = HandymanApp.getInstance().getString(R.string.today);
        } else if (dateTransfer.compareTo(yesterday) == 0) {
            result = HandymanApp.getInstance().getString(R.string.yesterday);
        } else {
            result = formatter.format(dateTransfer);
        }

        return result;
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

