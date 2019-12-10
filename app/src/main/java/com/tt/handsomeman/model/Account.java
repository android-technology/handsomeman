package com.tt.handsomeman.model;

import java.util.List;

public class Account {
    private int id;
    private String avatar;
    private List<Payout> payoutList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<Payout> getPayoutList() {
        return payoutList;
    }

    public void setPayoutList(List<Payout> payoutList) {
        this.payoutList = payoutList;
    }
}
