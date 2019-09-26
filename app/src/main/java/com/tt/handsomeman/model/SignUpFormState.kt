package com.tt.handsomeman.model

class SignUpFormState {
    var nameError: Int? = null
        private set
    var mailError: Int? = null
        private set
    var passwordError: Int? = null
        private set
    var rePasswordError: Int? = null
        private set

    var isDataValid: Boolean = false
        private set

    constructor(nameError: Int?, mailError: Int?, passwordError: Int?, rePasswordError: Int?) {
        this.nameError = nameError
        this.mailError = mailError
        this.passwordError = passwordError
        this.rePasswordError = rePasswordError
        this.isDataValid = false
    }

    constructor(isDataValid: Boolean) {
        this.nameError = null
        this.mailError = null
        this.passwordError = null
        this.rePasswordError = null
        this.isDataValid = isDataValid
    }
}
