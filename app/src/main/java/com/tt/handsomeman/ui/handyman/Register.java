package com.tt.handsomeman.ui.handyman;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tt.handsomeman.HandymanApp;
import com.tt.handsomeman.databinding.ActivityRegisterBinding;
import com.tt.handsomeman.util.Constants;
import com.tt.handsomeman.util.SharedPreferencesUtils;

import javax.inject.Inject;

public class Register extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    public static Activity register;
    @Inject
    SharedPreferencesUtils sharedPreferencesUtils;
    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        HandymanApp.getComponent().inject(this);

        register = this;

        Integer state = sharedPreferencesUtils.get("state", Integer.class);

        if (state.equals(Constants.NOT_ACTIVE_ACCOUNT)) {
            startActivity(new Intent(Register.this, SignUpAddPayout.class));
            finish();
        }

        binding.registerBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.loginFBLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Register.this, "Login Facebook", Toast.LENGTH_LONG).show();
            }
        });

        binding.buttonRegisterLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });

        binding.buttonRegisterSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this, SignUp.class));
            }
        });
    }
}
