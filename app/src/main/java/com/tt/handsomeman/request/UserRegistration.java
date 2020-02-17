package com.tt.handsomeman.request;

public class UserRegistration {
    private String name;

    private String mail;

    private String password;

    private String rePassword;

    public UserRegistration(String name, String mail, String password, String rePassword) {
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.rePassword = rePassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }
}
