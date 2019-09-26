package com.tt.handsomeman.ui

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.AdapterView
import android.widget.DatePicker
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.tt.handsomeman.HandymanApp
import com.tt.handsomeman.R
import com.tt.handsomeman.adapter.SpinnerCountryPayout
import com.tt.handsomeman.adapter.SpinnerTypePayout
import com.tt.handsomeman.model.SignUpAddPayoutFormState
import com.tt.handsomeman.model.UserActivatingAccount
import com.tt.handsomeman.response.StandardResponse
import com.tt.handsomeman.service.SignUpAddPayoutService
import com.tt.handsomeman.util.SharedPreferencesUtils
import com.tt.handsomeman.util.StatusCodeConstant
import com.tt.handsomeman.util.StatusConstant
import com.tt.handsomeman.viewmodel.SignUpAddPayoutViewModel

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

import javax.inject.Inject

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpAddPayout : AppCompatActivity() {
    internal var type: Array<String>
    internal var country: Array<String>
    internal var edtBirthday: EditText
    internal var ibBirthDay: ImageButton
    internal var ibCheck: ImageButton
    internal var myCalendar = Calendar.getInstance()

    private var signUpAddPayoutViewModel: SignUpAddPayoutViewModel? = null

    @Inject
    internal var sharedPreferencesUtils: SharedPreferencesUtils? = null

    @Inject
    internal var signUpAddPayoutService: SignUpAddPayoutService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_add_payout)

        type = resources.getStringArray(R.array.type_array)
        country = resources.getStringArray(R.array.countries_array)

        val edtFirstName = findViewById<EditText>(R.id.editTextFirstNameSignUpPayout)
        val edtLastName = findViewById<EditText>(R.id.editTextLastNameSignUpPayout)
        val edtAddress = findViewById<EditText>(R.id.editTextAddressSignUpPayout)
        val edtPortalCode = findViewById<EditText>(R.id.editTextPortalCodeSignUpPayout)
        val edtEmail = findViewById<EditText>(R.id.editTextEmailSignUpPayout)
        val edtAccountNumber = findViewById<EditText>(R.id.editTextAccountNumberSignUpPayout)
        val edtAccountRouting = findViewById<EditText>(R.id.editTextAccountRoutingSignUpPayout)
        edtBirthday = findViewById(R.id.editTextBirthdaySignUpPayout)
        val progressBarHolder = findViewById<FrameLayout>(R.id.progressBarHolder)

        val spinnerType = findViewById<Spinner>(R.id.spinnerTypePayout)
        val spinnerCountry = findViewById<Spinner>(R.id.spinnerCountryPayout)
        ibBirthDay = findViewById(R.id.imageButtonSignUpBirthday)
        ibCheck = findViewById(R.id.imageButtonCheckSignUpPayout)
        ibCheck.isEnabled = false

        HandymanApp.component!!.inject(this)
        signUpAddPayoutViewModel = ViewModelProviders.of(this).get(SignUpAddPayoutViewModel::class.java!!)

        findViewById<View>(R.id.signUpPayoutBackButton).setOnClickListener { onBackPressed() }

        observeSignUpAddPayoutState(edtFirstName, edtLastName, edtAddress, edtPortalCode, edtEmail, edtAccountNumber, edtAccountRouting)

        edtChangedListener(edtFirstName, edtLastName, edtAddress, edtPortalCode, edtEmail, edtAccountNumber, edtAccountRouting)


        val date = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, monthOfYear)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLabel()
        }
        showBirthdayPickerDialog(date)

        generateTypeSpinner(spinnerType)
        generateCountrySpinner(spinnerCountry)

        doAddPayout(edtFirstName, edtLastName, edtAddress, edtPortalCode, edtEmail, edtAccountNumber, edtAccountRouting, progressBarHolder, spinnerType, spinnerCountry)
    }

    private fun doAddPayout(edtFirstName: EditText, edtLastName: EditText, edtAddress: EditText, edtPortalCode: EditText, edtEmail: EditText, edtAccountNumber: EditText, edtAccountRouting: EditText, progressBarHolder: FrameLayout, spinnerType: Spinner, spinnerCountry: Spinner) {
        ibCheck.setOnClickListener {
            val inAnimation: AlphaAnimation

            progressBarHolder.bringToFront()
            inAnimation = AlphaAnimation(0f, 1f)
            inAnimation.duration = 300
            progressBarHolder.animation = inAnimation
            progressBarHolder.visibility = View.VISIBLE

            ibCheck.isEnabled = false

            val token = sharedPreferencesUtils!!.get<String>("token", String::class.java)

            val myFormat = "yyyy-MM-dd" //In which you need put here
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            edtBirthday.setText(sdf.format(myCalendar.time))

            val firstName = edtFirstName.text.toString()
            val lastName = edtLastName.text.toString()
            val address = edtAddress.text.toString()
            val portalCode = Integer.parseInt(edtPortalCode.text.toString())
            val birthday = sdf.format(myCalendar.time)
            val selectedType = type[spinnerType.selectedItemPosition]
            val email = edtEmail.text.toString()
            val accountNumber = edtAccountNumber.text.toString()
            val accountRouting = edtAccountRouting.text.toString()
            val selectedCountry = country[spinnerCountry.selectedItemPosition]
            val accountStatus = "T" // only one character
            val businessNumber = "123456"

            val kindOfHandyman = "CPR"

            val userActivatingAccount = UserActivatingAccount(firstName, lastName, address, portalCode, birthday, selectedType, email, accountNumber, accountRouting, selectedCountry, accountStatus, businessNumber)

            signUpAddPayoutService!!.doSignUpAddPayout(token, userActivatingAccount, kindOfHandyman).enqueue(object : Callback<StandardResponse> {
                override fun onResponse(call: Call<StandardResponse>, response: Response<StandardResponse>) {
                    if (response.body()!!.status == StatusConstant.OK && response.body()!!.statusCode == StatusCodeConstant.OK) {
                        Toast.makeText(this@SignUpAddPayout, response.body()!!.message, Toast.LENGTH_LONG).show()
                        startActivity(Intent(this@SignUpAddPayout, HandyManMainScreen::class.java))
                        finish()
                    } else {
                        val outAnimation: AlphaAnimation

                        outAnimation = AlphaAnimation(1f, 0f)
                        outAnimation.duration = 200
                        progressBarHolder.animation = outAnimation
                        progressBarHolder.visibility = View.GONE
                        ibCheck.isEnabled = true
                        Toast.makeText(this@SignUpAddPayout, response.body()!!.message, Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<StandardResponse>, t: Throwable) {
                    val outAnimation: AlphaAnimation

                    outAnimation = AlphaAnimation(1f, 0f)
                    outAnimation.duration = 200
                    progressBarHolder.animation = outAnimation
                    progressBarHolder.visibility = View.GONE
                    ibCheck.isEnabled = true
                    Toast.makeText(this@SignUpAddPayout, t.message, Toast.LENGTH_LONG).show()
                }
            })
        }
    }

    private fun showBirthdayPickerDialog(date: DatePickerDialog.OnDateSetListener) {
        ibBirthDay.setOnClickListener {
            // TODO Auto-generated method stub
            showDatePickerDialog(date)
        }

        edtBirthday.setOnClickListener {
            // TODO Auto-generated method stub
            showDatePickerDialog(date)
        }
    }

    private fun edtChangedListener(edtFirstName: EditText, edtLastName: EditText, edtAddress: EditText, edtPortalCode: EditText, edtEmail: EditText, edtAccountNumber: EditText, edtAccountRouting: EditText) {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun afterTextChanged(editable: Editable) {
                signUpAddPayoutViewModel!!.signUpPayOutDateChanged(edtFirstName.text.toString(), edtLastName.text.toString(), edtAddress.text.toString(),
                        edtPortalCode.text.toString(), edtEmail.text.toString(), edtAccountNumber.text.toString(),
                        edtAccountRouting.text.toString(), edtBirthday.text.toString())
            }
        }

        edtFirstName.addTextChangedListener(textWatcher)
        edtLastName.addTextChangedListener(textWatcher)
        edtAddress.addTextChangedListener(textWatcher)
        edtPortalCode.addTextChangedListener(textWatcher)
        edtEmail.addTextChangedListener(textWatcher)
        edtAccountNumber.addTextChangedListener(textWatcher)
        edtAccountRouting.addTextChangedListener(textWatcher)
    }

    private fun observeSignUpAddPayoutState(edtFirstName: EditText, edtLastName: EditText, edtAddress: EditText, edtPortalCode: EditText, edtEmail: EditText, edtAccountNumber: EditText, edtAccountRouting: EditText) {
        signUpAddPayoutViewModel!!.upAddPayoutFormState.observe(this, Observer { signUpAddPayoutFormState ->
            if (signUpAddPayoutFormState == null) {
                return@Observer
            }
            ibCheck.isEnabled = signUpAddPayoutFormState.isDataValid
            if (signUpAddPayoutFormState.firstNameError != null) {
                ibCheck.isEnabled = signUpAddPayoutFormState.isDataValid
                edtFirstName.error = getString(signUpAddPayoutFormState.firstNameError!!)
            }
            if (signUpAddPayoutFormState.lastNameError != null) {
                ibCheck.isEnabled = signUpAddPayoutFormState.isDataValid
                edtLastName.error = getString(signUpAddPayoutFormState.lastNameError!!)
            }
            if (signUpAddPayoutFormState.addressError != null) {
                ibCheck.isEnabled = signUpAddPayoutFormState.isDataValid
                edtAddress.error = getString(signUpAddPayoutFormState.addressError!!)
            }
            if (signUpAddPayoutFormState.portalCodeError != null) {
                ibCheck.isEnabled = signUpAddPayoutFormState.isDataValid
                edtPortalCode.error = getString(signUpAddPayoutFormState.portalCodeError!!)
            }
            if (signUpAddPayoutFormState.emailError != null) {
                ibCheck.isEnabled = signUpAddPayoutFormState.isDataValid
                edtEmail.error = getString(signUpAddPayoutFormState.emailError!!)
            }
            if (signUpAddPayoutFormState.accountNumberError != null) {
                ibCheck.isEnabled = signUpAddPayoutFormState.isDataValid
                edtAccountNumber.error = getString(signUpAddPayoutFormState.accountNumberError!!)
            }
            if (signUpAddPayoutFormState.accountRoutingError != null) {
                ibCheck.isEnabled = signUpAddPayoutFormState.isDataValid
                edtAccountRouting.error = getString(signUpAddPayoutFormState.accountRoutingError!!)
            }
            if (signUpAddPayoutFormState.birthdayError != null) {
                ibCheck.isEnabled = signUpAddPayoutFormState.isDataValid
                edtBirthday.error = getString(signUpAddPayoutFormState.birthdayError!!)
            }
        })
    }

    private fun generateTypeSpinner(spinnerType: Spinner) {
        val spinnerTypePayout = SpinnerTypePayout(this@SignUpAddPayout, type)
        spinnerType.adapter = spinnerTypePayout
    }

    private fun generateCountrySpinner(spinnerCountry: Spinner) {
        val spinnerCountryPayout = SpinnerCountryPayout(this@SignUpAddPayout, country)
        spinnerCountry.adapter = spinnerCountryPayout
    }

    //    private void showDatePickerDialog(DatePickerDialog.OnDateSetListener date) {
    //        new DatePickerDialog(SignUpAddPayout.this, date, myCalendar
    //                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
    //                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    //    }

    private fun showDatePickerDialog(date: DatePickerDialog.OnDateSetListener) {
        DatePickerDialog(this@SignUpAddPayout, date, 1996, 11, 26).show()
    }

    private fun updateLabel() {
        val myFormat = "dd/MM/yyyy" //In which you need put here
        val sdf = SimpleDateFormat(myFormat, Locale.US)

        edtBirthday.setText(sdf.format(myCalendar.time))
        edtBirthday.error = null
    }

    override fun onDestroy() {
        super.onDestroy()
        Register.register = null
    }
}
