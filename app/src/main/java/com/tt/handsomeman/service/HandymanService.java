package com.tt.handsomeman.service;

import com.tt.handsomeman.model.Handyman;
import com.tt.handsomeman.response.DataBracketResponse;
import com.tt.handsomeman.util.Constants;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface HandymanService {

    @GET(Constants.GET_HANDYMAN_INFO)
    Single<Response<DataBracketResponse<Handyman>>> getHandymanInfo(@Header("Authorization") String header);
}
