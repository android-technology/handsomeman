package com.tt.handsomeman.viewmodel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.tt.handsomeman.request.NearbyHandymanRequest;
import com.tt.handsomeman.request.NearbyJobRequest;
import com.tt.handsomeman.response.StartScreenCustomer;
import com.tt.handsomeman.service.CustomerService;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CustomerViewModel extends BaseViewModel {

    private MutableLiveData<StartScreenCustomer> startScreenCustomerMutableLiveData = new MutableLiveData<>();
    private final CustomerService customerService;

    @Inject
    CustomerViewModel(@NonNull Application application, CustomerService customerService) {
        super(application);
        this.customerService = customerService;
    }

    public MutableLiveData<StartScreenCustomer> getStartScreenCustomerMutableLiveData() {
        return startScreenCustomerMutableLiveData;
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
}
