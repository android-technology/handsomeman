package com.tt.handsomeman.di.component;

import com.tt.handsomeman.di.module.AppModule;
import com.tt.handsomeman.di.module.HandymanModule;
import com.tt.handsomeman.di.module.JobModule;
import com.tt.handsomeman.di.module.MessageModule;
import com.tt.handsomeman.di.module.NetworkModule;
import com.tt.handsomeman.di.module.UserModule;
import com.tt.handsomeman.di.module.ViewModelModule;
import com.tt.handsomeman.ui.handyman.Login;
import com.tt.handsomeman.ui.handyman.OnBoardingSlidePagerActivity;
import com.tt.handsomeman.ui.handyman.Register;
import com.tt.handsomeman.ui.handyman.SignUp;
import com.tt.handsomeman.ui.handyman.SignUpAddPayout;
import com.tt.handsomeman.ui.handyman.Start;
import com.tt.handsomeman.ui.handyman.jobs.CustomerProfileJobDetail;
import com.tt.handsomeman.ui.handyman.jobs.GroupByCategory;
import com.tt.handsomeman.ui.handyman.jobs.JobDetail;
import com.tt.handsomeman.ui.handyman.jobs.JobFilterResult;
import com.tt.handsomeman.ui.handyman.jobs.JobsChildJobsFragment;
import com.tt.handsomeman.ui.handyman.jobs.JobsChildWishListFragment;
import com.tt.handsomeman.ui.handyman.jobs.YourLocation;
import com.tt.handsomeman.ui.handyman.jobs.bid_job_detail.BidJobLetterReviewFragment;
import com.tt.handsomeman.ui.handyman.messages.Conversation;
import com.tt.handsomeman.ui.handyman.messages.MessagesChildContactsFragment;
import com.tt.handsomeman.ui.handyman.messages.MessagesChildMessagesFragment;
import com.tt.handsomeman.ui.handyman.more.AddNewPayout;
import com.tt.handsomeman.ui.handyman.more.AddNewSkill;
import com.tt.handsomeman.ui.handyman.more.MoreFragment;
import com.tt.handsomeman.ui.handyman.more.MyProfileAboutFragment;
import com.tt.handsomeman.ui.handyman.more.MyProfileEdit;
import com.tt.handsomeman.ui.handyman.more.MyProfileReviewsFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        JobModule.class,
        UserModule.class,
        NetworkModule.class,
        MessageModule.class,
        HandymanModule.class,
        ViewModelModule.class})
public interface AppComponent {

    void inject(JobsChildJobsFragment fragment);

    void inject(JobsChildWishListFragment fragment);

    void inject(Login login);

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

    void inject(BidJobLetterReviewFragment reviewFragment);

    void inject(MessagesChildMessagesFragment messagesFragment);

    void inject(MessagesChildContactsFragment contactsFragment);

    void inject(Conversation conversation);

    void inject(MoreFragment moreFragment);

    void inject(MyProfileReviewsFragment profileReviewsFragment);

    void inject(MyProfileAboutFragment profileAboutFragment);

    void inject(MyProfileEdit myProfileEdit);

    void inject(AddNewSkill addNewSkill);

    void inject(AddNewPayout addNewPayout);
}
