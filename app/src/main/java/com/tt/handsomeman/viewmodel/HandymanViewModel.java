package com.tt.handsomeman.viewmodel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.tt.handsomeman.model.Handyman;
import com.tt.handsomeman.model.HandymanJobDetail;
import com.tt.handsomeman.model.Job;
import com.tt.handsomeman.request.HandymanEditRequest;
import com.tt.handsomeman.request.HandymanTransferRequest;
import com.tt.handsomeman.request.JobFilterRequest;
import com.tt.handsomeman.request.NearbyJobRequest;
import com.tt.handsomeman.response.DataBracketResponse;
import com.tt.handsomeman.response.HandymanProfileResponse;
import com.tt.handsomeman.response.HandymanReviewProfile;
import com.tt.handsomeman.response.JobDetailProfile;
import com.tt.handsomeman.response.ListCategory;
import com.tt.handsomeman.response.ListPayoutResponse;
import com.tt.handsomeman.response.ListTransferHistory;
import com.tt.handsomeman.response.MyProjectList;
import com.tt.handsomeman.response.StandardResponse;
import com.tt.handsomeman.response.StartScreenHandyman;
import com.tt.handsomeman.response.TransactionDetailResponse;
import com.tt.handsomeman.service.HandymanService;
import com.tt.handsomeman.util.Constants;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class HandymanViewModel extends BaseViewModel {

    private final HandymanService handymanService;
    private MutableLiveData<Handyman> handymanMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<HandymanReviewProfile> handymanReviewProfileMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<HandymanProfileResponse> handymanProfileResponseMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ListCategory> listCategoryMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ListTransferHistory> listTransferHistoryMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ListPayoutResponse> listPayoutResponseMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<StandardResponse> standardResponseMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<MyProjectList> myProjectListMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<StartScreenHandyman> screenDataMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Job>> jobMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<HandymanJobDetail> jobDetailMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<JobDetailProfile> jobDetailProfileMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<String> messageResponse = new MutableLiveData<>();
    private MutableLiveData<StandardResponse> standardResponseMarkReadMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<DataBracketResponse<TransactionDetailResponse>> jobTransactionLiveData = new MutableLiveData<>();
    private String locale = Constants.language.getValue();

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

    public MutableLiveData<MyProjectList> getMyProjectListMutableLiveData() {
        return myProjectListMutableLiveData;
    }

    public MutableLiveData<StandardResponse> getStandardResponseMutableLiveData() {
        return standardResponseMutableLiveData;
    }

    public LiveData<StartScreenHandyman> getStartScreenData() {
        return screenDataMutableLiveData;
    }

    public LiveData<List<Job>> getJobLiveData() {
        return jobMutableLiveData;
    }

    public LiveData<HandymanJobDetail> getJobDetailLiveData() {
        return jobDetailMutableLiveData;
    }

    public LiveData<JobDetailProfile> getJobDetailProfileLiveData() {
        return jobDetailProfileMutableLiveData;
    }

    public MutableLiveData<StandardResponse> getStandardResponseMarkReadMutableLiveData() {
        return standardResponseMarkReadMutableLiveData;
    }

    public MutableLiveData<DataBracketResponse<TransactionDetailResponse>> getJobTransactionLiveData() {
        return jobTransactionLiveData;
    }

    public MutableLiveData<String> getMessageResponse() {
        return messageResponse;
    }

    public void fetchHandymanInfo(String authorization) {
        compositeDisposable.add(handymanService.getHandymanInfo(locale, authorization)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((dataBracketResponseResponse) -> {
                            handymanMutableLiveData.setValue(dataBracketResponseResponse.body().getData());
                        },
                        throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_SHORT).show()));
    }

    public void fetchHandymanReview(String authorization) {
        compositeDisposable.add(handymanService.getHandymanReview(locale, authorization)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((response) -> {
                    handymanReviewProfileMutableLiveData.setValue(response.body().getData());
                }, throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_SHORT).show()));
    }

    public void fetchHandymanProfile(String authorization) {
        compositeDisposable.add(handymanService.getHandymanProfile(locale, authorization)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((dataBracketResponseResponse) -> {
                            handymanProfileResponseMutableLiveData.setValue(dataBracketResponseResponse.body().getData());
                        },
                        throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_SHORT).show()));
    }

    public void editHandymanProfile(String authorization, HandymanEditRequest handymanEditRequest) {
        compositeDisposable.add(handymanService.editHandymanProfile(locale, authorization, handymanEditRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataBracketResponseResponse -> {
                    standardResponseMutableLiveData.setValue(dataBracketResponseResponse.body());
                }, throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_SHORT).show()));
    }

    public void fetchListCategory(String authorization) {
        compositeDisposable.add(handymanService.getListCategory(locale, authorization)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((dataBracketResponseResponse) -> {
                            listCategoryMutableLiveData.setValue(dataBracketResponseResponse.body().getData());
                        },
                        throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_SHORT).show()));
    }

    public void viewTransferHistory(String authorization) {
        compositeDisposable.add(handymanService.viewTransferHistory(locale, authorization)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((dataBracketResponseResponse) -> {
                            listTransferHistoryMutableLiveData.setValue(dataBracketResponseResponse.body().getData());
                        },
                        throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_SHORT).show()));
    }

    public void getListPayoutOfHandyman(String authorization) {
        compositeDisposable.add(handymanService.getListPayoutOfHandyman(locale, authorization)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((dataBracketResponseResponse) -> {
                            listPayoutResponseMutableLiveData.setValue(dataBracketResponseResponse.body().getData());
                        },
                        throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_SHORT).show()));
    }

    public void transferToBankAccount(String authorization, HandymanTransferRequest handymanTransferRequest) {
        compositeDisposable.add(handymanService.transferToBank(locale, authorization, handymanTransferRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataBracketResponseResponse -> {
                    standardResponseMutableLiveData.setValue(dataBracketResponseResponse.body());
                }, throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_SHORT).show()));
    }

    public void fetchJobsOfHandyman(String authorization) {
        compositeDisposable.add(handymanService.getJobsOfHandyman(locale, authorization)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((myProjectListResponse) -> {
                            myProjectListMutableLiveData.setValue(myProjectListResponse.body().getData());
                        },
                        throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_LONG).show()));
    }

    public void fetchDataStartScreen(String authorization, NearbyJobRequest nearbyJobRequest) {
        compositeDisposable
                .add(handymanService.getStartScreen(locale, authorization, nearbyJobRequest)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((startScreenResponseResponse) -> {
                                    screenDataMutableLiveData.setValue(startScreenResponseResponse.body().getData());
                                },
                                throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_LONG).show()));
    }

    public void fetchJobsWishList(String authorization) {
        compositeDisposable
                .add(handymanService.getJobWishList(locale, authorization)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((jobWishList) -> {
                                    jobMutableLiveData.setValue(jobWishList.body().getData().getJobs());
                                },
                                throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_LONG).show()));
    }


    public void fetchYourLocationData(String authorization, NearbyJobRequest nearbyJobRequest) {
        compositeDisposable
                .add(handymanService.getJobNearBy(locale, authorization, nearbyJobRequest)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((jobResponse) -> {
                                    jobMutableLiveData.setValue(jobResponse.body().getData().getJobs());
                                },
                                throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_LONG).show()));
    }

    public void fetchJobsByCategory(String authorization, Integer categoryId, NearbyJobRequest nearbyJobRequest) {
        compositeDisposable
                .add(handymanService.getJobByCategory(locale, authorization, categoryId, nearbyJobRequest)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((jobResponse) -> {
                                    jobMutableLiveData.setValue(jobResponse.body().getData().getJobs());
                                },
                                throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_LONG).show()));
    }

    public void fetchJobsByFilter(String authorization, JobFilterRequest jobFilterRequest) {
        compositeDisposable
                .add(handymanService.getJobByFilter(locale, authorization, jobFilterRequest)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((jobResponse) -> {
                                    jobMutableLiveData.setValue(jobResponse.body().getData().getJobs());
                                },
                                throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_LONG).show()));
    }

    public void fetchHandymanJobDetail(String authorization, Integer jobId) {
        compositeDisposable.add(handymanService.getHandymanJobDetail(locale, authorization, jobId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((jobResponse) -> {
                            jobDetailMutableLiveData.setValue(jobResponse.body().getData());
                        },
                        throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_LONG).show()));
    }

    public void fetchJobDetailProfile(String authorization, Integer customerId) {
        compositeDisposable.add(handymanService.getJobDetailProfile(locale, authorization, customerId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((profileResponse) -> {
                            jobDetailProfileMutableLiveData.setValue(profileResponse.body().getData());
                        },
                        throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_LONG).show()));
    }


    public void addJobBid(String authorization, RequestBody bid, RequestBody description, List<MultipartBody.Part> files, RequestBody jobId, RequestBody serviceFee, RequestBody bidTime, List<RequestBody> md5List) {
        compositeDisposable.add(handymanService.addJobBid(locale, authorization, bid, description, files, jobId, serviceFee, bidTime, md5List)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((response) -> {
                    standardResponseMutableLiveData.setValue(response.body());
                }, throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_SHORT).show()));
    }

    public void fetchJobTransactionDetail(String authorization, Integer jobId) {
        compositeDisposable.add(handymanService.viewPaymentTransaction(locale, authorization, jobId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataBracketResponseResponse -> {
                    jobTransactionLiveData.setValue(dataBracketResponseResponse.body());
                }, throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_SHORT).show()));
    }

    public void markNotificationAsRead(String authorization, Integer notificationId) {
        compositeDisposable.add(handymanService.markNotificationRead(authorization, notificationId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                            standardResponseMarkReadMutableLiveData.setValue(response.body());
                        }, throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_SHORT).show()
                ));
    }
}
