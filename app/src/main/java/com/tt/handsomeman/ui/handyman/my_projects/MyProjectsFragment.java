package com.tt.handsomeman.ui.handyman.my_projects;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.tt.handsomeman.R;

public class MyProjectsFragment extends Fragment {

    private Fragment childInProgressFragment = new MyProjectsChildInProgressFragment();
    private Fragment childInPastFragment = new MyProjectsChildInPastFragment();
    private Fragment active = childInProgressFragment;
    private EditText edtSearchByWord;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_projects, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RadioButton rdInProgress = view.findViewById(R.id.radioButtonInProgress);
        RadioButton rdInPast = view.findViewById(R.id.radioButtonInPast);
        edtSearchByWord = view.findViewById(R.id.editTextSearchByWordMyProjectFragment);

        final FragmentManager fm = getChildFragmentManager();
        fm.beginTransaction().add(R.id.myProjectFragmentParent, childInPastFragment).hide(childInPastFragment).commit();
        fm.beginTransaction().add(R.id.myProjectFragmentParent, childInProgressFragment).commit();

        setEditTextHintTextAndIcon();

        rdInProgress.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    fm.beginTransaction().hide(active).show(childInProgressFragment).commit();
                    active = childInProgressFragment;
                }
            }
        });

        rdInPast.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    fm.beginTransaction().hide(active).show(childInPastFragment).commit();
                    active = childInPastFragment;
                }
            }
        });
    }

    private void setEditTextHintTextAndIcon() {
        ImageSpan imageHint = new ImageSpan(getContext(), R.drawable.ic_search_18dp);
        SpannableString spannableString = new SpannableString("    " + getResources().getString(R.string.search_by_word));
        spannableString.setSpan(imageHint, 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        edtSearchByWord.setHint(spannableString);
    }
}
