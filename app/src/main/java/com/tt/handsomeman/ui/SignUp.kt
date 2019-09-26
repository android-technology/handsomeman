package com.tt.handsomeman.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.tt.handsomeman.HandymanApp
import com.tt.handsomeman.R
import com.tt.handsomeman.model.SignUpFormState
import com.tt.handsomeman.response.StandardResponse
import com.tt.handsomeman.service.SignUpService
import com.tt.handsomeman.util.SharedPreferencesUtils
import com.tt.handsomeman.util.StatusCodeConstant
import com.tt.handsomeman.util.StatusConstant
import com.tt.handsomeman.viewmodel.SignUpViewModel

import javax.inject.Inject

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUp : AppCompatActivity() {

    private var signUpViewModel: SignUpViewModel? = null

    @Inject
    internal var sharedPreferencesUtils: SharedPreferencesUtils? = null

    @Inject
    internal var signUpService: SignUpService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        signUpViewModel = ViewModelProviders.of(this).get(SignUpViewModel::class.java!!)

        val cbPassword = findViewById<CheckBox>(R.id.checkboxVisibleSignUpPassword)
        val cbRePassword = findViewById<CheckBox>(R.id.checkboxVisibleSignUpRePassword)
        val edtPassword = findViewById<EditText>(R.id.editTextSignUpPassword)
        val edtRePassword = findViewById<EditText>(R.id.editTextSignUpRePassword)
        val edtName = findViewById<EditText>(R.id.editTextSignUpYourName)
        val edtMail = findViewById<EditText>(R.id.editTextSignUpYourMail)
        val btnSignUp = findViewById<Button>(R.id.buttonSignUp)
        val pgSignUp = findViewById<ProgressBar>(R.id.progressBarSignUp)

        HandymanApp.component!!.inject(this)

        findViewById<View>(R.id.signUpBackButton).setOnClickListener { onBackPressed() }

        observeSignUpState(edtPassword, edtRePassword, edtName, edtMail, btnSignUp)

        edtChangedListener(edtPassword, edtRePassword, edtName, edtMail)

        cbViewPassword(cbPassword, cbRePassword, edtPassword, edtRePassword)

        doSignUp(edtPassword, edtRePassword, edtName, edtMail, btnSignUp, pgSignUp)
    }

    private fun doSignUp(edtPassword: EditText, edtRePassword: EditText, edtName: EditText, edtMail: EditText, btnSignUp: Button, pgSignUp: ProgressBar) {
        val type = sharedPreferencesUtils!!.get<String>("type", String::class.java)

        btnSignUp.setOnClickListener {
            pgSignUp.visibility = View.VISIBLE
            btnSignUp.isEnabled = false
            val name = edtName.text.toString()
            val mail = edtMail.text.toString()
            val password = edtPassword.text.toString()
            val rePassword = edtRePassword.text.toString()

            signUpService!!.doSignUp(type, name, mail, password, rePassword).enqueue(object : Callback<StandardResponse> {
                override fun onResponse(call: Call<StandardResponse>, response: Response<StandardResponse>) {
                    if (response.body()!!.status == StatusConstant.OK && response.body()!!.statusCode == StatusCodeConstant.CREATED) {
                        pgSignUp.visibility = View.GONE
                        Toast.makeText(this@SignUp, response.body()!!.message, Toast.LENGTH_LONG).show()
                        startActivity(Intent(this@SignUp, Login::class.java))
                        finish()
                    } else {
                        pgSignUp.visibility = View.GONE
                        btnSignUp.isEnabled = true
                        Toast.makeText(this@SignUp, response.body()!!.message, Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<StandardResponse>, t: Throwable) {
                    pgSignUp.visibility = View.GONE
                    btnSignUp.isEnabled = true
                    Toast.makeText(this@SignUp, t.message, Toast.LENGTH_LONG).show()
                }
            })
        }
    }

    private fun cbViewPassword(cbPassword: CheckBox, cbRePassword: CheckBox, edtPassword: EditText, edtRePassword: EditText) {
        cbPassword.setOnClickListener {
            if (cbPassword.isChecked) {
                edtPassword.transformationMethod = null
            } else {
                edtPassword.transformationMethod = PasswordTransformationMethod()
            }
            edtPassword.setSelection(edtPassword.length())
        }

        cbRePassword.setOnClickListener {
            if (cbRePassword.isChecked) {
                edtRePassword.transformationMethod = null
            } else {
                edtRePassword.transformationMethod = PasswordTransformationMethod()
            }
            edtRePassword.setSelection(edtRePassword.length())
        }
    }

    private fun edtChangedListener(edtPassword: EditText, edtRePassword: EditText, edtName: EditText, edtMail: EditText) {
        val afterTextChangedListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // ignore
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // ignore
            }

            override fun afterTextChanged(s: Editable) {
                signUpViewModel!!.signUpDateChanged(edtName.text.toString(), edtMail.text.toString(),
                        edtPassword.text.toString(), edtRePassword.text.toString())
            }
        }

        edtName.addTextChangedListener(afterTextChangedListener)
        edtMail.addTextChangedListener(afterTextChangedListener)
        edtPassword.addTextChangedListener(afterTextChangedListener)
        edtRePassword.addTextChangedListener(afterTextChangedListener)
    }

    private fun observeSignUpState(edtPassword: EditText, edtRePassword: EditText, edtName: EditText, edtMail: EditText, btnSignUp: Button) {
        signUpViewModel!!.signUpFormState.observe(this, Observer { signUpFormState ->
            if (signUpFormState == null) {
                return@Observer
            }
            btnSignUp.isEnabled = signUpFormState.isDataValid
            if (signUpFormState.nameError != null) {
                edtName.error = getString(signUpFormState.nameError!!)
            }
            if (signUpFormState.mailError != null) {
                edtMail.error = getString(signUpFormState.mailError!!)
            }
            if (signUpFormState.passwordError != null) {
                edtPassword.error = getString(signUpFormState.passwordError!!)
            }
            if (signUpFormState.rePasswordError != null) {
                edtRePassword.error = getString(signUpFormState.rePasswordError!!)
            }
        })
    }
}
