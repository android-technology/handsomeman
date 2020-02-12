package com.tt.handsomeman.service;

import com.tt.handsomeman.model.Handyman;
import com.tt.handsomeman.request.HandymanEditRequest;
import com.tt.handsomeman.response.DataBracketResponse;
import com.tt.handsomeman.response.HandymanProfileResponse;
import com.tt.handsomeman.response.HandymanReviewProfile;
import com.tt.handsomeman.response.StandardResponse;
import com.tt.handsomeman.util.Constants;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface HandymanService {

    @GET(Constants.GET_HANDYMAN_INFO)
    Single<Response<DataBracketResponse<Handyman>>> getHandymanInfo(@Header("Authorization") String header);

    @GET(Constants.GET_HANDYMAN_REVIEW)
    Single<Response<DataBracketResponse<HandymanReviewProfile>>> getHandymanReview(@Header("Authorization") String token);

    @GET(Constants.GET_HANDYMAN_PROFILE)
    Single<Response<DataBracketResponse<HandymanProfileResponse>>> getHandymanProfile(@Header("Authorization") String header);

    @POST(Constants.GET_HANDYMAN_PROFILE_EDIT)
    Single<Response<StandardResponse>> editHandymanProfile(@Header("Authorization") String header, @Body HandymanEditRequest handymanEditRequest);
}
