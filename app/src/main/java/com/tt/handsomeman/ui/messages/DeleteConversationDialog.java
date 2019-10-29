package com.tt.handsomeman.ui.messages;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.tt.handsomeman.R;

public class DeleteConversationDialog extends Dialog implements android.view.View.OnClickListener {

    private OnItemClickListener mListener;

    DeleteConversationDialog(Activity a, int theme, DeleteConversationDialog.OnItemClickListener listener) {
        super(a, theme);
        mListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_delete_conversation);
        Button yes = findViewById(R.id.deleteConversationButton);
        Button no = findViewById(R.id.doNotDeleteConversationButton);
        ImageButton ibClose = findViewById(R.id.deleteConversationBackButton);

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
