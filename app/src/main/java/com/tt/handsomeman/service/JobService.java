package com.tt.handsomeman.service;

import com.tt.handsomeman.model.JobDetail;
import com.tt.handsomeman.request.JobFilterRequest;
import com.tt.handsomeman.request.NearbyJobRequest;
import com.tt.handsomeman.response.DataBracketResponse;
import com.tt.handsomeman.response.JobDetailProfile;
import com.tt.handsomeman.response.ListJob;
import com.tt.handsomeman.response.StandardResponse;
import com.tt.handsomeman.response.StartScreenHandyman;
import com.tt.handsomeman.util.Constants;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JobService {
    @POST(Constants.START_SCREEN_SUFFIX)
    Single<Response<DataBracketResponse<StartScreenHandyman>>> getStartScreen(@Header("Authorization") String token, @Body NearbyJobRequest nearbyJobRequest);

    @POST(Constants.YOUR_LOCATION_SUFFIX)
    Single<Response<DataBracketResponse<ListJob>>> getJobNearBy(@Header("Authorization") String token, @Body NearbyJobRequest nearbyJobRequest);

    @POST(Constants.JOB_BY_CATEGORY_SUFFIX)
    Single<Response<DataBracketResponse<ListJob>>> getJobByCategory(@Header("Authorization") String token, @Path("id") Integer categoryId);

    @POST(Constants.JOB_FILTER_SUFFIX)
    Single<Response<DataBracketResponse<ListJob>>> getJobByFilter(@Header("Authorization") String token, @Body JobFilterRequest jobFilterRequest);

    @GET(Constants.JOB_DETAIL_SUFFIX)
    Single<Response<DataBracketResponse<JobDetail>>> getJobDetail(@Header("Authorization") String token, @Path("id") Integer jobId);

    @POST(Constants.JOB_DETAIL_PROFILE_SUFFIX)
    Single<Response<DataBracketResponse<JobDetailProfile>>> getJobDetailProfile(@Header("Authorization") String token, @Path("id") Integer customerId);

    @GET(Constants.JOB_WISH_LIST)
    Single<Response<DataBracketResponse<ListJob>>> getJobWishList(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST(Constants.ADD_JOB_BID)
    Single<Response<StandardResponse>> addJobBid(@Header("Authorization") String token, @Field("bid") double bid, @Field("description") String description, @Field("file") MultipartBody.Part file, @Field("jobId") int jobId, @Field("serviceFee") double serviceFee);

    @FormUrlEncoded
    @POST(Constants.ADD_JOB_BID_WITH_MULTI_FILE)
    Single<Response<StandardResponse>> addJobBidWithMultiFile(@Header("Authorization") String token, @Field("bid") double bid, @Field("description") String description, @Field("files") MultipartBody.Part[] files, @Field("jobId") int jobId, @Field("serviceFee") double serviceFee);
}
