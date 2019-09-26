package com.tt.handsomeman.viewmodel

import android.util.Patterns

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.tt.handsomeman.R
import com.tt.handsomeman.model.SignUpAddPayoutFormState

class SignUpAddPayoutViewModel : ViewModel() {
    private val liveData = MutableLiveData<SignUpAddPayoutFormState>()

    val upAddPayoutFormState: LiveData<SignUpAddPayoutFormState>
        get() = liveData

    fun signUpPayOutDateChanged(firstName: String, lastName: String, address: String, portalCode: String, email: String, accountNumber: String, accountRouting: String, birthday: String) {
        if (!isFirstNameValid(firstName)) {
            liveData.value = SignUpAddPayoutFormState(R.string.not_valid_first_name, null, null, null, null, null, null, null)
        }
        if (!isLastNameValid(lastName)) {
            liveData.value = SignUpAddPayoutFormState(null, R.string.not_valid_last_name, null, null, null, null, null, null)
        }
        if (!isAddressValid(address)) {
            liveData.value = SignUpAddPayoutFormState(null, null, R.string.not_valid_address, null, null, null, null, null)
        }
        if (!isPortalCodeValid(portalCode)) {
            liveData.value = SignUpAddPayoutFormState(null, null, null, R.string.not_valid_portal_code, null, null, null, null)
        }
        if (!isEmailValid(email)) {
            liveData.value = SignUpAddPayoutFormState(null, null, null, null, R.string.not_valid_email, null, null, null)
        }
        if (!isAccountNumberValid(accountNumber)) {
            liveData.value = SignUpAddPayoutFormState(null, null, null, null, null, R.string.not_valid_account_number, null, null)
        }
        if (!isAccountRoutingValid(accountRouting)) {
            liveData.value = SignUpAddPayoutFormState(null, null, null, null, null, null, R.string.not_valid_account_routing, null)
        }
        if (!isBirthdayValid(birthday)) {
            liveData.value = SignUpAddPayoutFormState(null, null, null, null, null, null, null, R.string.not_valid_birthday)
        }
        if (isFirstNameValid(firstName) && isLastNameValid(lastName) && isAddressValid(address) && isPortalCodeValid(portalCode) && isEmailValid(email) && isAccountNumberValid(accountNumber) && isAccountRoutingValid(accountRouting) && isBirthdayValid(birthday)) {
            liveData.value = SignUpAddPayoutFormState(true)
        }
    }

    private fun isFirstNameValid(firstName: String?): Boolean {
        return firstName != null && firstName.trim { it <= ' ' }.length > 2
    }

    private fun isLastNameValid(lastName: String?): Boolean {
        return lastName != null && lastName.trim { it <= ' ' }.length > 2
    }

    private fun isAddressValid(address: String?): Boolean {
        return address != null && address.trim { it <= ' ' }.length > 3
    }

    private fun isPortalCodeValid(portalCode: String?): Boolean {
        return portalCode != null && portalCode.trim { it <= ' ' }.length == 6
    }

    private fun isEmailValid(email: String?): Boolean {
        if (email == null) {
            return false
        }
        return if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            false
        } else {
            !email.trim { it <= ' ' }.isEmpty()
        }
    }

    private fun isAccountNumberValid(accountNumber: String?): Boolean {
        return accountNumber != null && accountNumber.trim { it <= ' ' }.length > 11
    }

    private fun isAccountRoutingValid(accountRouting: String?): Boolean {
        return accountRouting != null && accountRouting.trim { it <= ' ' }.length == 9
    }

    private fun isBirthdayValid(birthday: String?): Boolean {
        return birthday != null && birthday.trim { it <= ' ' }.length > 6
    }
}
