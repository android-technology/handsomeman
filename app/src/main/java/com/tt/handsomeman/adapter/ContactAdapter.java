package com.tt.handsomeman.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tt.handsomeman.R;
import com.tt.handsomeman.response.Contact;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int HEADER = 0;
    public static final int ITEM = 1;

    private List<Contact> contactList;
    private Context context;
    private LayoutInflater layoutInflater;

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
                view = layoutInflater.inflate(R.layout.item_contact_header, parent, false);
                return new ContactAdapter.HeaderViewHolder(view);
            case ITEM:
                view = layoutInflater.inflate(R.layout.item_contact, parent, false);
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

        TextView tvHeaderCharacter;
        ImageView imgContactAvatarHeader;
        TextView tvContactNameHeader;

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);

            tvHeaderCharacter = itemView.findViewById(R.id.textViewHeaderCharacter);
            imgContactAvatarHeader = itemView.findViewById(R.id.imageViewContactAvatarHeader);
            tvContactNameHeader = itemView.findViewById(R.id.textViewContactNameHeader);
        }
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {

        ImageView imgContactAvatar;
        TextView tvContactName;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);

            imgContactAvatar = itemView.findViewById(R.id.imageViewContactAvatar);
            tvContactName = itemView.findViewById(R.id.textViewContactName);
        }
    }
}
