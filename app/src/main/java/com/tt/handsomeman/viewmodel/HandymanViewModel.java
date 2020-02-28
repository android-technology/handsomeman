package com.tt.handsomeman.viewmodel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.tt.handsomeman.model.Handyman;
import com.tt.handsomeman.request.HandymanEditRequest;
import com.tt.handsomeman.request.HandymanTransferRequest;
import com.tt.handsomeman.response.HandymanProfileResponse;
import com.tt.handsomeman.response.HandymanReviewProfile;
import com.tt.handsomeman.response.ListCategory;
import com.tt.handsomeman.response.ListPayoutResponse;
import com.tt.handsomeman.response.ListTransferHistory;
import com.tt.handsomeman.response.StandardResponse;
import com.tt.handsomeman.service.HandymanService;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HandymanViewModel extends BaseViewModel {
    private MutableLiveData<Handyman> handymanMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<HandymanReviewProfile> handymanReviewProfileMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<HandymanProfileResponse> handymanProfileResponseMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ListCategory> listCategoryMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ListTransferHistory> listTransferHistoryMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ListPayoutResponse> listPayoutResponseMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<StandardResponse> standardResponseMutableLiveData = new MutableLiveData<>();
    private HandymanService handymanService;

    @Inject
    HandymanViewModel(@NonNull Application application, HandymanService handymanService) {
        super(application);
        this.handymanService = handymanService;
    }

    public MutableLiveData<Handyman> getHandymanMutableLiveData() {
        return handymanMutableLiveData;
    }

    public MutableLiveData<HandymanReviewProfile> getHandymanReviewProfileLiveData() {
        return handymanReviewProfileMutableLiveData;
    }

    public MutableLiveData<HandymanProfileResponse> getHandymanProfileResponseMutableLiveData() {
        return handymanProfileResponseMutableLiveData;
    }

    public MutableLiveData<ListCategory> getListCategoryMutableLiveData() {
        return listCategoryMutableLiveData;
    }

    public MutableLiveData<ListPayoutResponse> getListPayoutResponseMutableLiveData() {
        return listPayoutResponseMutableLiveData;
    }

    public MutableLiveData<ListTransferHistory> getListTransferHistoryMutableLiveData() {
        return listTransferHistoryMutableLiveData;
    }

    public MutableLiveData<StandardResponse> getStandardResponseMutableLiveData() {
        return standardResponseMutableLiveData;
    }

    public void fetchHandymanInfo(String authorization) {
        compositeDisposable.add(handymanService.getHandymanInfo(authorization)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((dataBracketResponseResponse) -> {
                            handymanMutableLiveData.setValue(dataBracketResponseResponse.body().getData());
                        },
                        throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_SHORT).show()));
    }

    public void fetchHandymanReview(String authorization) {
        compositeDisposable.add(handymanService.getHandymanReview(authorization)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((response) -> {
                    handymanReviewProfileMutableLiveData.setValue(response.body().getData());
                }, throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_SHORT).show()));
    }

    public void fetchHandymanProfile(String authorization) {
        compositeDisposable.add(handymanService.getHandymanProfile(authorization)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((dataBracketResponseResponse) -> {
                            handymanProfileResponseMutableLiveData.setValue(dataBracketResponseResponse.body().getData());
                        },
                        throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_SHORT).show()));
    }

    public void editHandymanProfile(String authorization, HandymanEditRequest handymanEditRequest) {
        compositeDisposable.add(handymanService.editHandymanProfile(authorization, handymanEditRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataBracketResponseResponse -> {
                    standardResponseMutableLiveData.setValue(dataBracketResponseResponse.body());
                }, throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_SHORT).show()));
    }

    public void fetchListCategory(String authorization) {
        compositeDisposable.add(handymanService.getListCategory(authorization)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((dataBracketResponseResponse) -> {
                            listCategoryMutableLiveData.setValue(dataBracketResponseResponse.body().getData());
                        },
                        throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_SHORT).show()));
    }

    public void viewTransferHistory(String authorization) {
        compositeDisposable.add(handymanService.viewTransferHistory(authorization)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((dataBracketResponseResponse) -> {
                            listTransferHistoryMutableLiveData.setValue(dataBracketResponseResponse.body().getData());
                        },
                        throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_SHORT).show()));
    }

    public void getListPayoutOfHandyman(String authorization) {
        compositeDisposable.add(handymanService.getListPayoutOfHandyman(authorization)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((dataBracketResponseResponse) -> {
                            listPayoutResponseMutableLiveData.setValue(dataBracketResponseResponse.body().getData());
                        },
                        throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_SHORT).show()));
    }

    public void transferToBankAccount(String authorization, HandymanTransferRequest handymanTransferRequest) {
        compositeDisposable.add(handymanService.transferToBank(authorization, handymanTransferRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataBracketResponseResponse -> {
                    standardResponseMutableLiveData.setValue(dataBracketResponseResponse.body());
                }, throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_SHORT).show()));
    }
}
