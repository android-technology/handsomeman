package com.tt.handsomeman.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.tt.handsomeman.R;

public class OnBoardingSlidePageFragment extends Fragment {

    int image;
    int description;

    public static OnBoardingSlidePageFragment newInstance(int image, int description) {
        OnBoardingSlidePageFragment screenSlidePageFragment = new OnBoardingSlidePageFragment();
        Bundle args = new Bundle();
        args.putInt("image", image);
        args.putInt("description", description);
        screenSlidePageFragment.setArguments(args);
        return screenSlidePageFragment;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        image = getArguments().getInt("image", 0);
        description = getArguments().getInt("description");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.on_boarding_fragment_slide_page, container, false);

        ImageView imgOnBoarding = rootView.findViewById(R.id.imageViewOnBoarding);
        TextView txtOnBoarding = rootView.findViewById(R.id.textViewOnBoarding);
        imgOnBoarding.setImageResource(image);
        txtOnBoarding.setText(description);

        return rootView;
    }
}
