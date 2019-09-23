package com.tt.handsomeman.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.tt.handsomeman.R;
import com.tt.handsomeman.model.SignUpFormState;
import com.tt.handsomeman.viewmodel.SignUpViewModel;

public class SignUp extends AppCompatActivity {

    private SignUpViewModel signUpViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signUpViewModel = ViewModelProviders.of(this).get(SignUpViewModel.class);

        final CheckBox cbPassword = findViewById(R.id.checkboxVisibleSignUpPassword);
        final CheckBox cbRePassword = findViewById(R.id.checkboxVisibleSignUpRePassword);
        final EditText edtPassword = findViewById(R.id.editTextSignUpPassword);
        final EditText edtRePassword = findViewById(R.id.editTextSignUpRePassword);
        final EditText edtName = findViewById(R.id.editTextSignUpYourName);
        final EditText edtMail = findViewById(R.id.editTextSignUpYourMail);
        final Button btnSignUp = findViewById(R.id.buttonSignUp);

        findViewById(R.id.signUpBackButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        signUpViewModel.getSignUpFormState().observe(this, new Observer<SignUpFormState>() {
            @Override
            public void onChanged(SignUpFormState signUpFormState) {
                if (signUpFormState == null) {
                    return;
                }
                btnSignUp.setEnabled(signUpFormState.isDataValid());
                if (signUpFormState.getNameError() != null) {
                    edtName.setError(getString(signUpFormState.getNameError()));
                }
                if (signUpFormState.getMailError() != null) {
                    edtMail.setError(getString(signUpFormState.getMailError()));
                }
                if (signUpFormState.getPasswordError() != null) {
                    edtPassword.setError(getString(signUpFormState.getPasswordError()));
                }
                if (signUpFormState.getRePasswordError() != null) {
                    edtRePassword.setError(getString(signUpFormState.getRePasswordError()));
                }
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                signUpViewModel.signUpDateChanged(edtName.getText().toString(), edtMail.getText().toString(),
                        edtPassword.getText().toString(), edtRePassword.getText().toString());
            }
        };

        edtName.addTextChangedListener(afterTextChangedListener);
        edtMail.addTextChangedListener(afterTextChangedListener);
        edtPassword.addTextChangedListener(afterTextChangedListener);
        edtRePassword.addTextChangedListener(afterTextChangedListener);

        cbPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cbPassword.isChecked()) {
                    edtPassword.setTransformationMethod(null);
                } else {
                    edtPassword.setTransformationMethod(new PasswordTransformationMethod());
                }
                edtPassword.setSelection(edtPassword.length());
            }
        });

        cbRePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cbRePassword.isChecked()) {
                    edtRePassword.setTransformationMethod(null);
                } else {
                    edtRePassword.setTransformationMethod(new PasswordTransformationMethod());
                }
                edtRePassword.setSelection(edtRePassword.length());
            }
        });


    }
}
