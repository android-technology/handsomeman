package com.tt.handsomeman.service;

import com.tt.handsomeman.model.UserActivatingAccount;
import com.tt.handsomeman.response.DataBracketResponse;
import com.tt.handsomeman.response.StandardResponse;
import com.tt.handsomeman.response.TokenState;
import com.tt.handsomeman.util.Constants;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {
    @FormUrlEncoded
    @POST(Constants.LOGIN_SUFFIX)
    Call<DataBracketResponse<TokenState>> doLogin(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST(Constants.SIGN_UP_SUFFIX)
    Call<StandardResponse> doSignUp(@Query("type") String type, @Field("name") String name, @Field("mail") String mail, @Field("password") String password, @Field("rePassword") String rePassword);

    @POST(Constants.SIGN_UP_ADD_PAYOUT_SUFFIX)
    Call<StandardResponse> doSignUpAddPayout(@Header("Authorization") String token, @Body UserActivatingAccount userActivatingAccount, @Query("kindOfHandyman") String kindOfHandyman);
}
