package com.tt.handsomeman.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tt.handsomeman.R;
import com.tt.handsomeman.ui.Register;

public class Start extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        findViewById(R.id.continue_handyman).setOnClickListener(this);
        findViewById(R.id.continue_customer).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.continue_handyman:
                startActivity(new Intent(getApplicationContext(), Register.class));
                break;

            case R.id.continue_customer:
                Toast.makeText(getApplicationContext(), "Customer", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
