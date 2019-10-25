package com.tt.handsomeman.viewmodel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.tt.handsomeman.response.ConversationResponse;
import com.tt.handsomeman.service.MessageService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MessageViewModel extends BaseViewModel {
    private MutableLiveData<List<ConversationResponse>> conversationResponseListMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<String> messageResponse = new MutableLiveData<>();
    private MessageService messageService;

    @Inject
    MessageViewModel(@NonNull Application application, MessageService messageService) {
        super(application);
        this.messageService = messageService;
    }

    public MutableLiveData<List<ConversationResponse>> getConversationResponseMutableLiveData() {
        return conversationResponseListMutableLiveData;
    }

    public MutableLiveData<String> getMessageResponse() {
        return messageResponse;
    }

    public void fetchAllConversationByAccountId(String authorization) {
        compositeDisposable.add(messageService.getAllConversationByAccountId(authorization)
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
}
