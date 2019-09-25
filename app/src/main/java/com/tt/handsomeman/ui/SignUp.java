package com.tt.handsomeman.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.tt.handsomeman.HandymanApp;
import com.tt.handsomeman.R;
import com.tt.handsomeman.model.SignUpFormState;
import com.tt.handsomeman.response.StandardResponse;
import com.tt.handsomeman.service.SignUpService;
import com.tt.handsomeman.util.SharedPreferencesUtils;
import com.tt.handsomeman.util.StatusCodeConstant;
import com.tt.handsomeman.util.StatusConstant;
import com.tt.handsomeman.viewmodel.SignUpViewModel;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {

    private SignUpViewModel signUpViewModel;

    @Inject
    SharedPreferencesUtils sharedPreferencesUtils;

    @Inject
    SignUpService signUpService;

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
        final ProgressBar pgSignUp = findViewById(R.id.progressBarSignUp);

        HandymanApp.getComponent().inject(this);

        findViewById(R.id.signUpBackButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        observeSignUpState(edtPassword, edtRePassword, edtName, edtMail, btnSignUp);

        edtChangedListener(edtPassword, edtRePassword, edtName, edtMail);

        cbViewPassword(cbPassword, cbRePassword, edtPassword, edtRePassword);

        doSignUp(edtPassword, edtRePassword, edtName, edtMail, btnSignUp, pgSignUp);
    }

    private void doSignUp(EditText edtPassword, EditText edtRePassword, EditText edtName, EditText edtMail, Button btnSignUp, ProgressBar pgSignUp) {
        String type = sharedPreferencesUtils.get("type", String.class);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pgSignUp.setVisibility(View.VISIBLE);
                btnSignUp.setEnabled(false);
                String name = edtName.getText().toString();
                String mail = edtMail.getText().toString();
                String password = edtPassword.getText().toString();
                String rePassword = edtRePassword.getText().toString();

                signUpService.doSignUp(type, name, mail, password, rePassword).enqueue(new Callback<StandardResponse>() {
                    @Override
                    public void onResponse(Call<StandardResponse> call, Response<StandardResponse> response) {
                        if (response.body().getStatus().equals(StatusConstant.OK) && response.body().getStatusCode().equals(StatusCodeConstant.CREATED)) {
                            pgSignUp.setVisibility(View.GONE);
                            Toast.makeText(SignUp.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                            startActivity(new Intent(SignUp.this, Login.class));
                            finish();
                        } else {
                            pgSignUp.setVisibility(View.GONE);
                            btnSignUp.setEnabled(true);
                            Toast.makeText(SignUp.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<StandardResponse> call, Throwable t) {
                        pgSignUp.setVisibility(View.GONE);
                        btnSignUp.setEnabled(true);
                        Toast.makeText(SignUp.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    private void cbViewPassword(CheckBox cbPassword, CheckBox cbRePassword, EditText edtPassword, EditText edtRePassword) {
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

    private void edtChangedListener(EditText edtPassword, EditText edtRePassword, EditText edtName, EditText edtMail) {
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
    }

    private void observeSignUpState(EditText edtPassword, EditText edtRePassword, EditText edtName, EditText edtMail, Button btnSignUp) {
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
    }
}
