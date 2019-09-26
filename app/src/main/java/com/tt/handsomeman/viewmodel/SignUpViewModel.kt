package com.tt.handsomeman.viewmodel

import android.util.Patterns

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.tt.handsomeman.R
import com.tt.handsomeman.model.SignUpFormState

class SignUpViewModel : ViewModel() {

    private val signUpFormState = MutableLiveData<SignUpFormState>()

    fun getSignUpFormState(): LiveData<SignUpFormState> {
        return signUpFormState
    }

    fun signUpDateChanged(name: String, mail: String, password: String, rePassword: String) {
        if (!isNameValid(name)) {
            signUpFormState.value = SignUpFormState(R.string.not_valid_name, null, null, null)
        }
        if (!isMailValid(mail)) {
            signUpFormState.value = SignUpFormState(null, R.string.not_valid_mail, null, null)
        }
        if (!isPasswordValid(password)) {
            signUpFormState.value = SignUpFormState(null, null, R.string.not_valid_password, null)
        }
        if (!isMatchPassword(password, rePassword)) {
            signUpFormState.value = SignUpFormState(null, null, null, R.string.password_not_match)
        }
        if (isNameValid(name) && isMailValid(mail) && isPasswordValid(password) && isMatchPassword(password, rePassword)) {
            signUpFormState.value = SignUpFormState(true)
        }
    }

    private fun isNameValid(name: String?): Boolean {
        return name != null && name.trim { it <= ' ' }.length > 4
    }

    private fun isMailValid(mail: String?): Boolean {
        if (mail == null) {
            return false
        }
        return if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            false
        } else {
            !mail.trim { it <= ' ' }.isEmpty()
        }
    }

    private fun isPasswordValid(password: String?): Boolean {
        return password != null && password.trim { it <= ' ' }.length > 5
    }

    private fun isMatchPassword(password: String, rePassword: String?): Boolean {
        return if (rePassword != null && rePassword != password) {
            false
        } else true
    }
}
