package com.tt.handsomeman.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

import com.tt.handsomeman.R;
import com.tt.handsomeman.util.ImageHelper;

public class JobDetail extends AppCompatActivity {

    private ImageView mapView, avatar;
    private RatingBar rtReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail);

        mapView = findViewById(R.id.mapViewJobDetail);
        avatar = findViewById(R.id.clientAvatarJobDetail);
        rtReview = findViewById(R.id.ratingBarJobDetail);

        int pixel = dpToPx(10, this);
        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.map_2);
        Bitmap afterEdit = ImageHelper.getRoundedCornerBitmap(icon, pixel);

        mapView.setImageBitmap(afterEdit);

        rtReview.setRating(3);
    }

    public int dpToPx(float dp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }
}
