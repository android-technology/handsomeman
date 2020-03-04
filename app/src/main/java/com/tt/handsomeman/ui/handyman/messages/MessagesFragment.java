package com.tt.handsomeman.ui.handyman.messages;

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
import com.tt.handsomeman.databinding.FragmentMessagesBinding;

public class MessagesFragment extends Fragment {

    private Fragment childMessagesFragment = new MessagesChildMessagesFragment();
    private Fragment childContactsFragment = new MessagesChildContactsFragment();
    private Fragment active = childMessagesFragment;
    private EditText edtSearchByWord;
    private FragmentMessagesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMessagesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RadioButton rdMessage = binding.radioButtonMessages;
        RadioButton rdContact = binding.radioButtonContacts;
        edtSearchByWord = binding.editTextSearchByWordMessageFragment;

        final FragmentManager fm = getChildFragmentManager();
        fm.beginTransaction().add(R.id.messageFragmentParent, childContactsFragment).hide(childContactsFragment).commit();
        fm.beginTransaction().add(R.id.messageFragmentParent, childMessagesFragment).commit();

        setEditTextHintTextAndIcon();

        rdMessage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    fm.beginTransaction().hide(active).show(childMessagesFragment).commit();
                    active = childMessagesFragment;
                }
            }
        });

        rdContact.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    fm.beginTransaction().hide(active).show(childContactsFragment).commit();
                    active = childContactsFragment;
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

    @Override
    public void onDestroy() {
        binding = null;
        super.onDestroy();
    }
}