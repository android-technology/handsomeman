package com.tt.handsomeman.ui.handyman.more;

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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tt.handsomeman.HandymanApp;
import com.tt.handsomeman.R;
import com.tt.handsomeman.adapter.PayoutAdapter;
import com.tt.handsomeman.model.Handyman;
import com.tt.handsomeman.model.Payout;
import com.tt.handsomeman.ui.BaseFragment;
import com.tt.handsomeman.ui.handyman.Login;
import com.tt.handsomeman.util.CustomDividerItemDecoration;
import com.tt.handsomeman.util.SharedPreferencesUtils;
import com.tt.handsomeman.viewmodel.MoreViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MoreFragment extends BaseFragment<MoreViewModel> {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    SharedPreferencesUtils sharedPreferencesUtils;
    private ConstraintLayout viewMyProfileLayout;
    private TextView tvLogout, tvWalletBalance, tvAccountName;
    private PayoutAdapter payoutAdapter;
    private List<Payout> payoutList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HandymanApp.getComponent().inject(this);
        baseViewModel = ViewModelProviders.of(this, viewModelFactory).get(MoreViewModel.class);
        return inflater.inflate(R.layout.fragment_more, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewMyProfileLayout = view.findViewById(R.id.viewMyProfileLayout);
        tvLogout = view.findViewById(R.id.logoutMore);
        tvWalletBalance = view.findViewById(R.id.walletBalance);
        tvAccountName = view.findViewById(R.id.accountNameMore);

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

        RecyclerView rcvPayout = view.findViewById(R.id.recyclerViewPayoutMore);
        payoutAdapter = new PayoutAdapter(payoutList, getContext());
        RecyclerView.LayoutManager layoutManagerPayout = new LinearLayoutManager(getContext());
        rcvPayout.setLayoutManager(layoutManagerPayout);
        rcvPayout.setItemAnimator(new DefaultItemAnimator());
        rcvPayout.addItemDecoration(new CustomDividerItemDecoration(getResources().getDrawable(R.drawable.recycler_view_divider)));
        rcvPayout.setAdapter(payoutAdapter);

        String authorizationCode = sharedPreferencesUtils.get("token", String.class);
        baseViewModel.getHandymanInfo(authorizationCode);
        baseViewModel.getHandymanMutableLiveData().observe(this, new Observer<Handyman>() {
            @Override
            public void onChanged(Handyman handyman) {
                tvAccountName.setText(handyman.getName());
                tvWalletBalance.setText("$" + handyman.getWallet().getBalance());

                payoutList.clear();
                payoutList.addAll(handyman.getAccount().getPayoutList());
                payoutAdapter.notifyDataSetChanged();
            }
        });
    }
}
