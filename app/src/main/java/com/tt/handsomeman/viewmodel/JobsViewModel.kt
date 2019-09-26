package com.tt.handsomeman.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.tt.handsomeman.response.StartScreenData
import com.tt.handsomeman.service.StartScreenService

import javax.inject.Inject

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class JobsViewModel @Inject
constructor(application: Application, private val startScreenService: StartScreenService) : AndroidViewModel(application) {

    private val screenDataMutableLiveData = MutableLiveData<StartScreenData>()
    private val latLiveData = MutableLiveData<Double>()
    private val lngLiveData = MutableLiveData<Double>()

    val startScreenData: LiveData<StartScreenData>
        get() = screenDataMutableLiveData

    private val compositeDisposable = CompositeDisposable()

    fun initData(authorization: String, lat: Double?, lng: Double?, radius: Double?) {
        compositeDisposable
                .add(startScreenService.getStartScreen(authorization, lat, lng, radius)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ startScreenResponseResponse ->
//                            assert(startScreenResponseResponse.body() != null)
                            screenDataMutableLiveData.setValue(startScreenResponseResponse.body()!!.data)
                        },
                                { throwable -> Toast.makeText(getApplication(), throwable.message, Toast.LENGTH_LONG).show() }))
    }

    fun clearSubscriptions() {
        super.onCleared()
        compositeDisposable.clear()
        Log.d("AAA", "Disposed")
    }
}