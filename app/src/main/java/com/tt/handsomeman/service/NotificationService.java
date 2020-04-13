package com.tt.handsomeman.service;

import com.tt.handsomeman.request.AcceptBidRequest;
import com.tt.handsomeman.response.DataBracketResponse;
import com.tt.handsomeman.response.MadeABidNotificationResponse;
import com.tt.handsomeman.response.NotificationResponse;
import com.tt.handsomeman.response.PaidPaymentNotificationResponse;
import com.tt.handsomeman.response.StandardResponse;
import com.tt.handsomeman.util.Constants;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface NotificationService {

    @GET(Constants.GET_ALL_NOTIFICATION)
    Single<Response<DataBracketResponse<List<NotificationResponse>>>> getAllNotification(@Header("Authorization") String token, @Query("type") String type);

    @POST(Constants.MARK_NOTIFICATION_AS_READ)
    Single<Response<StandardResponse>> markNotificationRead(@Header("Authorization") String token, @Query("notificationId") Integer notificationId);

    @GET(Constants.READ_MADE_BID_NOTIFICATION)
    Single<Response<DataBracketResponse<MadeABidNotificationResponse>>> viewMadeBid(@Header("Authorization") String token, @Query("jobBidId") Integer jobBidId);

    @POST(Constants.ACCEPT_BID)
    Single<Response<StandardResponse>> acceptBid(@Header("Authorization") String token, @Body AcceptBidRequest acceptBidRequest);

    @GET(Constants.READ_PAID_PAYMENT_NOTIFICATION)
    Single<Response<DataBracketResponse<PaidPaymentNotificationResponse>>> viewPaidPayment(@Header("Authorization") String token, @Query("customerTransferId") Integer customerTransferId);
}
