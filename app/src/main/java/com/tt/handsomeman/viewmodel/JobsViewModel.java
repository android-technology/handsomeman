package com.tt.handsomeman.viewmodel;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.tt.handsomeman.model.Category;
import com.tt.handsomeman.model.Job;
import com.tt.handsomeman.response.StartScreenData;
import com.tt.handsomeman.service.StartScreenService;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class JobsViewModel extends AndroidViewModel {

    private MutableLiveData<List<Job>> listJob = new MutableLiveData<>();
    private MutableLiveData<List<Category>> listCategory = new MutableLiveData<>();

    private MutableLiveData<StartScreenData> screenDataMutableLiveData = new MutableLiveData<>();

    public LiveData<StartScreenData> getStartScreenData() {
        return screenDataMutableLiveData;
    }

    private CompositeDisposable compositeDisposable;

    private StartScreenService startScreenService;

    public JobsViewModel(@NonNull Application application, StartScreenService startScreenService) {
        super(application);
        compositeDisposable = new CompositeDisposable();
        this.startScreenService = startScreenService;
    }

    public void getListJob(String authorization, Double lat, Double lng, Double radius) {
        compositeDisposable
                .add(startScreenService.getStartScreen(authorization, lat, lng, radius)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                (startScreenResponseResponse) -> {
                                    assert startScreenResponseResponse.body() != null;

                                    listJob.setValue(startScreenResponseResponse.body().getData().getJobList());
                                    listCategory.setValue(startScreenResponseResponse.body().getData().getCategoryList());

                                    StartScreenData startScreenData = new StartScreenData(listJob.getValue(), listCategory.getValue());
                                    screenDataMutableLiveData.setValue(startScreenData);
                                },
                                throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_LONG).show()));
    }

    public void clearSubscriptions() {
        super.onCleared();
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
        Log.d("AAA", "Disposed");
    }
}