package com.tt.handsomeman.service;

import com.tt.handsomeman.response.DataBracketResponse;
import com.tt.handsomeman.response.ListConversation;
import com.tt.handsomeman.response.ListMessage;
import com.tt.handsomeman.response.StandardResponse;
import com.tt.handsomeman.util.Constants;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface MessageService {
    @GET(Constants.GET_ALL_CONVERSATION_OF_ACCOUNT)
    Observable<Response<DataBracketResponse<ListConversation>>> getAllConversationByAccountId(@Header("Authorization") String token);

    @GET(Constants.GET_ALL_MESSAGES_IN_CONVERSATION)
    Observable<Response<DataBracketResponse<ListMessage>>> getAllMessagesInConversation(@Header("Authorization") String token, @Path("conversationId") Integer conversationId);

    @DELETE(Constants.DELETE_CONVERSATION_ID)
    Single<Response<StandardResponse>> deleteConversationById(@Header("Authorization") String token, @Path("conversationId") Integer conversationId);
}
