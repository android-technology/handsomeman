package com.tt.handsomeman.ui.handyman.more;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tt.handsomeman.HandymanApp;
import com.tt.handsomeman.R;
import com.tt.handsomeman.adapter.SkillAdapter;
import com.tt.handsomeman.model.Handyman;
import com.tt.handsomeman.model.Skill;
import com.tt.handsomeman.response.HandymanProfileResponse;
import com.tt.handsomeman.ui.BaseFragment;
import com.tt.handsomeman.util.CustomDividerItemDecoration;
import com.tt.handsomeman.util.SharedPreferencesUtils;
import com.tt.handsomeman.viewmodel.HandymanViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MyProfileAboutFragment extends BaseFragment<HandymanViewModel> {
    private static final Integer REQUEST_MY_PROFILE_RESULT_CODE = 77;

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    SharedPreferencesUtils sharedPreferencesUtils;
    private TextView yourName, education, about, allProjects, successedProject;
    private SkillAdapter skillAdapter;
    private RecyclerView rcvSkill;
    private List<Skill> skillList = new ArrayList<>();
    private boolean isMyProfileEdit = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        HandymanApp.getComponent().inject(this);
        baseViewModel = new ViewModelProvider(this, viewModelFactory).get(HandymanViewModel.class);
        return inflater.inflate(R.layout.fragment_my_profile_about, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        yourName = view.findViewById(R.id.aboutYourName);
        education = view.findViewById(R.id.aboutEducation);
        about = view.findViewById(R.id.about);
        allProjects = view.findViewById(R.id.aboutAllProjects);
        successedProject = view.findViewById(R.id.aboutSuccessedProjects);
        rcvSkill = view.findViewById(R.id.aboutMySkillRecyclerView);

        createSkillRecyclerView();
        fetchHandymanProfile();
    }

    private void createSkillRecyclerView() {
        skillAdapter = new SkillAdapter(skillList, getContext());
        RecyclerView.LayoutManager layoutManagerPayout = new LinearLayoutManager(getContext());
        rcvSkill.setLayoutManager(layoutManagerPayout);
        rcvSkill.setItemAnimator(new DefaultItemAnimator());
        rcvSkill.addItemDecoration(new CustomDividerItemDecoration(getResources().getDrawable(R.drawable.recycler_view_divider)));
        rcvSkill.setAdapter(skillAdapter);
    }

    private void fetchHandymanProfile() {
        String authorizationCode = sharedPreferencesUtils.get("token", String.class);
        baseViewModel.fetchHandymanProfile(authorizationCode);
        baseViewModel.getHandymanProfileResponseMutableLiveData().observe(getViewLifecycleOwner(), new Observer<HandymanProfileResponse>() {
            @Override
            public void onChanged(HandymanProfileResponse handymanProfileResponse) {
                Handyman handyman = handymanProfileResponse.getHandyman();

                yourName.setText(handyman.getName());
                education.setText(handyman.getEducation());
                about.setText(handyman.getDetail());
                allProjects.setText(String.valueOf(handymanProfileResponse.getAllProject()));
                successedProject.setText(String.valueOf(handymanProfileResponse.getSuccessedProject()));

                skillList.clear();
                skillList.addAll(handymanProfileResponse.getSkillList());
                skillAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == REQUEST_MY_PROFILE_RESULT_CODE && resultCode == Activity.RESULT_OK && data != null) {
            if (data.getBooleanExtra("isMyProfileEdit", false)) {
                fetchHandymanProfile();
                isMyProfileEdit = true;
                MyProfile myProfile = (MyProfile) getActivity();
                myProfile.setEditResult(isMyProfileEdit);
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
