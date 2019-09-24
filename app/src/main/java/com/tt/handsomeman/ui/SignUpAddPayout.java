package com.tt.handsomeman.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.tt.handsomeman.R;
import com.tt.handsomeman.adapter.SpinnerCountryPayout;
import com.tt.handsomeman.adapter.SpinnerTypePayout;
import com.tt.handsomeman.model.SignUpAddPayoutFormState;
import com.tt.handsomeman.viewmodel.SignUpAddPayoutViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SignUpAddPayout extends AppCompatActivity {
    String[] type, country;
    EditText edtBirthday;
    ImageButton ibBirthDay, ibCheck;
    Calendar myCalendar = Calendar.getInstance();

    private SignUpAddPayoutViewModel signUpAddPayoutViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_add_payout);
        type = getResources().getStringArray(R.array.type_array);
        country = getResources().getStringArray(R.array.countries_array);

        Spinner spinnerType = findViewById(R.id.spinnerTypePayout);
        Spinner spinnerCountry = findViewById(R.id.spinnerCountryPayout);
        ibBirthDay = findViewById(R.id.imageButtonSignUpBirthday);
        ibCheck = findViewById(R.id.imageButtonCheckSignUpPayout);
        ibCheck.setEnabled(false);

        findViewById(R.id.signUpPayoutBackButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        ibCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SignUpAddPayout.this, "Test", Toast.LENGTH_LONG).show();
            }
        });

        final EditText edtFirstName = findViewById(R.id.editTextFirstNameSignUpPayout);
        final EditText edtLastName = findViewById(R.id.editTextLastNameSignUpPayout);
        final EditText edtAddress = findViewById(R.id.editTextAddressSignUpPayout);
        final EditText edtPortalCode = findViewById(R.id.editTextPortalCodeSignUpPayout);
        final EditText edtEmail = findViewById(R.id.editTextEmailSignUpPayout);
        final EditText edtAccountNumber = findViewById(R.id.editTextAccountNumberSignUpPayout);
        final EditText edtAccountRouting = findViewById(R.id.editTextAccountRoutingSignUpPayout);
        edtBirthday = findViewById(R.id.editTextBirthdaySignUpPayout);


        signUpAddPayoutViewModel = ViewModelProviders.of(this).get(SignUpAddPayoutViewModel.class);

        signUpAddPayoutViewModel.getUpAddPayoutFormState().observe(this, new Observer<SignUpAddPayoutFormState>() {
            @Override
            public void onChanged(SignUpAddPayoutFormState signUpAddPayoutFormState) {
                if (signUpAddPayoutFormState == null) {
                    return;
                }
                ibCheck.setEnabled(signUpAddPayoutFormState.isDataValid());
                if (signUpAddPayoutFormState.getFirstNameError() != null) {
                    ibCheck.setEnabled(signUpAddPayoutFormState.isDataValid());
                    edtFirstName.setError(getString(signUpAddPayoutFormState.getFirstNameError()));
                }
                if (signUpAddPayoutFormState.getLastNameError() != null) {
                    ibCheck.setEnabled(signUpAddPayoutFormState.isDataValid());
                    edtLastName.setError(getString(signUpAddPayoutFormState.getLastNameError()));
                }
                if (signUpAddPayoutFormState.getAddressError() != null) {
                    ibCheck.setEnabled(signUpAddPayoutFormState.isDataValid());
                    edtAddress.setError(getString(signUpAddPayoutFormState.getAddressError()));
                }
                if (signUpAddPayoutFormState.getPortalCodeError() != null) {
                    ibCheck.setEnabled(signUpAddPayoutFormState.isDataValid());
                    edtPortalCode.setError(getString(signUpAddPayoutFormState.getPortalCodeError()));
                }
                if (signUpAddPayoutFormState.getEmailError() != null) {
                    ibCheck.setEnabled(signUpAddPayoutFormState.isDataValid());
                    edtEmail.setError(getString(signUpAddPayoutFormState.getEmailError()));
                }
                if (signUpAddPayoutFormState.getAccountNumberError() != null) {
                    ibCheck.setEnabled(signUpAddPayoutFormState.isDataValid());
                    edtAccountNumber.setError(getString(signUpAddPayoutFormState.getAccountNumberError()));
                }
                if (signUpAddPayoutFormState.getAccountRoutingError() != null) {
                    ibCheck.setEnabled(signUpAddPayoutFormState.isDataValid());
                    edtAccountRouting.setError(getString(signUpAddPayoutFormState.getAccountRoutingError()));
                }
                if (signUpAddPayoutFormState.getBirthdayError() != null) {
                    ibCheck.setEnabled(signUpAddPayoutFormState.isDataValid());
                    edtBirthday.setError(getString(signUpAddPayoutFormState.getBirthdayError()));
                }
            }
        });

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                signUpAddPayoutViewModel.signUpPayOutDateChanged(edtFirstName.getText().toString(), edtLastName.getText().toString(), edtAddress.getText().toString(),
                        edtPortalCode.getText().toString(), edtEmail.getText().toString(), edtAccountNumber.getText().toString(),
                        edtAccountRouting.getText().toString(), edtBirthday.getText().toString());
            }
        };

        edtFirstName.addTextChangedListener(textWatcher);
        edtLastName.addTextChangedListener(textWatcher);
        edtAddress.addTextChangedListener(textWatcher);
        edtPortalCode.addTextChangedListener(textWatcher);
        edtEmail.addTextChangedListener(textWatcher);
        edtAccountNumber.addTextChangedListener(textWatcher);
        edtAccountRouting.addTextChangedListener(textWatcher);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        ibBirthDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                showDatePickerDialog(date);
            }
        });

        edtBirthday.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                showDatePickerDialog(date);
            }
        });

        generateTypeSpinner(spinnerType);
        generateCountrySpinner(spinnerCountry);
    }

    private void generateCountrySpinner(Spinner spinnerCountry) {
        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        SpinnerCountryPayout spinnerCountryPayout = new SpinnerCountryPayout(SignUpAddPayout.this, country);
        spinnerCountry.setAdapter(spinnerCountryPayout);
    }

    private void generateTypeSpinner(Spinner spinnerType) {
        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        SpinnerTypePayout spinnerTypePayout = new SpinnerTypePayout(SignUpAddPayout.this, type);
        spinnerType.setAdapter(spinnerTypePayout);
    }

//    private void showDatePickerDialog(DatePickerDialog.OnDateSetListener date) {
//        new DatePickerDialog(SignUpAddPayout.this, date, myCalendar
//                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
//                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
//    }

    private void showDatePickerDialog(DatePickerDialog.OnDateSetListener date) {
        new DatePickerDialog(SignUpAddPayout.this, date, 1996, 11, 26).show();
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edtBirthday.setText(sdf.format(myCalendar.getTime()));
        edtBirthday.setError(null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Register.register = null;
    }
}
