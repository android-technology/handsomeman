package com.tt.handsomeman.ui.handyman.messages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tt.handsomeman.HandymanApp;
import com.tt.handsomeman.R;
import com.tt.handsomeman.adapter.ContactAdapter;
import com.tt.handsomeman.databinding.FragmentMessagesChildContactsBinding;
import com.tt.handsomeman.response.Contact;
import com.tt.handsomeman.ui.BaseFragment;
import com.tt.handsomeman.util.ContactDivider;
import com.tt.handsomeman.util.SharedPreferencesUtils;
import com.tt.handsomeman.viewmodel.MessageViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import jp.wasabeef.recyclerview.animators.FadeInLeftAnimator;

public class MessagesChildContactsFragment extends BaseFragment<MessageViewModel, FragmentMessagesChildContactsBinding> {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    SharedPreferencesUtils sharedPreferencesUtils;
    private ContactAdapter contactAdapter;
    private List<Contact> contactList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        HandymanApp.getComponent().inject(this);
        baseViewModel = new ViewModelProvider(this, viewModelFactory).get(MessageViewModel.class);
        viewBinding = FragmentMessagesChildContactsBinding.inflate(inflater, container, false);
        return viewBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        createContactRecyclerView(view);

        String authorizationCode = sharedPreferencesUtils.get("token", String.class);
        String type = sharedPreferencesUtils.get("type", String.class);

        baseViewModel.fetchAllContactOfAccount(authorizationCode, type);
        baseViewModel.getContactListMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {
                contactList.clear();
                contactList.addAll(contacts);
                contactAdapter.notifyItemRangeInserted(1, contactList.size());
            }
        });
    }

    private void createContactRecyclerView(@NonNull View view) {
        RecyclerView rcvContact = viewBinding.recycleViewContacts;
        contactAdapter = new ContactAdapter(contactList, getContext());

        RecyclerView.LayoutManager layoutManagerMessage = new LinearLayoutManager(getContext());
        rcvContact.setLayoutManager(layoutManagerMessage);
        rcvContact.setItemAnimator(new FadeInLeftAnimator());

        ContactDivider contactDivider = new ContactDivider(getResources().getDrawable(R.drawable.recycler_view_divider));
        rcvContact.addItemDecoration(contactDivider);

        rcvContact.setAdapter(contactAdapter);
    }
}
