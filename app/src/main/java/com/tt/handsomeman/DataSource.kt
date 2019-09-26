package com.tt.handsomeman

import androidx.paging.PageKeyedDataSource

import com.tt.handsomeman.model.Job

class DataSource : PageKeyedDataSource<Int, Job>() {
    override fun loadInitial(params: PageKeyedDataSource.LoadInitialParams<Int>, callback: PageKeyedDataSource.LoadInitialCallback<Int, Job>) {

    }

    override fun loadBefore(params: PageKeyedDataSource.LoadParams<Int>, callback: PageKeyedDataSource.LoadCallback<Int, Job>) {

    }

    override fun loadAfter(params: PageKeyedDataSource.LoadParams<Int>, callback: PageKeyedDataSource.LoadCallback<Int, Job>) {

    }
}
