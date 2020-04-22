package com.tt.handsomeman.response;

import java.util.Date;

public class CustomerTransferHistoryResponse {
    private String handymanAvatar;
    private String handymanName;
    private String jobTitle;
    private String lastPayoutNumber;
    private int paymentMilestoneOrder;
    private double balance;
    private Date dateTransfer;
    private long updateDate;

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

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getLastPayoutNumber() {
        return lastPayoutNumber;
    }

    public void setLastPayoutNumber(String lastPayoutNumber) {
        this.lastPayoutNumber = lastPayoutNumber;
    }

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

    public Date getDateTransfer() {
        return dateTransfer;
    }

    public void setDateTransfer(Date dateTransfer) {
        this.dateTransfer = dateTransfer;
    }

    public long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(long updateDate) {
        this.updateDate = updateDate;
    }
}
