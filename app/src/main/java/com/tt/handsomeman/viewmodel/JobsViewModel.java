package com.tt.handsomeman.viewmodel;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.tt.handsomeman.response.StartScreenData;
import com.tt.handsomeman.service.StartScreenService;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class JobsViewModel extends AndroidViewModel {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MutableLiveData<StartScreenData> screenDataMutableLiveData = new MutableLiveData<>();
    private StartScreenService startScreenService;

    @Inject
    JobsViewModel(@NonNull Application application, StartScreenService startScreenService) {
        super(application);
        this.startScreenService = startScreenService;
    }

    public LiveData<StartScreenData> getStartScreenData() {
        return screenDataMutableLiveData;
    }

    public void fetchData(String authorization, Double lat, Double lng, Double radius) {
        compositeDisposable
                .add(startScreenService.getStartScreen(authorization, lat, lng, radius)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((startScreenResponseResponse) -> {
                                    assert startScreenResponseResponse.body() != null;
                                    screenDataMutableLiveData.setValue(startScreenResponseResponse.body().getData());
                                },
                                throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_LONG).show()));
    }

    public void clearSubscriptions() {
        super.onCleared();
        compositeDisposable.clear();
        Log.d("AAA", "Disposed");
    }
}