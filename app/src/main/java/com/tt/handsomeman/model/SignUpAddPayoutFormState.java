package com.tt.handsomeman.model;

import androidx.annotation.Nullable;

public class SignUpAddPayoutFormState {
    @Nullable
    private Integer firstNameError;
    @Nullable
    private Integer lastNameError;
    @Nullable
    private Integer addressError;
    @Nullable
    private Integer portalCodeError;
    @Nullable
    private Integer emailError;
    @Nullable
    private Integer accountNumberError;
    @Nullable
    private Integer accountRoutingError;
    @Nullable
    private Integer birthdayError;

    private boolean isDataValid;

    public SignUpAddPayoutFormState(@Nullable Integer firstNameError, @Nullable Integer lastNameError, @Nullable Integer addressError, @Nullable Integer portalCodeError, @Nullable Integer emailError, @Nullable Integer accountNumberError, @Nullable Integer accountRoutingError, @Nullable Integer birthdayError) {
        this.firstNameError = firstNameError;
        this.lastNameError = lastNameError;
        this.addressError = addressError;
        this.portalCodeError = portalCodeError;
        this.emailError = emailError;
        this.accountNumberError = accountNumberError;
        this.accountRoutingError = accountRoutingError;
        this.birthdayError = birthdayError;
        isDataValid =false;
    }

    public SignUpAddPayoutFormState(boolean isDataValid) {
        this.firstNameError = null;
        this.lastNameError = null;
        this.addressError = null;
        this.portalCodeError = null;
        this.emailError = null;
        this.accountNumberError = null;
        this.accountRoutingError = null;
        this.birthdayError = null;
        this.isDataValid = isDataValid;
    }

    @Nullable
    public Integer getFirstNameError() {
        return firstNameError;
    }

    @Nullable
    public Integer getLastNameError() {
        return lastNameError;
    }

    @Nullable
    public Integer getAddressError() {
        return addressError;
    }

    @Nullable
    public Integer getPortalCodeError() {
        return portalCodeError;
    }

    @Nullable
    public Integer getEmailError() {
        return emailError;
    }

    @Nullable
    public Integer getAccountNumberError() {
        return accountNumberError;
    }

    @Nullable
    public Integer getAccountRoutingError() {
        return accountRoutingError;
    }

    @Nullable
    public Integer getBirthdayError() {
        return birthdayError;
    }

    public boolean isDataValid(){
        return isDataValid;
    }
}
