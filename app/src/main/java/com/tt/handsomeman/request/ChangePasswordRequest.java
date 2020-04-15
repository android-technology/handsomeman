package com.tt.handsomeman.request;

public class ChangePasswordRequest {
    private String currentPassword;
    private String newPassword;
    private String rePassword;

    public ChangePasswordRequest(String currentPassword,
                                 String newPassword,
                                 String rePassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
        this.rePassword = rePassword;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }
}
