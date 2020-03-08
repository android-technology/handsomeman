package com.tt.handsomeman.response;

import com.tt.handsomeman.model.Category;
import com.tt.handsomeman.model.Job;

import java.util.List;

public class StartScreenHandyman {

    private List<Job> jobList;
    private List<Category> categoryList;

    public StartScreenHandyman(List<Job> jobList, List<Category> categoryList) {
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
