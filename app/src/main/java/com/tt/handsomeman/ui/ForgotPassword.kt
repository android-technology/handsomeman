package com.tt.handsomeman.ui

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText

import com.tt.handsomeman.R

class ForgotPassword : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        val edtForgotPassword = findViewById<EditText>(R.id.editTextForgotPasswordYourMail)
        val btnForgotPassword = findViewById<Button>(R.id.buttonSendForgotPassword)

        findViewById<View>(R.id.forgotPasswordBackButton).setOnClickListener { onBackPressed() }

        edtForgotPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun afterTextChanged(editable: Editable) {
                if (TextUtils.isEmpty(edtForgotPassword.text.toString()) || !Patterns.EMAIL_ADDRESS.matcher(edtForgotPassword.text.toString()).matches()) {
                    btnForgotPassword.isEnabled = false
                    edtForgotPassword.error = resources.getString(R.string.not_valid_mail)
                } else {
                    btnForgotPassword.isEnabled = true
                }
            }
        })
    }
}
