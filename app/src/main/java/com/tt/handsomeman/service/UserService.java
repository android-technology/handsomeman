package com.tt.handsomeman.service;

import com.tt.handsomeman.model.UserActivatingAccount;
import com.tt.handsomeman.request.UserLogin;
import com.tt.handsomeman.request.UserRegistration;
import com.tt.handsomeman.response.DataBracketResponse;
import com.tt.handsomeman.response.StandardResponse;
import com.tt.handsomeman.response.TokenState;
import com.tt.handsomeman.util.Constants;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {
    @POST(Constants.LOGIN_SUFFIX)
    Call<DataBracketResponse<TokenState>> doLogin(@Body UserLogin user);

    @POST(Constants.SIGN_UP_SUFFIX)
    Call<StandardResponse> doSignUp(@Query("type") String type, @Body UserRegistration userRegistration);

    @POST(Constants.SIGN_UP_ADD_PAYOUT_SUFFIX)
    Call<StandardResponse> doSignUpAddPayout(@Header("Authorization") String token, @Body UserActivatingAccount userActivatingAccount, @Query("kindOfHandyman") String kindOfHandyman);
}
