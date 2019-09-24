package com.tt.handsomeman.service;

import com.tt.handsomeman.response.StandardResponse;
import com.tt.handsomeman.util.Constants;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SignUpService {
    @FormUrlEncoded
    @POST(Constants.SIGN_UP_SUFFIX)
    Call<StandardResponse> doSignUp(@Query("type") String type, @Field("name") String name, @Field("mail") String mail, @Field("password") String password, @Field("rePassword") String rePassword);
}
