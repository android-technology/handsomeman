package com.tt.handsomeman.service;

import com.tt.handsomeman.request.NearbyHandymanRequest;
import com.tt.handsomeman.response.DataBracketResponse;
import com.tt.handsomeman.response.NearbyHandymanResponse;
import com.tt.handsomeman.response.StartScreenCustomer;
import com.tt.handsomeman.util.Constants;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CustomerService {

    @POST(Constants.FIND_HANDYMAN)
    Single<Response<DataBracketResponse<StartScreenCustomer>>> getStartScreen(@Header("Authorization") String token, @Body NearbyHandymanRequest nearbyHandymanRequest);

    @POST(Constants.FIND_HANDYMAN_CATEGORY)
    Single<Response<DataBracketResponse<NearbyHandymanResponse>>> getHandymanByCateGory(@Header("Authorization") String token, @Path("id") Integer categoryId, @Body NearbyHandymanRequest nearbyHandymanRequest);

    @POST(Constants.FIND_HANDYMAN_NEARBY)
    Single<Response<DataBracketResponse<NearbyHandymanResponse>>> getHandymanNearby(@Header("Authorization") String token, @Body NearbyHandymanRequest nearbyHandymanRequest);
}
