package com.tt.handsomeman.ui.handyman.jobs.bid_job_detail;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tt.handsomeman.R;
import com.tt.handsomeman.databinding.FragmentBidJobDetailLetterWritingBinding;

public class BidJobLetterFragment extends Fragment {
    private EditText edtIntroduce;
    private ImageButton ibCheckButtonLetter;
    private String edtValue;
    private FragmentBidJobDetailLetterWritingBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentBidJobDetailLetterWritingBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        BidJobDetail bidJobDetail = (BidJobDetail) getActivity();
        ibCheckButtonLetter = bidJobDetail.activityBidJobDetailBinding.imageButtonCheckLetterBidJobDetail;
        edtIntroduce = binding.introduceYourSelfEditText;
        ibCheckButtonLetter.setEnabled(false);

        edtIntroduce.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                edtValue = edtIntroduce.getText().toString().trim();
                if (TextUtils.isEmpty(edtValue) || edtValue.length() < 10) {
                    ibCheckButtonLetter.setEnabled(false);
                    edtIntroduce.setError(getResources().getString(R.string.introduce_error));
                } else {
                    ibCheckButtonLetter.setEnabled(true);
                }
            }
        });

        ibCheckButtonLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BidJobLetterReviewFragment.setTextViewIntroduceValue(edtValue);
                if (BidJobDetail.mPager.getCurrentItem() < BidJobDetail.NUM_PAGES - 1) {
                    BidJobDetail.mPager.setCurrentItem(BidJobDetail.mPager.getCurrentItem() + 1);
                }
            }
        });
    }
}
