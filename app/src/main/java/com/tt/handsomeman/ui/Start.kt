package com.tt.handsomeman.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.tt.handsomeman.HandymanApp
import com.tt.handsomeman.R
import com.tt.handsomeman.util.SharedPreferencesUtils

import javax.inject.Inject

class Start : AppCompatActivity(), View.OnClickListener {

    @Inject
    internal var sharedPreferencesUtils: SharedPreferencesUtils? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        HandymanApp.component!!.inject(this)

        val type = sharedPreferencesUtils!!.get<String>("type", String::class.java)

        if (!type.isEmpty()) {
            startActivity(Intent(this@Start, Register::class.java))
            finish()
        }

        findViewById<View>(R.id.continue_handyman).setOnClickListener(this)
        findViewById<View>(R.id.continue_customer).setOnClickListener(this)

    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.continue_handyman -> {
                sharedPreferencesUtils!!.put("type", "handyman")
                startActivity(Intent(this@Start, Register::class.java))
                finish()
            }

            R.id.continue_customer -> {
                sharedPreferencesUtils!!.put("type", "customer")
                Toast.makeText(this@Start, "Customer", Toast.LENGTH_LONG).show()
            }
        }
    }
}
