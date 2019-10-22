package com.tt.handsomeman.ui.messages;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tt.handsomeman.R;

public class MessagesFragment extends Fragment {

    private EditText edtSearchByWord;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_messages, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edtSearchByWord = view.findViewById(R.id.editTextSearchByWordMessageFragment);

        setEditTextHintTextAndIcon();
    }

    private void setEditTextHintTextAndIcon() {
        ImageSpan imageHint = new ImageSpan(getContext(), R.drawable.ic_search_18dp);
        SpannableString spannableString = new SpannableString("    " + getResources().getString(R.string.search_by_word));
        spannableString.setSpan(imageHint, 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        edtSearchByWord.setHint(spannableString);
    }
}