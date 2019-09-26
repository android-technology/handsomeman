package com.tt.handsomeman.service

import com.tt.handsomeman.response.StartScreenResponse
import com.tt.handsomeman.util.Constants

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface StartScreenService {
    @FormUrlEncoded
    @POST(Constants.START_SCREEN_SUFFIX)
    fun getStartScreen(@Header("Authorization") token: String, @Field("lat") lat: Double?, @Field("lng") lng: Double?, @Field("radius") radius: Double?): Single<Response<StartScreenResponse>>
}
