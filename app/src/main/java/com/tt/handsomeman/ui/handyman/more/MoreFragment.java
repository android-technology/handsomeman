package com.tt.handsomeman.ui.handyman.more;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tt.handsomeman.HandymanApp;
import com.tt.handsomeman.R;
import com.tt.handsomeman.adapter.PayoutAdapter;
import com.tt.handsomeman.databinding.FragmentMoreBinding;
import com.tt.handsomeman.model.Handyman;
import com.tt.handsomeman.model.Payout;
import com.tt.handsomeman.ui.BaseFragment;
import com.tt.handsomeman.ui.handyman.Login;
import com.tt.handsomeman.util.CustomDividerItemDecoration;
import com.tt.handsomeman.util.SharedPreferencesUtils;
import com.tt.handsomeman.viewmodel.HandymanViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MoreFragment extends BaseFragment<HandymanViewModel, FragmentMoreBinding> {
    private static final Integer REQUEST_MORE_FRAGMENT_MY_PROFILE_EDIT_RESULT_CODE = 7;

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    SharedPreferencesUtils sharedPreferencesUtils;
    private ConstraintLayout viewMyProfileLayout;
    private TextView tvLogout, tvWalletBalance, tvAccountName;
    private ImageButton ibAddPayout;
    private PayoutAdapter payoutAdapter;
    private List<Payout> payoutList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HandymanApp.getComponent().inject(this);
        baseViewModel = new ViewModelProvider(this, viewModelFactory).get(HandymanViewModel.class);
        viewBinding = FragmentMoreBinding.inflate(inflater, container, false);
        return viewBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewMyProfileLayout = viewBinding.viewMyProfileLayout;
        tvLogout = viewBinding.logoutMore;
        tvWalletBalance = viewBinding.walletBalance;
        tvAccountName = viewBinding.accountNameMore;
        ibAddPayout = viewBinding.imageButtonAddPayout;

        viewMyProfileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(), MyProfile.class), REQUEST_MORE_FRAGMENT_MY_PROFILE_EDIT_RESULT_CODE);
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

        ibAddPayout.setOnClickListener(v -> {
            startActivityForResult(new Intent(getActivity(), AddNewPayout.class), REQUEST_MORE_FRAGMENT_MY_PROFILE_EDIT_RESULT_CODE);
        });

        createPayoutRecyclerView(view);
        fetchHandymanInfo();
    }

    private void fetchHandymanInfo() {
        String authorizationCode = sharedPreferencesUtils.get("token", String.class);
        baseViewModel.fetchHandymanInfo(authorizationCode);
        baseViewModel.getHandymanMutableLiveData().observe(getViewLifecycleOwner(), new Observer<Handyman>() {
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

    private void createPayoutRecyclerView(@NonNull View view) {
        RecyclerView rcvPayout = viewBinding.recyclerViewPayoutMore;
        payoutAdapter = new PayoutAdapter(payoutList, getContext());
        RecyclerView.LayoutManager layoutManagerPayout = new LinearLayoutManager(getContext());
        rcvPayout.setLayoutManager(layoutManagerPayout);
        rcvPayout.setItemAnimator(new DefaultItemAnimator());
        rcvPayout.addItemDecoration(new CustomDividerItemDecoration(getResources().getDrawable(R.drawable.recycler_view_divider)));
        rcvPayout.setAdapter(payoutAdapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == REQUEST_MORE_FRAGMENT_MY_PROFILE_EDIT_RESULT_CODE && resultCode == Activity.RESULT_OK && data != null) {
            if (data.getBooleanExtra("isMoreFragmentEdit", false)) {
                fetchHandymanInfo();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
