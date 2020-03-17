package com.tt.handsomeman.response;


import com.tt.handsomeman.model.Job;

import java.util.List;

public class MyProjectList {
    private List<Job> inProgressList;
    private List<Job> inPastList;

    public List<Job> getInProgressList() {
        return inProgressList;
    }

    public void setInProgressList(List<Job> inProgressList) {
        this.inProgressList = inProgressList;
    }

    public List<Job> getInPastList() {
        return inPastList;
    }

    public void setInPastList(List<Job> inPastList) {
        this.inPastList = inPastList;
    }
}
