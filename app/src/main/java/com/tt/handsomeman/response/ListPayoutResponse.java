package com.tt.handsomeman.response;

import java.util.List;

public class ListPayoutResponse {
    private double balance;
    private List<PayoutResponse> payoutResponseList;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<PayoutResponse> getPayoutResponseList() {
        return payoutResponseList;
    }

    public void setPayoutResponseList(List<PayoutResponse> payoutResponseList) {
        this.payoutResponseList = payoutResponseList;
    }
}
