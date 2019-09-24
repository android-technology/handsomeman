package com.tt.handsomeman.model;

import java.util.Date;

public class UserActivatingAccount {

    private String email;

    private String firstName;

    private String lastName;

    private String address;

    private Integer portalCode;

    private Date birthday;

    private String type;

    private String country;

    private String status;

    private String number;

    private String accountNumber;

    private String accountRouting;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public int getPortalCode() {
        return portalCode;
    }

    public void setPortalCode(int portalCode) {
        this.portalCode = portalCode;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
