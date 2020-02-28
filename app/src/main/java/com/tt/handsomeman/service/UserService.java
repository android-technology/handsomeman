package com.tt.handsomeman.service;

import com.tt.handsomeman.request.AddNewPayoutRequest;
import com.tt.handsomeman.request.ChangePasswordRequest;
import com.tt.handsomeman.request.UserActivatingAccount;
import com.tt.handsomeman.request.UserLogin;
import com.tt.handsomeman.request.UserRegistration;
import com.tt.handsomeman.response.DataBracketResponse;
import com.tt.handsomeman.response.StandardResponse;
import com.tt.handsomeman.response.TokenState;
import com.tt.handsomeman.util.Constants;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserService {
    @POST(Constants.LOGIN_SUFFIX)
    Call<DataBracketResponse<TokenState>> doLogin(@Body UserLogin user);

    @POST(Constants.SIGN_UP_SUFFIX)
    Call<StandardResponse> doSignUp(@Query("type") String type, @Body UserRegistration userRegistration);

    @POST(Constants.SIGN_UP_ADD_PAYOUT_SUFFIX)
    Call<StandardResponse> doSignUpAddPayout(@Header("Authorization") String token, @Body UserActivatingAccount userActivatingAccount, @Query("kindOfHandyman") String kindOfHandyman);

    // Both handyman and customer can have payout
    @POST(Constants.USER_ADD_PAYOUT)
    Call<StandardResponse> addPayout(@Header("Authorization") String token, @Body AddNewPayoutRequest request);

    @POST(Constants.USER_EDIT_PAYOUT)
    Call<StandardResponse> editPayout(@Header("Authorization") String token, @Body AddNewPayoutRequest request, @Path("payoutId") Integer payoutId);

    @POST(Constants.USER_REMOVE_PAYOUT)
    Single<Response<StandardResponse>> removePayout(@Header("Authorization") String token, @Path("payoutId") Integer payoutId);

    @POST(Constants.CHANGE_PASSWORD)
    Single<Response<StandardResponse>> changePassword(@Header("Authorization") String token, @Body ChangePasswordRequest changePasswordRequest);
}
