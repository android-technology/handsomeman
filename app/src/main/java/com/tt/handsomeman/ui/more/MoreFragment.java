package com.tt.handsomeman.ui.more;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.tt.handsomeman.HandymanApp;
import com.tt.handsomeman.R;
import com.tt.handsomeman.ui.Login;
import com.tt.handsomeman.util.SharedPreferencesUtils;

import javax.inject.Inject;

public class MoreFragment extends Fragment {

    @Inject
    SharedPreferencesUtils sharedPreferencesUtils;
    private ConstraintLayout viewMyProfileLayout;
    private TextView tvLogout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HandymanApp.getComponent().inject(this);
        return inflater.inflate(R.layout.fragment_more, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewMyProfileLayout = view.findViewById(R.id.viewMyProfileLayout);
        tvLogout = view.findViewById(R.id.logoutMore);

        viewMyProfileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MyProfile.class));
            }
        });

        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferencesUtils.clear();
                startActivity(new Intent(getActivity(), Login.class));
                getActivity().finish();
            }
        });
    }
}
