package com.tt.handsomeman.util;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.tt.handsomeman.R;

public class DeleteItemDialog extends Dialog implements android.view.View.OnClickListener {

    private String dialogDescription;
    private OnItemClickListener mListener;

    public DeleteItemDialog(Activity activity, int theme, String dialogDescription, OnItemClickListener listener) {
        super(activity, theme);
        this.dialogDescription = dialogDescription;
        mListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_delete_conversation);
        TextView tvDescription = findViewById(R.id.textViewDeleteDialogDescription);
        Button yes = findViewById(R.id.deleteConversationButton);
        Button no = findViewById(R.id.doNotDeleteConversationButton);
        ImageButton ibClose = findViewById(R.id.deleteConversationBackButton);

        tvDescription.setText(dialogDescription);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);
        ibClose.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.deleteConversationButton:
                mListener.onItemClickYes();
                break;
            case R.id.doNotDeleteConversationButton:
            case R.id.deleteConversationBackButton:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }

    public interface OnItemClickListener {
        void onItemClickYes();
    }
}
