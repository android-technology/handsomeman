package com.tt.handsomeman.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.tt.handsomeman.service.MessageService;

import javax.inject.Inject;

public class MessageViewModel extends BaseViewModel {

    private MessageService messageService;

    @Inject
    MessageViewModel(@NonNull Application application, MessageService messageService) {
        super(application);
        this.messageService = messageService;
    }
}
