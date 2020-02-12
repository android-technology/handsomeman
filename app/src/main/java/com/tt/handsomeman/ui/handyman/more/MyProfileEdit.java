package com.tt.handsomeman.ui.handyman.more;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.tt.handsomeman.HandymanApp;
import com.tt.handsomeman.R;
import com.tt.handsomeman.adapter.SkillEditAdapter;
import com.tt.handsomeman.model.Handyman;
import com.tt.handsomeman.model.Skill;
import com.tt.handsomeman.request.HandymanEditRequest;
import com.tt.handsomeman.request.SkillEditRequest;
import com.tt.handsomeman.response.HandymanProfileResponse;
import com.tt.handsomeman.response.StandardResponse;
import com.tt.handsomeman.ui.BaseAppCompatActivity;
import com.tt.handsomeman.util.SharedPreferencesUtils;
import com.tt.handsomeman.viewmodel.HandymanViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MyProfileEdit extends BaseAppCompatActivity<HandymanViewModel> {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    SharedPreferencesUtils sharedPreferencesUtils;
    private TextView yourNameEdit, educationEdit, aboutEdit;
    private ImageButton ibEdit;
    private SkillEditAdapter skillEditAdapter;
    private RecyclerView rcvSkillEdit;
    private List<Skill> skillEditList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_edit);
        HandymanApp.getComponent().inject(this);
        baseViewModel = ViewModelProviders.of(this, viewModelFactory).get(HandymanViewModel.class);

        yourNameEdit = findViewById(R.id.editTextYourNameMyProfileEdit);
        educationEdit = findViewById(R.id.editTextEducationMyProfileEdit);
        aboutEdit = findViewById(R.id.editTextAboutMyProfileEdit);
        rcvSkillEdit = findViewById(R.id.editMySkillRecyclerView);
        ibEdit = findViewById(R.id.imageButtonEdit);

        findViewById(R.id.myProfileEditBackButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        fetchHandymanProfile();
        doEdit();
    }

    private void doEdit() {
        ibEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String yourNameEditValue = yourNameEdit.getText().toString();
                String educationEditValue = educationEdit.getText().toString();
                String aboutEditValue = aboutEdit.getText().toString();
                String authorizationCode = sharedPreferencesUtils.get("token", String.class);

                List<SkillEditRequest> skillEditRequestList = new ArrayList<>();

                for (Skill skill : skillEditList ){
                    SkillEditRequest skillEditRequest = new SkillEditRequest(skill.getCategory_id(), skill.getName());
                    skillEditRequestList.add(skillEditRequest);
                }

                HandymanEditRequest handymanEditRequest = new HandymanEditRequest(yourNameEditValue, educationEditValue, aboutEditValue, skillEditRequestList);

                baseViewModel.editHandymanProfile(authorizationCode, handymanEditRequest);
                baseViewModel.getStandardResponseMutableLiveData().observe(MyProfileEdit.this, new Observer<StandardResponse>() {
                    @Override
                    public void onChanged(StandardResponse standardResponse) {
                        Toast.makeText(MyProfileEdit.this, standardResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void fetchHandymanProfile() {
        String authorizationCode = sharedPreferencesUtils.get("token", String.class);
        baseViewModel.fetchHandymanProfile(authorizationCode);
        baseViewModel.getHandymanProfileResponseMutableLiveData().observe(this, new Observer<HandymanProfileResponse>() {
            @Override
            public void onChanged(HandymanProfileResponse handymanProfileResponse) {
                Handyman handyman = handymanProfileResponse.getHandyman();

                yourNameEdit.setText(handyman.getName());
                educationEdit.setText(handyman.getEducation());
                aboutEdit.setText(handyman.getDetail());

//                skillEditList.clear();
//                skillEditList.addAll(handymanProfileResponse.getSkillList());
//                skillEditAdapter.notifyDataSetChanged();
            }
        });
    }
}
