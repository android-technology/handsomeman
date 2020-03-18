package com.tt.handsomeman.viewmodel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.tt.handsomeman.model.Customer;
import com.tt.handsomeman.request.HandymanDetailRequest;
import com.tt.handsomeman.request.NearbyHandymanRequest;
import com.tt.handsomeman.response.CustomerProfileResponse;
import com.tt.handsomeman.response.CustomerReviewProfile;
import com.tt.handsomeman.response.HandymanDetailResponse;
import com.tt.handsomeman.response.NearbyHandymanResponse;
import com.tt.handsomeman.response.StandardResponse;
import com.tt.handsomeman.response.StartScreenCustomer;
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
    private MutableLiveData<CustomerProfileResponse> customerProfileResponseMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<StandardResponse> standardResponseMutableLiveData = new MutableLiveData<>();
    private String locale = Constants.language.getValue();

    @Inject
    CustomerViewModel(@NonNull Application application, CustomerService customerService) {
        super(application);
        this.customerService = customerService;
    }

    public MutableLiveData<StartScreenCustomer> getStartScreenCustomerMutableLiveData() {
        return startScreenCustomerMutableLiveData;
    }

    public MutableLiveData<NearbyHandymanResponse> getNearbyHandymanResponseMutableLiveData() {
        return nearbyHandymanResponseMutableLiveData;
    }

    public MutableLiveData<CustomerReviewProfile> getCustomerReviewProfileMutableLiveData() {
        return customerReviewProfileMutableLiveData;
    }

    public MutableLiveData<HandymanDetailResponse> getHandymanDetailResponseMutableLiveData() {
        return handymanDetailResponseMutableLiveData;
    }

    public MutableLiveData<Customer> getCustomerMutableLiveData() {
        return customerMutableLiveData;
    }

    public MutableLiveData<CustomerProfileResponse> getCustomerProfileResponseMutableLiveData() {
        return customerProfileResponseMutableLiveData;
    }

    public MutableLiveData<StandardResponse> getStandardResponseMutableLiveData() {
        return standardResponseMutableLiveData;
    }

    public void fetchDataStartScreen(String authorization, NearbyHandymanRequest nearbyHandymanRequest) {
        compositeDisposable
                .add(customerService.getStartScreen(locale, authorization, nearbyHandymanRequest)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((startScreenResponseResponse) -> {
                                    startScreenCustomerMutableLiveData.setValue(startScreenResponseResponse.body().getData());
                                },
                                throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_LONG).show()));
    }

    public void fetHandymanByCategory(String authorization, Integer categoryId, NearbyHandymanRequest nearbyHandymanRequest) {
        compositeDisposable
                .add(customerService.getHandymanByCateGory(locale, authorization, categoryId, nearbyHandymanRequest)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((nearbyHandymanResponseResponse) -> {
                                    nearbyHandymanResponseMutableLiveData.setValue(nearbyHandymanResponseResponse.body().getData());
                                },
                                throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_LONG).show()));
    }

    public void fetHandymanNearby(String authorization, NearbyHandymanRequest nearbyHandymanRequest) {
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

    public void fetchHandymanDetail(String token, HandymanDetailRequest handymanDetailRequest) {
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

    public void editCustomerProfile(String authorization, String customerEditName) {
        compositeDisposable
                .add(customerService.editCustomerProfile(locale, authorization, customerEditName)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((nearbyHandymanResponseResponse) -> {
                                    standardResponseMutableLiveData.setValue(nearbyHandymanResponseResponse.body());
                                },
                                throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_LONG).show()));
    }
}
