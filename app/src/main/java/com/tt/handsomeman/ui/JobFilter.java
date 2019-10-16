package com.tt.handsomeman.ui;

import android.content.Intent;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class JobFilter extends AppCompatActivity {

    Integer radius, priceMin, priceMax;
    String dateCreated = null;
    private String[] createTime;
    private SeekBar skDistance;
    private TextView tvDistance;
    private RangeSeekBar rgPrice;
    private ImageButton btnClose, btnCheck;
    private Spinner spinnerCreateTime;
    private Calendar myCalendar = Calendar.getInstance();
    private SimpleDateFormat sdf;
    private String myFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_filter);

        skDistance = findViewById(R.id.seekBarDistance);
        tvDistance = findViewById(R.id.textViewDistance);
        rgPrice = findViewById(R.id.rangeSeekBarPrice);
        btnClose = findViewById(R.id.imageButtonCloseFilter);
        btnCheck = findViewById(R.id.imageButtonCheckFilter);

        createTime = getResources().getStringArray(R.array.create_time);
        spinnerCreateTime = findViewById(R.id.spinnerCreateTime);

        myFormat = "yyyy-MM-dd"; //In which you need put here
        sdf = new SimpleDateFormat(myFormat, Locale.US);

        backPreviousScreen();

        seekBarDistance();

        rangeSeekBarPrice();

        generateTypeSpinner();

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radius = skDistance.getProgress();
                priceMin = (int) rgPrice.getLeftSeekBar().getProgress();
                priceMax = (int) rgPrice.getRightSeekBar().getProgress();
                getCreatedDate();

                Intent intent = new Intent(JobFilter.this, FilterResult.class);
                intent.putExtra("radius", radius);
                intent.putExtra("priceMin", priceMin);
                intent.putExtra("priceMax", priceMax);
                intent.putExtra("dateCreated", dateCreated);
                startActivity(intent);
            }
        });
    }

    private void getCreatedDate() {
        switch (spinnerCreateTime.getSelectedItemPosition()) {
            case 0:
                // Today
                myCalendar.getTime();
                break;
            case 1:
                // Yesterday
                myCalendar.add(Calendar.DATE, -1);
                break;
            case 2:
                // Last 1 week
                myCalendar.add(Calendar.DATE, -7);
                break;
            case 3:
                // Last 1 month
                myCalendar.add(Calendar.MONTH, -1);
                break;
            case 4:
                // Last 3 month
                myCalendar.add(Calendar.MONTH, -3);
                break;
            case 5:
                // All the time
                myCalendar.set(Calendar.YEAR, 1990);
                myCalendar.set(Calendar.MONTH, 0);
                myCalendar.set(Calendar.DATE, 1);
                break;
        }
        dateCreated = sdf.format(myCalendar.getTime());
        // Reset myCalendar to current date (now)
        myCalendar = Calendar.getInstance();
    }

    private void backPreviousScreen() {
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
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

                radius = seekBar.getProgress();
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
                priceMin = (int) view.getLeftSeekBar().getProgress();
                priceMax = (int) view.getRightSeekBar().getProgress();
            }
        });
    }

    private void generateTypeSpinner() {
        SpinnerCreationTime spinnerCreationTime = new SpinnerCreationTime(JobFilter.this, createTime);
        spinnerCreateTime.setAdapter(spinnerCreationTime);
    }
}
