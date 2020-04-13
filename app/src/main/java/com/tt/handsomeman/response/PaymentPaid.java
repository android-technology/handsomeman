package com.tt.handsomeman.response;

public class PaymentPaid {
    private int paymentMilestoneOrder;
    private double balance;
    private double bonus;

    public int getPaymentMilestoneOrder() {
        return paymentMilestoneOrder;
    }

    public void setPaymentMilestoneOrder(int paymentMilestoneOrder) {
        this.paymentMilestoneOrder = paymentMilestoneOrder;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }
}
