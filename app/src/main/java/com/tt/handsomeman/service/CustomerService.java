package com.tt.handsomeman.service;

import com.tt.handsomeman.model.Customer;
import com.tt.handsomeman.request.HandymanDetailRequest;
import com.tt.handsomeman.request.NearbyHandymanRequest;
import com.tt.handsomeman.response.CustomerProfileResponse;
import com.tt.handsomeman.response.CustomerReviewProfile;
import com.tt.handsomeman.response.DataBracketResponse;
import com.tt.handsomeman.response.HandymanDetailResponse;
import com.tt.handsomeman.response.NearbyHandymanResponse;
import com.tt.handsomeman.response.StandardResponse;
import com.tt.handsomeman.response.StartScreenCustomer;
import com.tt.handsomeman.util.Constants;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CustomerService {

    @POST(Constants.FIND_HANDYMAN)
    Single<Response<DataBracketResponse<StartScreenCustomer>>> getStartScreen(@Header("Accept-Language") String locale, @Header("Authorization") String token, @Body NearbyHandymanRequest nearbyHandymanRequest);

    @POST(Constants.FIND_HANDYMAN_CATEGORY)
    Single<Response<DataBracketResponse<NearbyHandymanResponse>>> getHandymanByCateGory(@Header("Accept-Language") String locale, @Header("Authorization") String token, @Path("id") Integer categoryId, @Body NearbyHandymanRequest nearbyHandymanRequest);

    @POST(Constants.FIND_HANDYMAN_NEARBY)
    Single<Response<DataBracketResponse<NearbyHandymanResponse>>> getHandymanNearby(@Header("Accept-Language") String locale, @Header("Authorization") String token, @Body NearbyHandymanRequest nearbyHandymanRequest);

    @GET(Constants.GET_CUSTOMER_REVIEW)
    Single<Response<DataBracketResponse<CustomerReviewProfile>>> getCustomerReview(@Header("Accept-Language") String locale, @Header("Authorization") String token);

    @POST(Constants.GET_HANDYMAN_DETAIL)
    Single<Response<DataBracketResponse<HandymanDetailResponse>>> getHandymanDetail(@Header("Accept-Language") String locale, @Header("Authorization") String token, @Body HandymanDetailRequest handymanDetailRequest);

    @GET(Constants.GET_CUSTOMER_INFO)
    Single<Response<DataBracketResponse<Customer>>> getCustomerInfo(@Header("Accept-Language") String locale, @Header("Authorization") String token);

    @GET(Constants.GET_CUSTOMER_PROFILE)
    Single<Response<DataBracketResponse<CustomerProfileResponse>>> getCustomerProfile(@Header("Accept-Language") String locale, @Header("Authorization") String token);

    @POST(Constants.EDIT_CUSTOMER_PROFILE)
    Single<Response<StandardResponse>> editCustomerProfile(@Header("Accept-Language") String locale, @Header("Authorization") String token, @Query("customerEditName") String customerEditName);
}
