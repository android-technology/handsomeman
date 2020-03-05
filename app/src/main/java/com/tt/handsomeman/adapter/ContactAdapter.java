package com.tt.handsomeman.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tt.handsomeman.databinding.ItemContactBinding;
import com.tt.handsomeman.databinding.ItemContactHeaderBinding;
import com.tt.handsomeman.response.Contact;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int HEADER = 0;
    public static final int ITEM = 1;

    private List<Contact> contactList;
    private Context context;
    private LayoutInflater layoutInflater;
    private ItemContactBinding contactBinding;
    private ItemContactHeaderBinding headerBinding;

    public ContactAdapter(List<Contact> contactList, Context context) {
        this.contactList = contactList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case HEADER:
                headerBinding = ItemContactHeaderBinding.inflate(layoutInflater, parent, false);
                view = headerBinding.getRoot();
                return new ContactAdapter.HeaderViewHolder(view);
            case ITEM:
                contactBinding = ItemContactBinding.inflate(layoutInflater, parent, false);
                view = contactBinding.getRoot();
                return new ContactAdapter.ContactViewHolder(view);
            default:
                throw new IllegalStateException("unsupported item type");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Contact contact = contactList.get(position);

        switch (holder.getItemViewType()) {
            case HEADER:
                ContactAdapter.HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
                headerViewHolder.tvHeaderCharacter.setText(contact.getFirstCharacter(contact.getName()));
                headerViewHolder.tvContactNameHeader.setText(contact.getName());
                break;
            case ITEM:
                ContactAdapter.ContactViewHolder contactViewHolder1 = (ContactViewHolder) holder;
                contactViewHolder1.tvContactName.setText(contact.getName());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (contactList.get(position).getIsHeader()) {
            return HEADER;
        } else {
            return ITEM;
        }
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvHeaderCharacter;
        private final ImageView imgContactAvatarHeader;
        private final TextView tvContactNameHeader;

        HeaderViewHolder(@NonNull View itemView) {
            super(itemView);

            tvHeaderCharacter = headerBinding.textViewHeaderCharacter;
            imgContactAvatarHeader = headerBinding.imageViewContactAvatarHeader;
            tvContactNameHeader = headerBinding.textViewContactNameHeader;
        }
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imgContactAvatar;
        private final TextView tvContactName;

        ContactViewHolder(@NonNull View itemView) {
            super(itemView);

            imgContactAvatar = contactBinding.imageViewContactAvatar;
            tvContactName = contactBinding.textViewContactName;
        }
    }
}
