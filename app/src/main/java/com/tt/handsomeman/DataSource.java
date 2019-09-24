package com.tt.handsomeman;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.tt.handsomeman.model.Job;

public class DataSource extends PageKeyedDataSource<Integer, Job> {
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Job> callback) {

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Job> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Job> callback) {

    }
}
