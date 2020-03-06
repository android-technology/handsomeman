package com.tt.handsomeman.viewmodel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.tt.handsomeman.request.NearbyHandymanRequest;
import com.tt.handsomeman.response.NearbyHandymanResponse;
import com.tt.handsomeman.response.StartScreenCustomer;
import com.tt.handsomeman.service.CustomerService;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CustomerViewModel extends BaseViewModel {

    private final CustomerService customerService;
    private MutableLiveData<StartScreenCustomer> startScreenCustomerMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<NearbyHandymanResponse> nearbyHandymanResponseMutableLiveData = new MutableLiveData<>();

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

    public void fetchDataStartScreen(String authorization, NearbyHandymanRequest nearbyHandymanRequest) {
        compositeDisposable
                .add(customerService.getStartScreen(authorization, nearbyHandymanRequest)
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((startScreenResponseResponse) -> {
                                    startScreenCustomerMutableLiveData.setValue(startScreenResponseResponse.body().getData());
                                },
                                throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_LONG).show()));
    }

    public void fetHandymanByCategory(String authorization, Integer categoryId, NearbyHandymanRequest nearbyHandymanRequest) {
        compositeDisposable
                .add(customerService.getHandymanByCateGory(authorization, categoryId, nearbyHandymanRequest)
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((nearbyHandymanResponseResponse) -> {
                                    nearbyHandymanResponseMutableLiveData.setValue(nearbyHandymanResponseResponse.body().getData());
                                },
                                throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_LONG).show()));
    }

    public void fetHandymanNearby(String authorization, NearbyHandymanRequest nearbyHandymanRequest) {
        compositeDisposable
                .add(customerService.getHandymanNearby(authorization, nearbyHandymanRequest)
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((nearbyHandymanResponseResponse) -> {
                                    nearbyHandymanResponseMutableLiveData.setValue(nearbyHandymanResponseResponse.body().getData());
                                },
                                throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_LONG).show()));
    }
}
