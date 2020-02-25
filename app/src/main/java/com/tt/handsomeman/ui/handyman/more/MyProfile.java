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
import com.tt.handsomeman.databinding.ActivityMyProfileBinding;

public class MyProfile extends AppCompatActivity {
    private static final Integer REQUEST_MY_PROFILE_RESULT_CODE = 77;

    private Fragment aboutFragment = new MyProfileAboutFragment();
    private Fragment reviewsFragment = new MyProfileReviewsFragment();
    private Fragment active = aboutFragment;
    private ImageButton ibEdit;
    private boolean isEdit = false;
    private ActivityMyProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        RadioButton rdAbout = binding.radioButtonAbout;
        RadioButton rdReviews = binding.radioButtonReviews;
        ibEdit = binding.imageButtonMyProfileEdit;

        binding.myProfileBackButton.setOnClickListener(new View.OnClickListener() {
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
