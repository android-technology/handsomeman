package com.tt.handsomeman.response;

import com.tt.handsomeman.model.Handyman;
import com.tt.handsomeman.model.Skill;

import java.util.List;

public class HandymanProfileResponse {
    private Handyman handyman;
    private String avatar;
    private List<Skill> skillList;
    private int allProject;
    private int successedProject;
    private long updateDate;

    public Handyman getHandyman() {
        return handyman;
    }

    public void setHandyman(Handyman handyman) {
        this.handyman = handyman;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<Skill> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<Skill> skillList) {
        this.skillList = skillList;
    }

    public int getAllProject() {
        return allProject;
    }

    public void setAllProject(int allProject) {
        this.allProject = allProject;
    }

    public int getSuccessedProject() {
        return successedProject;
    }

    public void setSuccessedProject(int successedProject) {
        this.successedProject = successedProject;
    }

    public long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(long updateDate) {
        this.updateDate = updateDate;
    }
}
