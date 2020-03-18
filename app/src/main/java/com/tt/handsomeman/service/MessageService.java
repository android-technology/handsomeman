package com.tt.handsomeman.service;

import com.tt.handsomeman.request.SendMessageRequest;
import com.tt.handsomeman.response.DataBracketResponse;
import com.tt.handsomeman.response.ListContact;
import com.tt.handsomeman.response.ListConversation;
import com.tt.handsomeman.response.ListMessage;
import com.tt.handsomeman.response.StandardResponse;
import com.tt.handsomeman.util.Constants;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MessageService {
    @GET(Constants.GET_ALL_CONVERSATION_OF_ACCOUNT)
    Observable<Response<DataBracketResponse<ListConversation>>> getAllConversationByAccountId(@Header("Accept-Language") String locale, @Header("Authorization") String token, @Query("type") String type);

    @GET(Constants.GET_ALL_MESSAGES_IN_CONVERSATION)
    Observable<Response<DataBracketResponse<ListMessage>>> getAllMessagesInConversation(@Header("Accept-Language") String locale, @Header("Authorization") String token, @Path("conversationId") Integer conversationId);

    @DELETE(Constants.DELETE_CONVERSATION_ID)
    Single<Response<StandardResponse>> deleteConversationById(@Header("Accept-Language") String locale, @Header("Authorization") String token, @Path("conversationId") Integer conversationId);

    @GET(Constants.GET_CONTACT_OF_ACCOUNT)
    Observable<Response<DataBracketResponse<ListContact>>> getContactOfAccount(@Header("Accept-Language") String locale, @Header("Authorization") String token, @Query("type") String type);

    @PUT(Constants.SEND_MESSAGE_TO_CONVERSATION)
    Single<Response<StandardResponse>> sendMessageToConversation(@Header("Accept-Language") String locale, @Header("Authorization") String token, @Body SendMessageRequest message);
}
