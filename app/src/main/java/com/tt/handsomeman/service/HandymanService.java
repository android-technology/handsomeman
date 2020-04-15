package com.tt.handsomeman.service;

import com.tt.handsomeman.model.Handyman;
import com.tt.handsomeman.model.HandymanJobDetail;
import com.tt.handsomeman.request.HandymanEditRequest;
import com.tt.handsomeman.request.HandymanTransferRequest;
import com.tt.handsomeman.request.JobFilterRequest;
import com.tt.handsomeman.request.NearbyJobRequest;
import com.tt.handsomeman.response.DataBracketResponse;
import com.tt.handsomeman.response.HandymanProfileResponse;
import com.tt.handsomeman.response.HandymanReviewProfile;
import com.tt.handsomeman.response.JobDetailProfile;
import com.tt.handsomeman.response.ListCategory;
import com.tt.handsomeman.response.ListJob;
import com.tt.handsomeman.response.ListPayoutResponse;
import com.tt.handsomeman.response.ListTransferHistory;
import com.tt.handsomeman.response.MyProjectList;
import com.tt.handsomeman.response.StandardResponse;
import com.tt.handsomeman.response.StartScreenHandyman;
import com.tt.handsomeman.response.TransactionDetailResponse;
import com.tt.handsomeman.util.Constants;

import java.util.List;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface HandymanService {

    @GET(Constants.GET_HANDYMAN_INFO)
    Single<Response<DataBracketResponse<Handyman>>> getHandymanInfo(@Header("Accept-Language") String locale,
                                                                    @Header("Authorization") String header);

    @GET(Constants.GET_HANDYMAN_REVIEW)
    Single<Response<DataBracketResponse<HandymanReviewProfile>>> getHandymanReview(@Header("Accept-Language") String locale,
                                                                                   @Header("Authorization") String token);

    @GET(Constants.GET_HANDYMAN_PROFILE)
    Single<Response<DataBracketResponse<HandymanProfileResponse>>> getHandymanProfile(@Header("Accept-Language") String locale,
                                                                                      @Header("Authorization") String header);

    @POST(Constants.GET_HANDYMAN_PROFILE_EDIT)
    Single<Response<StandardResponse>> editHandymanProfile(@Header("Accept-Language") String locale,
                                                           @Header("Authorization") String header,
                                                           @Body HandymanEditRequest handymanEditRequest);

    @GET(Constants.HANDYMAN_GET_LIST_CATEGORY)
    Single<Response<DataBracketResponse<ListCategory>>> getListCategory(@Header("Accept-Language") String locale,
                                                                        @Header("Authorization") String header);

    @GET(Constants.VIEW_TRANSFER_HISTORY)
    Single<Response<DataBracketResponse<ListTransferHistory>>> viewTransferHistory(@Header("Accept-Language") String locale,
                                                                                   @Header("Authorization") String header);

    @GET(Constants.HANDYMAN_GET_LIST_PAYOUT)
    Single<Response<DataBracketResponse<ListPayoutResponse>>> getListPayoutOfHandyman(@Header("Accept-Language") String locale,
                                                                                      @Header("Authorization") String header);

    @POST(Constants.TRANSFER_TO_BANK)
    Single<Response<StandardResponse>> transferToBank(@Header("Accept-Language") String locale,
                                                      @Header("Authorization") String header,
                                                      @Body HandymanTransferRequest handymanTransferRequest);

    @GET(Constants.HANDYMAN_MY_PROJECT)
    Single<Response<DataBracketResponse<MyProjectList>>> getJobsOfHandyman(@Header("Accept-Language") String locale,
                                                                           @Header("Authorization") String token);

    @POST(Constants.HANDYMAN_START_SCREEN_SUFFIX)
    Single<Response<DataBracketResponse<StartScreenHandyman>>> getStartScreen(@Header("Accept-Language") String locale,
                                                                              @Header("Authorization") String token,
                                                                              @Body NearbyJobRequest nearbyJobRequest);

    @POST(Constants.HANDYMAN_YOUR_LOCATION_SUFFIX)
    Single<Response<DataBracketResponse<ListJob>>> getJobNearBy(@Header("Accept-Language") String locale,
                                                                @Header("Authorization") String token,
                                                                @Body NearbyJobRequest nearbyJobRequest);

    @POST(Constants.HANDYMAN_GET_JOB_BY_CATEGORY_SUFFIX)
    Single<Response<DataBracketResponse<ListJob>>> getJobByCategory(@Header("Accept-Language") String locale,
                                                                    @Header("Authorization") String token,
                                                                    @Path("id") Integer categoryId,
                                                                    @Body NearbyJobRequest nearbyJobRequest);

    @POST(Constants.HANDYMAN_GET_JOB_FILTER_SUFFIX)
    Single<Response<DataBracketResponse<ListJob>>> getJobByFilter(@Header("Accept-Language") String locale,
                                                                  @Header("Authorization") String token,
                                                                  @Body JobFilterRequest jobFilterRequest);

    @GET(Constants.HANDYMAN_VIEW_JOB_DETAIL)
    Single<Response<DataBracketResponse<HandymanJobDetail>>> getHandymanJobDetail(@Header("Accept-Language") String locale,
                                                                                  @Header("Authorization") String token,
                                                                                  @Path("id") Integer jobId);

    @POST(Constants.HANDYMAN_VIEW_CUSTOMER_PROFILE)
    Single<Response<DataBracketResponse<JobDetailProfile>>> getJobDetailProfile(@Header("Accept-Language") String locale,
                                                                                @Header("Authorization") String token,
                                                                                @Path("id") Integer customerId);

    @GET(Constants.HANDYMAN_JOB_WISH_LIST)
    Single<Response<DataBracketResponse<ListJob>>> getJobWishList(@Header("Accept-Language") String locale,
                                                                  @Header("Authorization") String token);

    @Multipart
    @POST(Constants.HANDYMAN_ADD_JOB_BID)
    Single<Response<StandardResponse>> addJobBid(@Header("Accept-Language") String locale,
                                                 @Header("Authorization") String token,
                                                 @Part("bid") RequestBody bid,
                                                 @Part("description") RequestBody description,
                                                 @Part List<MultipartBody.Part> files,
                                                 @Part("jobId") RequestBody jobId,
                                                 @Part("serviceFee") RequestBody serviceFee,
                                                 @Part("bidTime") RequestBody bidTime,
                                                 @Part("md5List") List<RequestBody> md5List);

    @POST(Constants.MARK_NOTIFICATION_AS_READ)
    Single<Response<StandardResponse>> markNotificationRead(@Header("Authorization") String token,
                                                            @Query("notificationId") Integer notificationId);

    @GET(Constants.VIEW_PAYMENT_TRANSACTION)
    Single<Response<DataBracketResponse<TransactionDetailResponse>>> viewPaymentTransaction(@Header("Accept-Language") String locale,
                                                                                            @Header("Authorization") String token,
                                                                                            @Query("jobId") Integer jobId);
}
