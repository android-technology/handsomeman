package com.tt.handsomeman.service

import com.tt.handsomeman.response.StandardResponse
import com.tt.handsomeman.util.Constants

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query

interface SignUpService {
    @FormUrlEncoded
    @POST(Constants.SIGN_UP_SUFFIX)
    fun doSignUp(@Query("type") type: String, @Field("name") name: String, @Field("mail") mail: String, @Field("password") password: String, @Field("rePassword") rePassword: String): Call<StandardResponse>
}
