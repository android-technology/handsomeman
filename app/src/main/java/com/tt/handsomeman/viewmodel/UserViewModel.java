package com.tt.handsomeman.viewmodel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.tt.handsomeman.request.ChangePasswordRequest;
import com.tt.handsomeman.response.StandardResponse;
import com.tt.handsomeman.service.UserService;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UserViewModel extends BaseViewModel {

    private MutableLiveData<StandardResponse> standardResponseMutableLiveData = new MutableLiveData<>();
    private UserService userService;

    @Inject
    UserViewModel(@NonNull Application application, UserService userService) {
        super(application);
        this.userService = userService;
    }

    public MutableLiveData<StandardResponse> getStandardResponseMutableLiveData() {
        return standardResponseMutableLiveData;
    }

    public void removePayout(String authorization, Integer payoutId) {
        compositeDisposable.add(userService.removePayout(authorization, payoutId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                            standardResponseMutableLiveData.setValue(response.body());
                        }, throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_SHORT).show()
                ));
    }

    public void changePassword(String authorization, ChangePasswordRequest changePasswordRequest) {
        compositeDisposable.add(userService.changePassword(authorization, changePasswordRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                            standardResponseMutableLiveData.setValue(response.body());
                        }, throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_SHORT).show()
                ));
    }
}
