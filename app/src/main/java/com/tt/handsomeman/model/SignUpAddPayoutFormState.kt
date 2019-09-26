package com.tt.handsomeman.model

class SignUpAddPayoutFormState {
    var firstNameError: Int? = null
        private set
    var lastNameError: Int? = null
        private set
    var addressError: Int? = null
        private set
    var portalCodeError: Int? = null
        private set
    var emailError: Int? = null
        private set
    var accountNumberError: Int? = null
        private set
    var accountRoutingError: Int? = null
        private set
    var birthdayError: Int? = null
        private set

    var isDataValid: Boolean = false
        private set

    constructor(firstNameError: Int?, lastNameError: Int?, addressError: Int?, portalCodeError: Int?, emailError: Int?, accountNumberError: Int?, accountRoutingError: Int?, birthdayError: Int?) {
        this.firstNameError = firstNameError
        this.lastNameError = lastNameError
        this.addressError = addressError
        this.portalCodeError = portalCodeError
        this.emailError = emailError
        this.accountNumberError = accountNumberError
        this.accountRoutingError = accountRoutingError
        this.birthdayError = birthdayError
        isDataValid = false
    }

    constructor(isDataValid: Boolean) {
        this.firstNameError = null
        this.lastNameError = null
        this.addressError = null
        this.portalCodeError = null
        this.emailError = null
        this.accountNumberError = null
        this.accountRoutingError = null
        this.birthdayError = null
        this.isDataValid = isDataValid
    }
}
