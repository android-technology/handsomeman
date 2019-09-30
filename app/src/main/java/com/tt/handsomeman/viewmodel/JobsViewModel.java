package com.tt.handsomeman.viewmodel;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.tt.handsomeman.model.Job;
import com.tt.handsomeman.response.StartScreenData;
import com.tt.handsomeman.service.JobService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class JobsViewModel extends AndroidViewModel {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MutableLiveData<StartScreenData> screenDataMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Job>> jobMutableLiveData = new MutableLiveData<>();

    private JobService jobService;

    @Inject
    JobsViewModel(@NonNull Application application, JobService jobService) {
        super(application);
        this.jobService = jobService;
    }

    public LiveData<StartScreenData> getStartScreenData() {
        return screenDataMutableLiveData;
    }

    public LiveData<List<Job>> geJobLiveData() {
        return jobMutableLiveData;
    }

    public void fetchData(String authorization, Double lat, Double lng, Double radius) {
        compositeDisposable
                .add(jobService.getStartScreen(authorization, lat, lng, radius)
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((startScreenResponseResponse) -> {
                                    screenDataMutableLiveData.setValue(startScreenResponseResponse.body().getData());
                                },
                                throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_LONG).show()));
    }

    public void fetchYourLocationData(String authorization, Double lat, Double lng, Double radius) {
        compositeDisposable
                .add(jobService.getJobNearBy(authorization, lat, lng, radius)
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((jobResponse) -> {
                                    jobMutableLiveData.setValue(jobResponse.body().getData().getJobs());
                                },
                                throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_LONG).show()));
    }

    public void clearSubscriptions() {
        super.onCleared();
        compositeDisposable.clear();
        Log.d("AAA", "Disposed");
    }
}