package com.tt.handsomeman.ui.customer.find_handyman;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tt.handsomeman.HandymanApp;
import com.tt.handsomeman.R;
import com.tt.handsomeman.adapter.CategoryAdapter;
import com.tt.handsomeman.adapter.FindHandymanAdapter;
import com.tt.handsomeman.databinding.FragmentFindHandymanChildHandymanBinding;
import com.tt.handsomeman.model.Category;
import com.tt.handsomeman.request.NearbyHandymanRequest;
import com.tt.handsomeman.response.HandymanResponse;
import com.tt.handsomeman.ui.BaseFragment;
import com.tt.handsomeman.ui.handyman.jobs.GroupByCategory;
import com.tt.handsomeman.ui.handyman.jobs.YourLocation;
import com.tt.handsomeman.util.Constants;
import com.tt.handsomeman.util.CustomDividerItemDecoration;
import com.tt.handsomeman.util.SharedPreferencesUtils;
import com.tt.handsomeman.viewmodel.CustomerViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

public class FindHandymanChildHandymanFragment extends BaseFragment<CustomerViewModel, FragmentFindHandymanChildHandymanBinding> {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    SharedPreferencesUtils sharedPreferencesUtils;
    private ProgressBar pgFindHandyman, pgCategory;
    private LinearLayout showMoreYourLocation;
    private FindHandymanAdapter findHandymanAdapter;
    private CategoryAdapter categoryAdapter;
    private List<HandymanResponse> handymanResponseList = new ArrayList<>();
    private List<Category> categoryArrayList = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        HandymanApp.getComponent().inject(this);
        baseViewModel = new ViewModelProvider(this, viewModelFactory).get(CustomerViewModel.class);
        viewBinding = FragmentFindHandymanChildHandymanBinding.inflate(inflater, container, false);
        return viewBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pgFindHandyman = viewBinding.progressBarFindHandyman;
        pgCategory = viewBinding.progressBarCategory;
        showMoreYourLocation = viewBinding.showMoreYourLocation;

        createJobRecycleView(view);
        createCategoryRecycleView(view);
        showMoreByYourLocation();

        Constants.Longitude.observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                fetchData(Constants.Latitude.getValue(), aDouble);
            }
        });
    }

    private void showMoreByYourLocation() {
        showMoreYourLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Your Location", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(getActivity(), YourLocation.class));
            }
        });
    }

    private void createJobRecycleView(View view) {
        RecyclerView rcvFindHandyman = viewBinding.recycleViewFindHandyman;
        findHandymanAdapter = new FindHandymanAdapter(getContext(), handymanResponseList);
//        findHandymanAdapter.setOnItemClickListener(new JobAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//                Intent intent = new Intent(getActivity(), JobDetail.class);
//                intent.putExtra("jobId", jobArrayList.get(position).getId());
//                startActivity(intent);
//            }
//        });
        RecyclerView.LayoutManager layoutManagerJob = new LinearLayoutManager(getContext());
        rcvFindHandyman.setLayoutManager(layoutManagerJob);
        rcvFindHandyman.setItemAnimator(new DefaultItemAnimator());
        rcvFindHandyman.addItemDecoration(new CustomDividerItemDecoration(getResources().getDrawable(R.drawable.recycler_view_divider)));
        rcvFindHandyman.setAdapter(findHandymanAdapter);
    }

    private void createCategoryRecycleView(View view) {
        RecyclerView rcvCategory = viewBinding.recycleViewCategories;
        categoryAdapter = new CategoryAdapter(getContext(), categoryArrayList);
        categoryAdapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                String categoryName = categoryArrayList.get(position).getName();
//
//                Intent intent = new Intent(getActivity(), GroupByCategory.class);
//                intent.putExtra("categoryName", categoryName);
//                intent.putExtra("categoryId", categoryArrayList.get(position).getId());
//                startActivity(intent);
            }
        });
        RecyclerView.LayoutManager layoutManagerCategory = new LinearLayoutManager(getContext());
        rcvCategory.setLayoutManager(layoutManagerCategory);
        rcvCategory.setItemAnimator(new DefaultItemAnimator());
        rcvCategory.addItemDecoration(new CustomDividerItemDecoration(getResources().getDrawable(R.drawable.recycler_view_divider)));
        rcvCategory.setAdapter(categoryAdapter);
    }

    private void fetchData(Double lat, Double lng) {
        String authorizationCode = sharedPreferencesUtils.get("token", String.class);
        Calendar now = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ZZ", Locale.getDefault());
        String dateRequest = formatter.format(now.getTime());
        double radius = 10d;

        baseViewModel.fetchDataStartScreen(authorizationCode, new NearbyHandymanRequest(lat, lng, radius, dateRequest));

        baseViewModel.getStartScreenCustomerMutableLiveData().observe(this, data -> {
            pgFindHandyman.setVisibility(View.GONE);
            handymanResponseList.clear();
            handymanResponseList.addAll(data.getHandymanResponsesList());
            findHandymanAdapter.notifyDataSetChanged();

            pgCategory.setVisibility(View.GONE);
            categoryArrayList.clear();
            categoryArrayList.addAll(data.getCategoryList());
            categoryAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public void onStop() {
        Constants.Longitude.removeObservers(this);
        super.onStop();
    }
}
