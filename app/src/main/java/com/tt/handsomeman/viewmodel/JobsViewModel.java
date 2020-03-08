package com.tt.handsomeman.viewmodel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.tt.handsomeman.model.Job;
import com.tt.handsomeman.model.JobDetail;
import com.tt.handsomeman.request.JobFilterRequest;
import com.tt.handsomeman.request.NearbyJobRequest;
import com.tt.handsomeman.response.JobDetailProfile;
import com.tt.handsomeman.response.StartScreenHandyman;
import com.tt.handsomeman.service.JobService;
import com.tt.handsomeman.util.StatusConstant;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

public class JobsViewModel extends BaseViewModel {

    private final JobService jobService;
    private MutableLiveData<StartScreenHandyman> screenDataMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Job>> jobMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<JobDetail> jobDetailMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<JobDetailProfile> jobDetailProfileMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<String> messageResponse = new MutableLiveData<>();

    @Inject
    JobsViewModel(@NonNull Application application, JobService jobService) {
        super(application);
        this.jobService = jobService;
    }

    public LiveData<StartScreenHandyman> getStartScreenData() {
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

    public LiveData<String> getMessageResponse() {
        return messageResponse;
    }

    public void fetchDataStartScreen(String authorization, NearbyJobRequest nearbyJobRequest) {
        compositeDisposable
                .add(jobService.getStartScreen(authorization, nearbyJobRequest)
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


    public void fetchYourLocationData(String authorization, NearbyJobRequest nearbyJobRequest) {
        compositeDisposable
                .add(jobService.getJobNearBy(authorization, nearbyJobRequest)
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

    public void fetchJobsByFilter(String authorization, JobFilterRequest jobFilterRequest) {
        compositeDisposable
                .add(jobService.getJobByFilter(authorization, jobFilterRequest)
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

    public void addJobBid(String authorization, double bid, String description, MultipartBody.Part file, int jobId, double serviceFee) {
        compositeDisposable.add(jobService.addJobBid(authorization, bid, description, file, jobId, serviceFee)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((response) -> {
                    if (response.body().getStatus().equals(StatusConstant.OK))
                        messageResponse.setValue(response.body().getMessage());
                    else messageResponse.setValue(response.body().getMessage());
                }, throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_SHORT).show()));
    }

    public void addJobBidWithMultiFile(String authorization, double bid, String description, MultipartBody.Part[] files, int jobId, double serviceFee) {
        compositeDisposable.add(jobService.addJobBidWithMultiFile(authorization, bid, description, files, jobId, serviceFee)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((response) -> {
                    if (response.body().getStatus().equals(StatusConstant.OK))
                        messageResponse.setValue(response.body().getMessage());
                    else messageResponse.setValue(response.body().getMessage());
                }, throwable -> Toast.makeText(getApplication(), throwable.getMessage(), Toast.LENGTH_SHORT).show()));
    }

}