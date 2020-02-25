package com.tt.handsomeman.request;

public class UserActivatingAccount {

    private String firstName;
    private String lastName;
    private String address;
    private Integer portalCode;
    private String birthday;
    private String type;
    private String email;
    private String accountNumber;
    private String accountRouting;
    private String country;
    private String accountStatus;
    private String businessNumber;

    public UserActivatingAccount(String firstName, String lastName, String address, Integer portalCode, String birthday, String type, String email, String accountNumber, String accountRouting, String country, String accountStatus, String businessNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.portalCode = portalCode;
        this.birthday = birthday;
        this.type = type;
        this.email = email;
        this.accountNumber = accountNumber;
        this.accountRouting = accountRouting;
        this.country = country;
        this.accountStatus = accountStatus;
        this.businessNumber = businessNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPortalCode() {
        return portalCode;
    }

    public void setPortalCode(Integer portalCode) {
        this.portalCode = portalCode;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountRouting() {
        return accountRouting;
    }

    public void setAccountRouting(String accountRouting) {
        this.accountRouting = accountRouting;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getBusinessNumber() {
        return businessNumber;
    }

    public void setBusinessNumber(String businessNumber) {
        this.businessNumber = businessNumber;
    }
}
