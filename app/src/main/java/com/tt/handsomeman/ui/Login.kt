package com.tt.handsomeman.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity

import com.tt.handsomeman.HandymanApp
import com.tt.handsomeman.R
import com.tt.handsomeman.response.LoginResponse
import com.tt.handsomeman.service.LoginService
import com.tt.handsomeman.util.Constants
import com.tt.handsomeman.util.SharedPreferencesUtils
import com.tt.handsomeman.util.StatusCodeConstant
import com.tt.handsomeman.util.StatusConstant

import javax.inject.Inject

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {
    internal var cbVisiblePassword: CheckBox
    internal var edtMail: EditText
    internal var edtPassword: EditText
    internal var btLogin: Button
    internal var btForgot: Button
    internal var pgLogin: ProgressBar

    internal var mailValidate = false
    internal var passwordValidate = false

    @Inject
    internal var loginService: LoginService? = null

    @Inject
    internal var sharedPreferencesUtils: SharedPreferencesUtils? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        cbVisiblePassword = findViewById(R.id.checkboxLoginVisiblePassword)
        edtMail = findViewById(R.id.editTextLoginYourMail)
        edtPassword = findViewById(R.id.editTextLoginPassword)
        btLogin = findViewById(R.id.buttonLogin)
        btForgot = findViewById(R.id.buttonForgotPassword)
        pgLogin = findViewById(R.id.progressBarLogin)

        HandymanApp.component!!.inject(this)

        findViewById<View>(R.id.loginBackButton).setOnClickListener { onBackPressed() }

        edtChangedListener()

        doLogin()

        doForgotPassword()

        viewPassword()
    }

    private fun viewPassword() {
        cbVisiblePassword.setOnClickListener {
            if (cbVisiblePassword.isChecked) {
                edtPassword.transformationMethod = null
            } else {
                edtPassword.transformationMethod = PasswordTransformationMethod()
            }
            edtPassword.setSelection(edtPassword.length())
        }
    }

    private fun doForgotPassword() {
        btForgot.setOnClickListener { startActivity(Intent(this@Login, ForgotPassword::class.java)) }
    }

    private fun doLogin() {
        btLogin.setOnClickListener {
            pgLogin.visibility = View.VISIBLE
            btLogin.isEnabled = false
            val mail = edtMail.text.toString()
            val password = edtPassword.text.toString()

            loginService!!.doLogin(mail, password).enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.body()!!.status == StatusConstant.OK && response.body()!!.statusCode == StatusCodeConstant.OK) {
                        val token = response.body()!!.data!!.token
                        val state = response.body()!!.data!!.state
                        pgLogin.visibility = View.GONE

                        sharedPreferencesUtils!!.put("token", token)
                        sharedPreferencesUtils!!.put("state", state)

                        if (state == Constants.NOT_ACTIVE_ACCOUNT) {
                            startActivity(Intent(this@Login, SignUpAddPayout::class.java))
                            Register.register.finish()
                            finish()
                        } else if (state == Constants.STATE_REGISTER_ADDED_PAYOUT) {
                            startActivity(Intent(this@Login, HandyManMainScreen::class.java))
                            Register.register.finish()
                            finish()
                        }
                    } else {
                        pgLogin.visibility = View.GONE
                        btLogin.isEnabled = true
                        Toast.makeText(this@Login, response.body()!!.message, Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    pgLogin.visibility = View.GONE
                    btLogin.isEnabled = true
                    Toast.makeText(this@Login, t.message, Toast.LENGTH_LONG).show()
                }
            })
        }
    }

    private fun edtChangedListener() {
        edtMail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun afterTextChanged(editable: Editable) {
                val mail = edtMail.text.toString().trim { it <= ' ' }
                if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches() || TextUtils.isEmpty(mail)) {
                    mailValidate = false
                    btLogin.isEnabled = false
                    edtMail.error = resources.getString(R.string.not_valid_mail)
                } else {
                    mailValidate = true
                }

                if (mailValidate && passwordValidate) {
                    btLogin.isEnabled = true
                }
            }
        })

        edtPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun afterTextChanged(editable: Editable) {
                val password = edtPassword.text.toString().trim { it <= ' ' }
                if (TextUtils.isEmpty(password) || password.length < 6) {
                    passwordValidate = false
                    btLogin.isEnabled = false
                    edtPassword.error = resources.getString(R.string.not_valid_password)
                } else {
                    passwordValidate = true
                }

                if (mailValidate && passwordValidate) {
                    btLogin.isEnabled = true
                }
            }
        })
    }
}
