package com.tt.handsomeman.service;

import com.tt.handsomeman.response.StartScreenResponse;
import com.tt.handsomeman.util.Constants;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface StartScreenService {
    @FormUrlEncoded
    @POST(Constants.START_SCREEN_SUFFIX)
    Single<Response<StartScreenResponse>> getStartScreen(@Header("Authorization") String token, @Field("lat") Double lat, @Field("lng") Double lng, @Field("radius") Double radius);
}
