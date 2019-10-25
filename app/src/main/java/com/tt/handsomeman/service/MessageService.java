package com.tt.handsomeman.service;

import com.tt.handsomeman.response.DataBracketResponse;
import com.tt.handsomeman.response.ListConversation;
import com.tt.handsomeman.util.Constants;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface MessageService {
    @GET(Constants.GET_ALL_CONVERSATION_OF_ACCOUNT)
    Observable<Response<DataBracketResponse<ListConversation>>> getAllConversationByAccountId(@Header("Authorization") String token);
}
