package com.tt.handsomeman.util;

import androidx.lifecycle.MutableLiveData;

public class Constants {
    public static final String BASE_URL = "https://handsomeman.herokuapp.com/";

    public static final String LOGIN_SUFFIX = "api/user/login";
    public static final String SIGN_UP_SUFFIX = "api/user/registration";
    public static final String SIGN_UP_ADD_PAYOUT_SUFFIX = "api/user/active-account";
    public static final String CHANGE_PASSWORD = "api/user/changePassword";

    // Both handyman and customer can have payout
    public static final String USER_ADD_PAYOUT = "api/user/payout/add";
    public static final String USER_EDIT_PAYOUT = "api/user/payout/edit/{payoutId}";
    public static final String USER_REMOVE_PAYOUT = "api/user/payout/remove/{payoutId}";

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
    public static final String GET_CONTACT_OF_ACCOUNT = "api/message/showContactOfAccount";
    public static final String SEND_MESSAGE_TO_CONVERSATION = "api/message/sendMessageToConversation";

    public static final String GET_HANDYMAN_INFO = "api/handyman/info";
    public static final String GET_HANDYMAN_REVIEW = "api/handyman/handymanReview";
    public static final String GET_HANDYMAN_PROFILE = "api/handyman/profile";
    public static final String GET_HANDYMAN_PROFILE_EDIT = "api/handyman/profile/edit";
    public static final String GET_LIST_CATEGORY = "api/handyman/categories";
    public static final String VIEW_TRANSFER_HISTORY = "api/handyman/viewTransferHistory";
    public static final String GET_LIST_PAYOUT = "api/handyman/listPayout";
    public static final String TRANSFER_TO_BANK = "api/handyman/transfer";

    public static final String FIND_HANDYMAN = "/api/customer/findHandyman";
    public static final String FIND_HANDYMAN_CATEGORY = "/api/customer/byCategory/{id}";
    public static final String FIND_HANDYMAN_NEARBY = "/api/customer/yourLocation";

    public static final Integer NOT_ACTIVE_ACCOUNT = 1;
    public static final Integer STATE_REGISTER_ADDED_PAYOUT = 3;

    public static MutableLiveData<Double> Latitude = new MutableLiveData<>();
    public static MutableLiveData<Double> Longitude = new MutableLiveData<>();
}
