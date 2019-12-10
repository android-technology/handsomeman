package com.tt.handsomeman.model;

import java.util.List;

public class Handyman {
    private int id;
    private String name;
    private String businessType;
    private String businessNumber;
    private String education;
    private String detail;
    private String status;
    private Wallet wallet;
    private List<HandymanTransfer> handymanTransferList;
    private Account account;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getBusinessNumber() {
        return businessNumber;
    }

    public void setBusinessNumber(String businessNumber) {
        this.businessNumber = businessNumber;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public List<HandymanTransfer> getHandymanTransferList() {
        return handymanTransferList;
    }

    public void setHandymanTransferList(List<HandymanTransfer> handymanTransferList) {
        this.handymanTransferList = handymanTransferList;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
