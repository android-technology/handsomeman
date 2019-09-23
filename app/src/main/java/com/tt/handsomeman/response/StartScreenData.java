package com.tt.handsomeman.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tt.handsomeman.model.Category;
import com.tt.handsomeman.model.Job;

import java.util.List;

public class StartScreenData {

    @SerializedName("jobList")
    @Expose
    private List<Job> jobList;
    @SerializedName("categoryList")
    @Expose
    private List<Category> categoryList;

    public StartScreenData(List<Job> jobList, List<Category> categoryList) {
        this.jobList = jobList;
        this.categoryList = categoryList;
    }

    public List<Job> getJobList() {
        return jobList;
    }

    public void setJobList(List<Job> jobList) {
        this.jobList = jobList;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

}
