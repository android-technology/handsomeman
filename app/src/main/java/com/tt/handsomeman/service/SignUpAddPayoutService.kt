package com.tt.handsomeman.service

import com.tt.handsomeman.model.UserActivatingAccount
import com.tt.handsomeman.response.StandardResponse
import com.tt.handsomeman.util.Constants

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface SignUpAddPayoutService {
    @POST(Constants.SIGN_UP_ADD_PAYOUT_SUFFIX)
    fun doSignUpAddPayout(@Header("Authorization") token: String, @Body userActivatingAccount: UserActivatingAccount, @Query("kindOfHandyman") kindOfHandyman: String): Call<StandardResponse>
}
