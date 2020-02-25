package com.tt.handsomeman.ui.handyman;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tt.handsomeman.HandymanApp;
import com.tt.handsomeman.R;
import com.tt.handsomeman.databinding.ActivityStartBinding;
import com.tt.handsomeman.util.SharedPreferencesUtils;

import javax.inject.Inject;

public class Start extends AppCompatActivity implements View.OnClickListener {

    @Inject
    SharedPreferencesUtils sharedPreferencesUtils;

    private ActivityStartBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        HandymanApp.getComponent().inject(this);

        String type = sharedPreferencesUtils.get("type", String.class);

        if (!type.isEmpty()) {
            startActivity(new Intent(Start.this, Register.class));
            finish();
        }

        binding.continueHandyman.setOnClickListener(this);
        binding.continueCustomer.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.continue_handyman:
                sharedPreferencesUtils.put("type", "handyman");
                startActivity(new Intent(Start.this, Register.class));
                finish();
                break;

            case R.id.continue_customer:
                sharedPreferencesUtils.put("type", "customer");
                Toast.makeText(Start.this, "Customer", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
