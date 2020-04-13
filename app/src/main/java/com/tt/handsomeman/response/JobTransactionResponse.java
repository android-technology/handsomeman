package com.tt.handsomeman.response;

public class JobTransactionResponse {
    private int jobId;
    private int handymanId;
    private double minPay;
    private String jobTitle;
    private int paymentMileStoneOrder;
    private int paymentMileStonePercentage;
    private String handymanAvatar;
    private String handymanName;

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

    public double getMinPay() {
        return minPay;
    }

    public void setMinPay(double minPay) {
        this.minPay = minPay;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public int getPaymentMileStoneOrder() {
        return paymentMileStoneOrder;
    }

    public void setPaymentMileStoneOrder(int paymentMileStoneOrder) {
        this.paymentMileStoneOrder = paymentMileStoneOrder;
    }

    public int getPaymentMileStonePercentage() {
        return paymentMileStonePercentage;
    }

    public void setPaymentMileStonePercentage(int paymentMileStonePercentage) {
        this.paymentMileStonePercentage = paymentMileStonePercentage;
    }

    public String getHandymanAvatar() {
        return handymanAvatar;
    }

    public void setHandymanAvatar(String handymanAvatar) {
        this.handymanAvatar = handymanAvatar;
    }

    public String getHandymanName() {
        return handymanName;
    }

    public void setHandymanName(String handymanName) {
        this.handymanName = handymanName;
    }
}
