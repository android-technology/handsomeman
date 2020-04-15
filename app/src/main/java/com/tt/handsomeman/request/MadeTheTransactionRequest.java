package com.tt.handsomeman.request;

public class MadeTheTransactionRequest {
    private int jobId;
    private int handymanId;
    private int payoutId;
    private double balance;
    private int paymentMilestoneOrder;
    private String dateTransfer;
    private double bonus;

    public MadeTheTransactionRequest(int jobId,
                                     int handymanId,
                                     int payoutId,
                                     double balance,
                                     int paymentMilestoneOrder,
                                     String dateTransfer,
                                     double bonus) {
        this.jobId = jobId;
        this.handymanId = handymanId;
        this.payoutId = payoutId;
        this.balance = balance;
        this.paymentMilestoneOrder = paymentMilestoneOrder;
        this.dateTransfer = dateTransfer;
        this.bonus = bonus;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getHandymanId() {
        return handymanId;
    }

    public void setHandymanId(int handymanId) {
        this.handymanId = handymanId;
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

    public int getPaymentMilestoneOrder() {
        return paymentMilestoneOrder;
    }

    public void setPaymentMilestoneOrder(int paymentMilestoneOrder) {
        this.paymentMilestoneOrder = paymentMilestoneOrder;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public String getStringTransfer() {
        return dateTransfer;
    }

    public void setStringTransfer(String dateTransfer) {
        this.dateTransfer = dateTransfer;
    }
}
