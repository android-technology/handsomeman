package com.tt.handsomeman.request;

import java.util.List;

public class HandymanEditRequest {
    private String name;

    private String education;

    private String details;

    private List<SkillEditRequest> skillList;

    public HandymanEditRequest(String name, String education, String details, List<SkillEditRequest> skillList) {
        this.name = name;
        this.education = education;
        this.details = details;
        this.skillList = skillList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public List<SkillEditRequest> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<SkillEditRequest> skillList) {
        this.skillList = skillList;
    }
}
