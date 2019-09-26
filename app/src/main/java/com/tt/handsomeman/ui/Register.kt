package com.tt.handsomeman.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity

import com.tt.handsomeman.HandymanApp
import com.tt.handsomeman.R
import com.tt.handsomeman.util.Constants
import com.tt.handsomeman.util.SharedPreferencesUtils

import javax.inject.Inject

class Register : AppCompatActivity() {

    @Inject
    internal var sharedPreferencesUtils: SharedPreferencesUtils? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        HandymanApp.component!!.inject(this)

        register = this

        val state = sharedPreferencesUtils!!.get<Int>("state", Int::class.java)

        if (state == Constants.NOT_ACTIVE_ACCOUNT) {
            startActivity(Intent(this@Register, SignUpAddPayout::class.java))
            finish()
        }

        findViewById<View>(R.id.registerBackButton).setOnClickListener { onBackPressed() }

        findViewById<View>(R.id.loginFBLinear).setOnClickListener { Toast.makeText(this@Register, "Login Facebook", Toast.LENGTH_LONG).show() }

        findViewById<View>(R.id.buttonRegisterLogin).setOnClickListener { startActivity(Intent(this@Register, Login::class.java)) }

        findViewById<View>(R.id.buttonRegisterSignUp).setOnClickListener { startActivity(Intent(this@Register, SignUp::class.java)) }
    }

    companion object {

        @SuppressLint("StaticFieldLeak")
        var register: Activity
    }
}
