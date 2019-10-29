package com.tt.handsomeman.util;

import androidx.lifecycle.MutableLiveData;

public class Constants {
    public static final String BASE_URL = "https://handsomeman.herokuapp.com/";
    public static final String LOGIN_SUFFIX = "api/login";
    public static final String SIGN_UP_SUFFIX = "api/registration";
    public static final String SIGN_UP_ADD_PAYOUT_SUFFIX = "api/active-account";

    public static final String START_SCREEN_SUFFIX = "api/jobs/startScreen";
    public static final String YOUR_LOCATION_SUFFIX = "api/jobs/yourLocation";
    public static final String JOB_BY_CATEGORY_SUFFIX = "api/jobs/byCategory/{id}";
    public static final String JOB_FILTER_SUFFIX = "api/jobs/filter";
    public static final String JOB_DETAIL_SUFFIX = "api/jobs/{id}";
    public static final String JOB_DETAIL_PROFILE_SUFFIX = "api/jobs/jobDetailProfile/{id}";
    public static final String JOB_WISH_LIST = "api/jobs/jobWishList";
    public static final String ADD_JOB_BID = "api/jobs/addJobBid";
    public static final String ADD_JOB_BID_WITH_MULTI_FILE = "api/jobs/addJobBidWithMultiFiles";

    public static final String GET_ALL_CONVERSATION_OF_ACCOUNT = "api/message/getAllConversationByAccountId";
    public static final String GET_ALL_MESSAGES_IN_CONVERSATION = "api/message/getAllMessageInConversation/{conversationId}";
    public static final String DELETE_CONVERSATION_ID = "api/message/deleteConversation/{conversationId}";

    public static final Integer NOT_ACTIVE_ACCOUNT = 1;
    public static final Integer STATE_REGISTER_ADDED_PAYOUT = 3;

    public static MutableLiveData<Double> Latitude = new MutableLiveData<>();
    public static MutableLiveData<Double> Longitude = new MutableLiveData<>();
}
