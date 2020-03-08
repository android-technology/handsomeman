package com.tt.handsomeman.viewmodel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.tt.handsomeman.request.SendMessageRequest;
import com.tt.handsomeman.response.Contact;
import com.tt.handsomeman.response.ConversationResponse;
import com.tt.handsomeman.response.MessageResponse;
import com.tt.handsomeman.response.StandardResponse;
import com.tt.handsomeman.service.MessageService;
import com.tt.handsomeman.util.StatusConstant;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MessageViewModel extends BaseViewModel {
    private final MessageService messageService;
    private MutableLiveData<List<ConversationResponse>> conversationResponseListMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<MessageResponse>> messageResponseListMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Contact>> contactListMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<String> messageResponse = new MutableLiveData<>();
    private MutableLiveData<StandardResponse> standardResponseMutableLiveData = new MutableLiveData<>();

    @Inject
    MessageViewModel(@NonNull Application application, MessageService messageService) {
        super(application);
        this.messageService = messageService;
    }

    public MutableLiveData<List<ConversationResponse>> getConversationResponseMutableLiveData() {
        return conversationResponseListMutableLiveData;
    }

    public MutableLiveData<List<MessageResponse>> getMessageResponseListMutableLiveData() {
        return messageResponseListMutableLiveData;
    }

    public MutableLiveData<List<Contact>> getContactListMutableLiveData() {
        return contactListMutableLiveData;
    }

    public MutableLiveData<StandardResponse> getStandardResponseMutableLiveData() {
        return standardResponseMutableLiveData;
    }

    public void clearStandardResponseLiveDate() {
        standardResponseMutableLiveData.setValue(null);
    }

    public MutableLiveData<String> getMessageResponse() {
        return messageResponse;
    }

    public void fetchAllConversationByAccountId(String authorization, String type) {
        compositeDisposable.add(messageService.getAllConversationByAccountId(authorization, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                            List<ConversationResponse> conversationResponseList = response.body().getData().getConversationList();
                            if (conversationResponseList.isEmpty()) {
                                messageResponse.setValue(response.body().getMessage());
                                conversationResponseListMutableLiveData.setValue(conversationResponseList);
                            } else {
                                conversationResponseListMutableLiveData.setValue(conversationResponseList);
                            }
                        }, throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_SHORT).show()
                ));
    }

    public void fetchAllMessageInConversation(String authorization, Integer conversationId) {
        compositeDisposable.add(messageService.getAllMessagesInConversation(authorization, conversationId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                            List<MessageResponse> messageResponseList = response.body().getData().getMessageResponseList();
                            if (messageResponseList.isEmpty()) {
                                messageResponse.setValue(response.body().getMessage());
                                messageResponseListMutableLiveData.setValue(messageResponseList);
                            } else {
                                messageResponseListMutableLiveData.setValue(messageResponseList);
                            }
                        }, throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_SHORT).show()
                ));
    }

    public void deleteConversationById(String authorization, Integer conversationId) {
        compositeDisposable.add(messageService.deleteConversationById(authorization, conversationId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                            if (response.body().getStatus().equals(StatusConstant.OK)) {
                                messageResponse.setValue(response.body().getMessage());
                            } else {
                                messageResponse.setValue(response.body().getMessage());
                            }
                        }, throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_SHORT).show()
                ));
    }

    public void fetchAllContactOfAccount(String authorization, String type) {
        compositeDisposable.add(messageService.getContactOfAccount(authorization, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                            List<Contact> contactList = response.body().getData().getContactList();
                            if (contactList.isEmpty()) {
                                messageResponse.setValue(response.body().getMessage());
                                contactListMutableLiveData.setValue(contactList);
                            } else {
                                contactListMutableLiveData.setValue(contactList);
                            }
                        }, throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_SHORT).show()
                ));
    }

    public void sendMessageToConversation(String authorization, SendMessageRequest sendMessageRequest) {
        compositeDisposable.add(messageService.sendMessageToConversation(authorization, sendMessageRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                            standardResponseMutableLiveData.setValue(response.body());
                        }, throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_SHORT).show()
                ));
    }
}
