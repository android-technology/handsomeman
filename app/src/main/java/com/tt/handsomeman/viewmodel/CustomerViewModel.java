package com.tt.handsomeman.viewmodel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.tt.handsomeman.model.Customer;
import com.tt.handsomeman.request.AddJobRequest;
import com.tt.handsomeman.request.HandymanDetailRequest;
import com.tt.handsomeman.request.MadeTheTransactionRequest;
import com.tt.handsomeman.request.NearbyHandymanRequest;
import com.tt.handsomeman.response.CustomerJobDetail;
import com.tt.handsomeman.response.CustomerProfileResponse;
import com.tt.handsomeman.response.CustomerReviewProfile;
import com.tt.handsomeman.response.DataBracketResponse;
import com.tt.handsomeman.response.HandymanDetailResponse;
import com.tt.handsomeman.response.ListCategory;
import com.tt.handsomeman.response.ListCustomerTransfer;
import com.tt.handsomeman.response.MyProjectList;
import com.tt.handsomeman.response.NearbyHandymanResponse;
import com.tt.handsomeman.response.StandardResponse;
import com.tt.handsomeman.response.StartScreenCustomer;
import com.tt.handsomeman.response.ViewMadeTransactionResponse;
import com.tt.handsomeman.service.CustomerService;
import com.tt.handsomeman.util.Constants;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CustomerViewModel extends BaseViewModel {

    private final CustomerService customerService;
    private MutableLiveData<StartScreenCustomer> startScreenCustomerMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<NearbyHandymanResponse> nearbyHandymanResponseMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<CustomerReviewProfile> customerReviewProfileMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<HandymanDetailResponse> handymanDetailResponseMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Customer> customerMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ListCategory> listCategoryMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<MyProjectList> myProjectListMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<CustomerProfileResponse> customerProfileResponseMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<StandardResponse> standardResponseMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<CustomerJobDetail> customerJobDetailMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<DataBracketResponse<ViewMadeTransactionResponse>> viewTransactionLiveData = new MutableLiveData<>();
    private MutableLiveData<DataBracketResponse<ListCustomerTransfer>> listTransferHistoryLiveData = new MutableLiveData<>();
    private String locale = Constants.language.getValue();

    @Inject
    CustomerViewModel(@NonNull Application application,
                      CustomerService customerService) {
        super(application);
        this.customerService = customerService;
    }

    public MutableLiveData<StartScreenCustomer> getStartScreenCustomerMutableLiveData() {
        return startScreenCustomerMutableLiveData;
    }

    public MutableLiveData<NearbyHandymanResponse> getNearbyHandymanResponseMutableLiveData() {
        return nearbyHandymanResponseMutableLiveData;
    }

    public MutableLiveData<ListCategory> getListCategoryMutableLiveData() {
        return listCategoryMutableLiveData;
    }

    public MutableLiveData<CustomerReviewProfile> getCustomerReviewProfileMutableLiveData() {
        return customerReviewProfileMutableLiveData;
    }

    public MutableLiveData<HandymanDetailResponse> getHandymanDetailResponseMutableLiveData() {
        return handymanDetailResponseMutableLiveData;
    }

    public MutableLiveData<MyProjectList> getMyProjectListMutableLiveData() {
        return myProjectListMutableLiveData;
    }

    public MutableLiveData<Customer> getCustomerMutableLiveData() {
        return customerMutableLiveData;
    }

    public MutableLiveData<CustomerProfileResponse> getCustomerProfileResponseMutableLiveData() {
        return customerProfileResponseMutableLiveData;
    }

    public MutableLiveData<DataBracketResponse<ListCustomerTransfer>> getListTransferHistoryLiveData() {
        return listTransferHistoryLiveData;
    }

    public MutableLiveData<StandardResponse> getStandardResponseMutableLiveData() {
        return standardResponseMutableLiveData;
    }

    public MutableLiveData<CustomerJobDetail> getCustomerJobDetailMutableLiveData() {
        return customerJobDetailMutableLiveData;
    }

    public MutableLiveData<DataBracketResponse<ViewMadeTransactionResponse>> getViewTransactionLiveData() {
        return viewTransactionLiveData;
    }

    public void fetchDataStartScreen(String authorization,
                                     NearbyHandymanRequest nearbyHandymanRequest) {
        compositeDisposable
                .add(customerService.getStartScreen(locale, authorization, nearbyHandymanRequest)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((startScreenResponseResponse) -> {
                                    startScreenCustomerMutableLiveData.setValue(startScreenResponseResponse.body().getData());
                                },
                                throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_LONG).show()));
    }

    public void fetHandymanByCategory(String authorization,
                                      Integer categoryId,
                                      NearbyHandymanRequest nearbyHandymanRequest) {
        compositeDisposable
                .add(customerService.getHandymanByCateGory(locale, authorization, categoryId, nearbyHandymanRequest)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((nearbyHandymanResponseResponse) -> {
                                    nearbyHandymanResponseMutableLiveData.setValue(nearbyHandymanResponseResponse.body().getData());
                                },
                                throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_LONG).show()));
    }

    public void fetHandymanNearby(String authorization,
                                  NearbyHandymanRequest nearbyHandymanRequest) {
        compositeDisposable
                .add(customerService.getHandymanNearby(locale, authorization, nearbyHandymanRequest)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((nearbyHandymanResponseResponse) -> {
                                    nearbyHandymanResponseMutableLiveData.setValue(nearbyHandymanResponseResponse.body().getData());
                                },
                                throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_LONG).show()));
    }

    public void fetchCustomerReview(String authorization) {
        compositeDisposable
                .add(customerService.getCustomerReview(locale, authorization)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((nearbyHandymanResponseResponse) -> {
                                    customerReviewProfileMutableLiveData.setValue(nearbyHandymanResponseResponse.body().getData());
                                },
                                throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_LONG).show()));
    }

    public void fetchHandymanDetail(String token,
                                    HandymanDetailRequest handymanDetailRequest) {
        compositeDisposable
                .add(customerService.getHandymanDetail(locale, token, handymanDetailRequest)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((nearbyHandymanResponseResponse) -> {
                                    handymanDetailResponseMutableLiveData.setValue(nearbyHandymanResponseResponse.body().getData());
                                },
                                throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_LONG).show()));
    }

    public void fetchCustomerInfo(String authorization) {
        compositeDisposable
                .add(customerService.getCustomerInfo(locale, authorization)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((nearbyHandymanResponseResponse) -> {
                                    customerMutableLiveData.setValue(nearbyHandymanResponseResponse.body().getData());
                                },
                                throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_LONG).show()));
    }

    public void fetchCustomerProfile(String authorization) {
        compositeDisposable
                .add(customerService.getCustomerProfile(locale, authorization)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((nearbyHandymanResponseResponse) -> {
                                    customerProfileResponseMutableLiveData.setValue(nearbyHandymanResponseResponse.body().getData());
                                },
                                throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_LONG).show()));
    }

    public void editCustomerProfile(String authorization,
                                    String customerEditName) {
        compositeDisposable
                .add(customerService.editCustomerProfile(locale, authorization, customerEditName)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((nearbyHandymanResponseResponse) -> {
                                    standardResponseMutableLiveData.setValue(nearbyHandymanResponseResponse.body());
                                },
                                throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_LONG).show()));
    }

    public void fetchCustomerJobDetail(String authorization,
                                       Integer jobId) {
        compositeDisposable.add(customerService.getCustomerJobDetail(locale, authorization, jobId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((jobResponse) -> {
                            customerJobDetailMutableLiveData.setValue(jobResponse.body().getData());
                        },
                        throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_LONG).show()));
    }

    public void fetchJobsOfCustomer(String authorization) {
        compositeDisposable.add(customerService.getJobsOfCustomer(locale, authorization)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((myProjectListResponse) -> {
                            myProjectListMutableLiveData.setValue(myProjectListResponse.body().getData());
                        },
                        throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_LONG).show()));
    }

    public void addNewJob(String authorization,
                          AddJobRequest addJobRequest) {
        compositeDisposable.add(customerService.addNewJob(locale, authorization, addJobRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(standardResponseResponse -> {
                            standardResponseMutableLiveData.setValue(standardResponseResponse.body());
                        },
                        throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_LONG).show()));
    }

    public void fetchListCategory(String authorization) {
        compositeDisposable.add(customerService.getListCategory(locale, authorization)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((dataBracketResponseResponse) -> {
                            listCategoryMutableLiveData.setValue(dataBracketResponseResponse.body().getData());
                        },
                        throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_SHORT).show()));
    }

    public void viewMakeTransaction(String authorization) {
        compositeDisposable.add(customerService.viewMakeTransaction(locale, authorization)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataBracketResponseResponse -> {
                    viewTransactionLiveData.setValue(dataBracketResponseResponse.body());
                }, throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_SHORT).show()));
    }

    public void makeTheTransaction(String authorization,
                                   MadeTheTransactionRequest madeTheTransactionRequest) {
        compositeDisposable.add(customerService.makeTheTransaction(locale, authorization, madeTheTransactionRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(standardResponseResponse -> {
                    standardResponseMutableLiveData.setValue(standardResponseResponse.body());
                }, throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_SHORT).show()));
    }

    public void fetchTransferHistory(String authorization) {
        compositeDisposable.add(customerService.viewTransferHistory(authorization)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataBracketResponseResponse -> {
                    listTransferHistoryLiveData.setValue(dataBracketResponseResponse.body());
                }, throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_SHORT).show()));
    }
}
