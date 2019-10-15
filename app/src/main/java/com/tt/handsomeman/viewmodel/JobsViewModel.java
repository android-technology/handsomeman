package com.tt.handsomeman.viewmodel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.tt.handsomeman.model.Job;
import com.tt.handsomeman.model.JobDetail;
import com.tt.handsomeman.response.JobDetailProfile;
import com.tt.handsomeman.response.StartScreenData;
import com.tt.handsomeman.service.JobService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class JobsViewModel extends BaseViewModel {

    private MutableLiveData<StartScreenData> screenDataMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Job>> jobMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<JobDetail> jobDetailMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<JobDetailProfile> jobDetailProfileMutableLiveData = new MutableLiveData<>();

    private JobService jobService;

    @Inject
    JobsViewModel(@NonNull Application application, JobService jobService) {
        super(application);
        this.jobService = jobService;
    }

    public LiveData<StartScreenData> getStartScreenData() {
        return screenDataMutableLiveData;
    }

    public LiveData<List<Job>> getJobLiveData() {
        return jobMutableLiveData;
    }

    public LiveData<JobDetail> getJobDetailLiveData() {
        return jobDetailMutableLiveData;
    }

    public LiveData<JobDetailProfile> getJobDetailProfileLiveData() {
        return jobDetailProfileMutableLiveData;
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

    public void fetchJobsWishList(String authorization) {
        compositeDisposable
                .add(jobService.getJobWishList(authorization)
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((jobWishList) -> {
                                    jobMutableLiveData.setValue(jobWishList.body().getData().getJobs());
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

    public void fetchJobsByCategory(String authorization, Integer categoryId) {
        compositeDisposable
                .add(jobService.getJobByCategory(authorization, categoryId)
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((jobResponse) -> {
                                    jobMutableLiveData.setValue(jobResponse.body().getData().getJobs());
                                },
                                throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_LONG).show()));
    }

    public void fetchJobsByFilter(String authorization, Double lat, Double lng, Integer radius, Integer priceMin, Integer priceMax, String createTime) {
        compositeDisposable
                .add(jobService.getJobByFilter(authorization, lat, lng, radius, priceMin, priceMax, createTime)
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((jobResponse) -> {
                                    jobMutableLiveData.setValue(jobResponse.body().getData().getJobs());
                                },
                                throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_LONG).show()));
    }

    public void fetchJobDetail(String authorization, Integer jobId) {
        compositeDisposable.add(jobService.getJobDetail(authorization, jobId)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((jobResponse) -> {
                            jobDetailMutableLiveData.setValue(jobResponse.body().getData());
                        },
                        throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_LONG).show()));
    }

    public void fetchJobDetailProfile(String authorization, Integer customerId) {
        compositeDisposable.add(jobService.getJobDetailProfile(authorization, customerId)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((profileResponse) -> {
                            jobDetailProfileMutableLiveData.setValue(profileResponse.body().getData());
                        },
                        throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_LONG).show()));
    }
}