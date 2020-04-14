package com.tt.handsomeman.di.component;

import com.tt.handsomeman.di.module.AppModule;
import com.tt.handsomeman.di.module.CustomerModule;
import com.tt.handsomeman.di.module.HandymanModule;
import com.tt.handsomeman.di.module.MessageModule;
import com.tt.handsomeman.di.module.NetworkModule;
import com.tt.handsomeman.di.module.NotificationModule;
import com.tt.handsomeman.di.module.UserModule;
import com.tt.handsomeman.di.module.ViewModelModule;
import com.tt.handsomeman.ui.AddNewPayout;
import com.tt.handsomeman.ui.ChangePassword;
import com.tt.handsomeman.ui.ForgotPassword;
import com.tt.handsomeman.ui.Login;
import com.tt.handsomeman.ui.MyProfile;
import com.tt.handsomeman.ui.NotificationsFragment;
import com.tt.handsomeman.ui.OnBoardingSlidePagerActivity;
import com.tt.handsomeman.ui.Register;
import com.tt.handsomeman.ui.SignUp;
import com.tt.handsomeman.ui.Start;
import com.tt.handsomeman.ui.ViewPayout;
import com.tt.handsomeman.ui.customer.CustomerMainScreen;
import com.tt.handsomeman.ui.customer.find_handyman.FindHandymanCategory;
import com.tt.handsomeman.ui.customer.find_handyman.FindHandymanChildHandymanFragment;
import com.tt.handsomeman.ui.customer.find_handyman.HandymanDetail;
import com.tt.handsomeman.ui.customer.find_handyman.HandymanNearYourLocation;
import com.tt.handsomeman.ui.customer.more.CustomerMakeTransaction;
import com.tt.handsomeman.ui.customer.more.CustomerMoreFragment;
import com.tt.handsomeman.ui.customer.more.CustomerProfileAboutFragment;
import com.tt.handsomeman.ui.customer.more.CustomerProfileEdit;
import com.tt.handsomeman.ui.customer.more.CustomerProfileReviewFragment;
import com.tt.handsomeman.ui.customer.more.CustomerTransferHistory;
import com.tt.handsomeman.ui.customer.my_projects.CustomerMyProjectsChildInProgressFragment;
import com.tt.handsomeman.ui.customer.my_projects.MyJobDetail;
import com.tt.handsomeman.ui.customer.my_projects.MyProjectsFragment;
import com.tt.handsomeman.ui.customer.my_projects.add_job.AddNewJob;
import com.tt.handsomeman.ui.customer.my_projects.add_job.AddNewJobChildFirstFragment;
import com.tt.handsomeman.ui.customer.my_projects.add_job.AddNewJobChildSecondFragment;
import com.tt.handsomeman.ui.customer.notification.ViewMadeBid;
import com.tt.handsomeman.ui.handyman.HandyManMainScreen;
import com.tt.handsomeman.ui.handyman.SignUpAddPayout;
import com.tt.handsomeman.ui.handyman.ViewJobTransaction;
import com.tt.handsomeman.ui.handyman.jobs.CustomerProfileJobDetail;
import com.tt.handsomeman.ui.handyman.jobs.GroupByCategory;
import com.tt.handsomeman.ui.handyman.jobs.JobDetail;
import com.tt.handsomeman.ui.handyman.jobs.JobFilterResult;
import com.tt.handsomeman.ui.handyman.jobs.JobsChildJobsFragment;
import com.tt.handsomeman.ui.handyman.jobs.JobsChildWishListFragment;
import com.tt.handsomeman.ui.handyman.jobs.YourLocation;
import com.tt.handsomeman.ui.handyman.jobs.bid_job_detail.BidJobDetail;
import com.tt.handsomeman.ui.handyman.more.AddNewSkill;
import com.tt.handsomeman.ui.handyman.more.MoreFragment;
import com.tt.handsomeman.ui.handyman.more.MyProfileAboutFragment;
import com.tt.handsomeman.ui.handyman.more.MyProfileEdit;
import com.tt.handsomeman.ui.handyman.more.MyProfileReviewsFragment;
import com.tt.handsomeman.ui.handyman.more.TransferHistory;
import com.tt.handsomeman.ui.handyman.more.TransferToBank;
import com.tt.handsomeman.ui.handyman.notifications.ViewTransaction;
import com.tt.handsomeman.ui.messages.Conversation;
import com.tt.handsomeman.ui.messages.MessagesChildMessagesFragment;
import com.tt.handsomeman.ui.messages.MessagesFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        UserModule.class,
        NetworkModule.class,
        MessageModule.class,
        NotificationModule.class,
        HandymanModule.class,
        CustomerModule.class,
        ViewModelModule.class})
public interface AppComponent {

    void inject(JobsChildJobsFragment fragment);

    void inject(JobsChildWishListFragment fragment);

    void inject(Login login);

    void inject(ForgotPassword forgotPassword);

    void inject(SignUp signUp);

    void inject(Start start);

    void inject(OnBoardingSlidePagerActivity activity);

    void inject(Register register);

    void inject(SignUpAddPayout signUpAddPayout);

    void inject(YourLocation yourLocation);

    void inject(GroupByCategory category);

    void inject(JobFilterResult jobFilterResult);

    void inject(JobDetail jobDetail);

    void inject(CustomerProfileJobDetail customerProfileJobDetail);

    void inject(BidJobDetail bidJobDetail);

    void inject(MessagesChildMessagesFragment messagesFragment);

    void inject(MessagesFragment messagesFragment);

    void inject(Conversation conversation);

    void inject(MoreFragment moreFragment);

    void inject(MyProfileReviewsFragment profileReviewsFragment);

    void inject(MyProfileAboutFragment profileAboutFragment);

    void inject(MyProfileEdit myProfileEdit);

    void inject(AddNewSkill addNewSkill);

    void inject(AddNewPayout addNewPayout);

    void inject(ViewPayout viewPayout);

    void inject(TransferHistory transferHistory);

    void inject(TransferToBank transferToBank);

    void inject(ChangePassword changePassword);

    void inject(FindHandymanChildHandymanFragment handymanChildHandymanFragment);

    void inject(FindHandymanCategory findHandymanCategory);

    void inject(HandymanNearYourLocation handymanNearYourLocation);

    void inject(CustomerMoreFragment customerMoreFragment);

    void inject(CustomerProfileAboutFragment customerProfileAboutFragment);

    void inject(CustomerProfileReviewFragment customerProfileReviewFragment);

    void inject(MyProfile myProfile);

    void inject(CustomerProfileEdit customerProfileEdit);

    void inject(HandymanDetail handymanDetail);

    void inject(MyProjectsFragment myProjectsFragment);

    void inject(CustomerMyProjectsChildInProgressFragment customerMyProjectsChildInProgressFragment);

    void inject(AddNewJob addNewJob);

    void inject(AddNewJobChildFirstFragment addNewJobChildFirstFragment);

    void inject(AddNewJobChildSecondFragment addNewJobChildSecondFragment);

    void inject(CustomerMainScreen customerMainScreen);

    void inject(HandyManMainScreen handyManMainScreen);

    void inject(MyJobDetail myJobDetail);

    void inject(NotificationsFragment notificationsFragment);

    void inject(ViewMadeBid viewMadeBid);

    void inject(com.tt.handsomeman.ui.handyman.my_projects.MyProjectsFragment myProjectsFragment);

    void inject(CustomerMakeTransaction customerMakeTransaction);

    void inject(CustomerTransferHistory customerTransferHistory);

    void inject(ViewTransaction viewTransaction);

    void inject(ViewJobTransaction viewJobTransaction);
}
