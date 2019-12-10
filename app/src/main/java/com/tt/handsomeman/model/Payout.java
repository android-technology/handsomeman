package com.tt.handsomeman.model;

import java.util.Date;

public class Payout {
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private int portalCode;
    private Date birthday;
    private String type;
    private String email;
    private String accountNumber;
    private String accountRouting;
    private String country;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
