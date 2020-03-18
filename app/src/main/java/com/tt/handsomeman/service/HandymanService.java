package com.tt.handsomeman.service;

import com.tt.handsomeman.model.Handyman;
import com.tt.handsomeman.request.HandymanEditRequest;
import com.tt.handsomeman.request.HandymanTransferRequest;
import com.tt.handsomeman.response.DataBracketResponse;
import com.tt.handsomeman.response.HandymanProfileResponse;
import com.tt.handsomeman.response.HandymanReviewProfile;
import com.tt.handsomeman.response.ListCategory;
import com.tt.handsomeman.response.ListPayoutResponse;
import com.tt.handsomeman.response.ListTransferHistory;
import com.tt.handsomeman.response.StandardResponse;
import com.tt.handsomeman.util.Constants;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface HandymanService {

    @GET(Constants.GET_HANDYMAN_INFO)
    Single<Response<DataBracketResponse<Handyman>>> getHandymanInfo(@Header("Accept-Language") String locale, @Header("Authorization") String header);

    @GET(Constants.GET_HANDYMAN_REVIEW)
    Single<Response<DataBracketResponse<HandymanReviewProfile>>> getHandymanReview(@Header("Accept-Language") String locale, @Header("Authorization") String token);

    @GET(Constants.GET_HANDYMAN_PROFILE)
    Single<Response<DataBracketResponse<HandymanProfileResponse>>> getHandymanProfile(@Header("Accept-Language") String locale, @Header("Authorization") String header);

    @POST(Constants.GET_HANDYMAN_PROFILE_EDIT)
    Single<Response<StandardResponse>> editHandymanProfile(@Header("Accept-Language") String locale, @Header("Authorization") String header, @Body HandymanEditRequest handymanEditRequest);

    @GET(Constants.GET_LIST_CATEGORY)
    Single<Response<DataBracketResponse<ListCategory>>> getListCategory(@Header("Accept-Language") String locale, @Header("Authorization") String header);

    @GET(Constants.VIEW_TRANSFER_HISTORY)
    Single<Response<DataBracketResponse<ListTransferHistory>>> viewTransferHistory(@Header("Accept-Language") String locale, @Header("Authorization") String header);

    @GET(Constants.GET_LIST_PAYOUT)
    Single<Response<DataBracketResponse<ListPayoutResponse>>> getListPayoutOfHandyman(@Header("Accept-Language") String locale, @Header("Authorization") String header);

    @POST(Constants.TRANSFER_TO_BANK)
    Single<Response<StandardResponse>> transferToBank(@Header("Accept-Language") String locale, @Header("Authorization") String header, @Body HandymanTransferRequest handymanTransferRequest);
}
