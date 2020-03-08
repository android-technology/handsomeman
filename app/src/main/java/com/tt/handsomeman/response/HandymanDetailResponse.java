package com.tt.handsomeman.response;

import com.tt.handsomeman.model.Skill;

import java.util.List;

public class HandymanDetailResponse {

    private Integer handymanId;
    private String handymanName;
    private String avatar;
    private Double distance;
    private Integer allProject;
    private Integer successedProject;
    private String education;
    private String detail;
    private List<Skill> skillList;
    private Integer countReviewers;
    private Double averageReviewPoint;
    private List<HandymanReviewResponse> handymanReviewResponseList;

    public Integer getHandymanId() {
        return handymanId;
    }

    public void setHandymanId(Integer handymanId) {
        this.handymanId = handymanId;
    }

    public String getHandymanName() {
        return handymanName;
    }

    public void setHandymanName(String handymanName) {
        this.handymanName = handymanName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Integer getAllProject() {
        return allProject;
    }

    public void setAllProject(Integer allProject) {
        this.allProject = allProject;
    }

    public Integer getSuccessedProject() {
        return successedProject;
    }

    public void setSuccessedProject(Integer successedProject) {
        this.successedProject = successedProject;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public List<Skill> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<Skill> skillList) {
        this.skillList = skillList;
    }

    public Integer getCountReviewers() {
        return countReviewers;
    }

    public void setCountReviewers(Integer countReviewers) {
        this.countReviewers = countReviewers;
    }

    public Double getAverageReviewPoint() {
        return averageReviewPoint;
    }

    public void setAverageReviewPoint(Double averageReviewPoint) {
        this.averageReviewPoint = averageReviewPoint;
    }

    public List<HandymanReviewResponse> getHandymanReviewResponseList() {
        return handymanReviewResponseList;
    }

    public void setHandymanReviewResponseList(List<HandymanReviewResponse> handymanReviewResponseList) {
        this.handymanReviewResponseList = handymanReviewResponseList;
    }
}
