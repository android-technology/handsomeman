package com.tt.handsomeman.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tt.handsomeman.HandymanApp;
import com.tt.handsomeman.R;
import com.tt.handsomeman.response.LoginResponse;
import com.tt.handsomeman.service.LoginService;
import com.tt.handsomeman.util.SharedPreferencesUtils;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    CheckBox cbVisiblePassword;
    EditText edtMail, edtPassword;
    Button btLogin, btForgot;
    ProgressBar pgLogin;

    boolean mailValidate = false;
    boolean passwordValidate = false;

    @Inject
    LoginService loginService;

    @Inject
    SharedPreferencesUtils sharedPreferencesUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        cbVisiblePassword = findViewById(R.id.checkboxLoginVisiblePassword);
        edtMail = findViewById(R.id.editTextLoginYourMail);
        edtPassword = findViewById(R.id.editTextLoginPassword);
        btLogin = findViewById(R.id.buttonLogin);
        btForgot = findViewById(R.id.buttonForgotPassword);
        pgLogin = findViewById(R.id.progressBarLogin);

        HandymanApp.getComponent().inject(this);

        findViewById(R.id.loginBackButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        edtMail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String mail = edtMail.getText().toString().trim();
                if (!(Patterns.EMAIL_ADDRESS.matcher(mail).matches()) || TextUtils.isEmpty(mail)) {
                    mailValidate = false;
                    btLogin.setEnabled(false);
                    edtMail.setError(getResources().getString(R.string.not_valid_mail));
                } else {
                    mailValidate = true;
                }

                if (mailValidate && passwordValidate) {
                    btLogin.setEnabled(true);
                }
            }
        });

        edtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String password = edtPassword.getText().toString().trim();
                if (TextUtils.isEmpty(password) || password.length() < 6) {
                    passwordValidate = false;
                    btLogin.setEnabled(false);
                    edtPassword.setError(getResources().getString(R.string.not_valid_password));
                } else {
                    passwordValidate = true;
                }

                if (mailValidate && passwordValidate) {
                    btLogin.setEnabled(true);
                }
            }
        });

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                pgLogin.setVisibility(View.VISIBLE);
                String mail = edtMail.getText().toString();
                String password = edtPassword.getText().toString();

                loginService.doLogin(mail, password).enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.body() != null && response.body().getData() != null) {
                            String token = response.body().getData().getToken();
                            Integer state = response.body().getData().getState();
                            pgLogin.setVisibility(View.GONE);

                            sharedPreferencesUtils.put("token", token);
                            sharedPreferencesUtils.put("state", state);

                            startActivity(new Intent(Login.this, SignUpAddPayout.class));
                            Register.register.finish();
                            finish();
                        } else {
                            pgLogin.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        pgLogin.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        btForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, ForgotPassword.class));
            }
        });

        cbVisiblePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cbVisiblePassword.isChecked()) {
                    edtPassword.setTransformationMethod(null);
                } else {
                    edtPassword.setTransformationMethod(new PasswordTransformationMethod());
                }
                edtPassword.setSelection(edtPassword.length());
            }
        });
    }
}
