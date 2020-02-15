package com.tt.handsomeman.ui.handyman.more;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.tt.handsomeman.R;

public class MyProfile extends AppCompatActivity {
    private static final Integer REQUEST_MY_PROFILE_RESULT_CODE = 77;

    private Fragment aboutFragment = new MyProfileAboutFragment();
    private Fragment reviewsFragment = new MyProfileReviewsFragment();
    private Fragment active = aboutFragment;
    private ImageButton ibEdit;
    private boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        RadioButton rdAbout = findViewById(R.id.radioButtonAbout);
        RadioButton rdReviews = findViewById(R.id.radioButtonReviews);
        ibEdit = findViewById(R.id.imageButtonMyProfileEdit);

        findViewById(R.id.myProfileBackButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ibEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aboutFragment.startActivityForResult(new Intent(MyProfile.this, MyProfileEdit.class), REQUEST_MY_PROFILE_RESULT_CODE);
            }
        });

        final FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.myProfileFragmentParent, reviewsFragment).hide(reviewsFragment).commit();
        fm.beginTransaction().add(R.id.myProfileFragmentParent, aboutFragment).commit();

        rdAbout.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ibEdit.setVisibility(View.VISIBLE);
                    fm.beginTransaction().hide(active).show(aboutFragment).commit();
                    active = aboutFragment;
                }
            }
        });

        rdReviews.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ibEdit.setVisibility(View.GONE);
                    fm.beginTransaction().hide(active).show(reviewsFragment).commit();
                    active = reviewsFragment;
                }
            }
        });
    }

    void setEditResult(boolean isEdit) {
        this.isEdit = isEdit;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("isMoreFragmentEdit", isEdit);
        setResult(RESULT_OK, intent);
        finish();
    }
}
