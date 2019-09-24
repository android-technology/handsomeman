package com.tt.handsomeman.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tt.handsomeman.HandymanApp;
import com.tt.handsomeman.R;
import com.tt.handsomeman.util.Constants;
import com.tt.handsomeman.util.SharedPreferencesUtils;

import javax.inject.Inject;

public class Register extends AppCompatActivity {

    @Inject
    SharedPreferencesUtils sharedPreferencesUtils;

    @SuppressLint("StaticFieldLeak")
    public static Activity register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        HandymanApp.getComponent().inject(this);

        register = this;

        Integer state = sharedPreferencesUtils.get("state", Integer.class);

        if (state.equals(Constants.NOT_ACTIVE_ACCOUNT)) {
            startActivity(new Intent(Register.this, SignUpAddPayout.class));
            finish();
        }

        findViewById(R.id.registerBackButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        findViewById(R.id.loginFBLinear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Login Facebook", Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.buttonRegisterLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });

        findViewById(R.id.buttonRegisterSignUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this, SignUp.class));
            }
        });
    }
}
