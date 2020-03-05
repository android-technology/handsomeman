package com.tt.handsomeman.service;

import com.tt.handsomeman.request.NearbyHandymanRequest;
import com.tt.handsomeman.request.NearbyJobRequest;
import com.tt.handsomeman.response.DataBracketResponse;
import com.tt.handsomeman.response.StartScreenCustomer;
import com.tt.handsomeman.response.StartScreenHandyman;
import com.tt.handsomeman.util.Constants;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface CustomerService {

    @POST(Constants.FIND_HANDYMAN)
    Single<Response<DataBracketResponse<StartScreenCustomer>>> getStartScreen(@Header("Authorization") String token, @Body NearbyHandymanRequest nearbyHandymanRequest);
}
