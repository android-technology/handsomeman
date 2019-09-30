package com.tt.handsomeman.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.tt.handsomeman.R;

public class ForgotPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        final EditText edtForgotPassword = findViewById(R.id.editTextForgotPasswordYourMail);
        final Button btnForgotPassword = findViewById(R.id.buttonSendForgotPassword);

        findViewById(R.id.forgotPasswordBackButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        edtForgotPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(edtForgotPassword.getText().toString()) || !(Patterns.EMAIL_ADDRESS.matcher(edtForgotPassword.getText().toString()).matches())) {
                    btnForgotPassword.setEnabled(false);
                    edtForgotPassword.setError(getResources().getString(R.string.not_valid_mail));
                } else {
                    btnForgotPassword.setEnabled(true);
                }
            }
        });
    }
}
