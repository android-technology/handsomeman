package com.tt.handsomeman.ui.handyman.more;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import com.tt.handsomeman.util.CustomDividerItemDecoration;
import com.tt.handsomeman.util.DeleteItemDialog;
import com.tt.handsomeman.util.SharedPreferencesUtils;
import com.tt.handsomeman.util.StatusConstant;
import com.tt.handsomeman.viewmodel.HandymanViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MyProfileEdit extends BaseAppCompatActivity<HandymanViewModel> {
    private static final Integer REQUEST_MY_PROFILE_EDIT_RESULT_CODE = 777;

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    SharedPreferencesUtils sharedPreferencesUtils;
    private TextView yourNameEdit, educationEdit, aboutEdit;
    private ImageButton ibEdit, ibAddSkillMyProfileEdit;
    private SkillEditAdapter skillEditAdapter;
    private RecyclerView rcvSkillEdit;
    private List<Skill> skillEditList = new ArrayList<>();
    private boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_edit);
        HandymanApp.getComponent().inject(this);
        baseViewModel = new ViewModelProvider(this, viewModelFactory).get(HandymanViewModel.class);

        yourNameEdit = findViewById(R.id.editTextYourNameMyProfileEdit);
        educationEdit = findViewById(R.id.editTextEducationMyProfileEdit);
        aboutEdit = findViewById(R.id.editTextAboutMyProfileEdit);
        rcvSkillEdit = findViewById(R.id.editMySkillRecyclerView);
        ibEdit = findViewById(R.id.imageButtonCheckEdit);
        ibAddSkillMyProfileEdit = findViewById(R.id.imageButtonAddSkillMyProfileEdit);

        findViewById(R.id.myProfileEditBackButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ibAddSkillMyProfileEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MyProfileEdit.this, AddNewSkill.class), REQUEST_MY_PROFILE_EDIT_RESULT_CODE);
            }
        });

        createRecyclerView();
        fetchHandymanProfile();
        doEdit();
    }

    private void createRecyclerView() {
        skillEditAdapter = new SkillEditAdapter(skillEditList, this);
        skillEditAdapter.setOnItemClickListener(new SkillEditAdapter.OnItemClickListener() {
            @Override
            public void deleteSkill(int position) {
                new DeleteItemDialog(MyProfileEdit.this, R.style.PauseDialog, HandymanApp.getInstance().getString(R.string.sure_to_delete_skill), new DeleteItemDialog.OnItemClickListener() {
                    @Override
                    public void onItemClickYes() {
                        skillEditList.remove(position);
                        skillEditAdapter.notifyItemRemoved(position);
                    }
                }).show();
            }
        });
        RecyclerView.LayoutManager layoutManagerJob = new LinearLayoutManager(this);
        rcvSkillEdit.setLayoutManager(layoutManagerJob);
        rcvSkillEdit.setItemAnimator(new DefaultItemAnimator());
        rcvSkillEdit.addItemDecoration(new CustomDividerItemDecoration(getResources().getDrawable(R.drawable.recycler_view_divider)));
        rcvSkillEdit.setAdapter(skillEditAdapter);
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

                for (Skill skill : skillEditList) {
                    SkillEditRequest skillEditRequest = new SkillEditRequest(skill.getCategory_id(), skill.getName());
                    skillEditRequestList.add(skillEditRequest);
                }

                HandymanEditRequest handymanEditRequest = new HandymanEditRequest(yourNameEditValue, educationEditValue, aboutEditValue, skillEditRequestList);

                baseViewModel.editHandymanProfile(authorizationCode, handymanEditRequest);
                baseViewModel.getStandardResponseMutableLiveData().observe(MyProfileEdit.this, new Observer<StandardResponse>() {
                    @Override
                    public void onChanged(StandardResponse standardResponse) {
                        Toast.makeText(MyProfileEdit.this, standardResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        if (standardResponse.getStatus().equals(StatusConstant.OK)) {
                            isEdit = true;
                            Intent intent = new Intent();
                            intent.putExtra("isMyProfileEdit", isEdit);
                            setResult(RESULT_OK, intent);
                            finish();
                        }
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

                skillEditList.clear();
                skillEditList.addAll(handymanProfileResponse.getSkillList());
                skillEditAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == REQUEST_MY_PROFILE_EDIT_RESULT_CODE && resultCode == RESULT_OK && data != null) {
            skillEditList.add((Skill) data.getSerializableExtra("skillAdded"));
            skillEditAdapter.notifyItemInserted(skillEditList.size());
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
