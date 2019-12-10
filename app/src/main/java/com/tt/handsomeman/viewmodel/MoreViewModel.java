package com.tt.handsomeman.viewmodel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.tt.handsomeman.model.Handyman;
import com.tt.handsomeman.service.HandymanService;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MoreViewModel extends BaseViewModel {
    private MutableLiveData<Handyman> handymanMutableLiveData = new MutableLiveData<>();
    private HandymanService handymanService;

    @Inject
    MoreViewModel(@NonNull Application application, HandymanService handymanService) {
        super(application);
        this.handymanService = handymanService;
    }

    public MutableLiveData<Handyman> getHandymanMutableLiveData() {
        return handymanMutableLiveData;
    }

    public void getHandymanInfo(String authorization) {
        compositeDisposable
                .add(handymanService.getHandymanInfo(authorization)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((dataBracketResponseResponse) -> {
                                    handymanMutableLiveData.setValue(dataBracketResponseResponse.body().getData());
                                },
                                throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_LONG).show()));
    }

}
