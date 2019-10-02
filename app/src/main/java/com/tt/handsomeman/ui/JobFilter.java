package com.tt.handsomeman.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jaygoo.widget.OnRangeChangedListener;
import com.jaygoo.widget.RangeSeekBar;
import com.tt.handsomeman.R;
import com.tt.handsomeman.adapter.SpinnerCreationTime;

public class JobFilter extends AppCompatActivity {

    private String[] createTime;
    private SeekBar skDistance;
    private TextView tvDistance;
    private RangeSeekBar rgPrice;
    private ImageButton btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_filter);

        createTime = getResources().getStringArray(R.array.create_time);
        Spinner spinnerCreateTime = findViewById(R.id.spinnerCreateTime);
        skDistance = findViewById(R.id.seekBarDistance);
        tvDistance = findViewById(R.id.textViewDistance);
        rgPrice = findViewById(R.id.rangeSeekBarPrice);
        btnClose = findViewById(R.id.imageButtonCloseFilter);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        rangeSeekBarPrice();

        seekBarDistance();

        generateTypeSpinner(spinnerCreateTime);

    }

    private void seekBarDistance() {
        skDistance.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                int val = (progress * (seekBar.getWidth() - 20 * seekBar.getThumbOffset())) / seekBar.getMax();
                tvDistance.setText(progress + " km");
                tvDistance.setX(seekBar.getX() + val + seekBar.getThumbOffset() / 2);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (seekBar.getProgress() == 0) {
                    seekBar.setProgress(1);
                }
            }
        });
    }

    private void rangeSeekBarPrice() {
        rgPrice.setIndicatorTextDecimalFormat("0");
        rgPrice.setProgress(100, 1000);
        rgPrice.getLeftSeekBar().setIndicatorText("$" + (int) rgPrice.getLeftSeekBar().getProgress());
        rgPrice.getRightSeekBar().setIndicatorText("$" + (int) rgPrice.getRightSeekBar().getProgress());
        rgPrice.setOnRangeChangedListener(new OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser) {
                rgPrice.getLeftSeekBar().setIndicatorText("$" + (int) leftValue);
                rgPrice.getRightSeekBar().setIndicatorText("$" + (int) rightValue);
            }

            @Override
            public void onStartTrackingTouch(RangeSeekBar view, boolean isLeft) {
            }

            @Override
            public void onStopTrackingTouch(RangeSeekBar view, boolean isLeft) {
            }
        });
    }

    private void generateTypeSpinner(Spinner spinnerCreateTime) {
        SpinnerCreationTime spinnerCreationTime = new SpinnerCreationTime(JobFilter.this, createTime);
        spinnerCreateTime.setAdapter(spinnerCreationTime);
    }
}
