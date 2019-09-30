package com.tt.handsomeman.service;

import com.tt.handsomeman.response.DataBracketResponse;
import com.tt.handsomeman.response.TokenState;
import com.tt.handsomeman.util.Constants;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginService {
    @FormUrlEncoded
    @POST(Constants.LOGIN_SUFFIX)
    Call<DataBracketResponse<TokenState>> doLogin(@Field("email") String email, @Field("password") String password);
}
