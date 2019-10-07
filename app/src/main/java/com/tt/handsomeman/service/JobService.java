package com.tt.handsomeman.service;

import com.tt.handsomeman.model.Job;
import com.tt.handsomeman.model.JobDetail;
import com.tt.handsomeman.response.DataBracketResponse;
import com.tt.handsomeman.response.ListJob;
import com.tt.handsomeman.response.StartScreenData;
import com.tt.handsomeman.util.Constants;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JobService {
    @FormUrlEncoded
    @POST(Constants.START_SCREEN_SUFFIX)
    Single<Response<DataBracketResponse<StartScreenData>>> getStartScreen(@Header("Authorization") String token, @Field("lat") Double lat, @Field("lng") Double lng, @Field("radius") Double radius);

    @FormUrlEncoded
    @POST(Constants.YOUR_LOCATION_SUFFIX)
    Single<Response<DataBracketResponse<ListJob>>> getJobNearBy(@Header("Authorization") String token, @Field("lat") Double lat, @Field("lng") Double lng, @Field("radius") Double radius);

    @POST(Constants.JOB_BY_CATEGORY_SUFFIX)
    Single<Response<DataBracketResponse<ListJob>>> getJobByCategory(@Header("Authorization") String token, @Path("id") Integer categoryId);

    @FormUrlEncoded
    @POST(Constants.JOB_FILTER_SUFFIX)
    Single<Response<DataBracketResponse<ListJob>>> getJobByFilter(@Header("Authorization") String token, @Field("lat") Double lat, @Field("lng") Double lng, @Field("radius") Integer radius, @Field("budgetMin") Integer priceMin, @Field("budgetMax") Integer priceMax, @Field("createTime") String createTime);

    @GET(Constants.JOB_FILTER_SUFFIX)
    Single<Response<DataBracketResponse<JobDetail>>> getJobDetail(@Header("Authorization") String token, @Path("id") Integer jobId);
}
