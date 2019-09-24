package com.tt.handsomeman.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tt.handsomeman.HandymanApp;
import com.tt.handsomeman.R;
import com.tt.handsomeman.util.SharedPreferencesUtils;

import javax.inject.Inject;

public class Start extends AppCompatActivity implements View.OnClickListener {

    @Inject
    SharedPreferencesUtils sharedPreferencesUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        HandymanApp.getComponent().inject(this);

        String type = sharedPreferencesUtils.get("type", String.class);

        if (!type.isEmpty()) {
            startActivity(new Intent(Start.this, Register.class));
            finish();
        }

        findViewById(R.id.continue_handyman).setOnClickListener(this);
        findViewById(R.id.continue_customer).setOnClickListener(this);

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
