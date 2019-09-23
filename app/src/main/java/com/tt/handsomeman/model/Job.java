package com.tt.handsomeman.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Job {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("categoryId")
    @Expose
    private Integer categoryId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("createTime")
    @Expose
    private Date createTime;
    @SerializedName("detail")
    @Expose
    private String detail;
    @SerializedName("budgetMin")
    @Expose
    private Integer budgetMin;
    @SerializedName("budgetMax")
    @Expose
    private Integer budgetMax;
    @SerializedName("deadline")
    @Expose
    private Date deadline;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lng")
    @Expose
    private Double lng;
    @SerializedName("bidMin")
    @Expose
    private Integer bidMin;
    @SerializedName("bidMax")
    @Expose
    private Integer bidMax;
    @SerializedName("interviewing")
    @Expose
    private Integer interviewing;
    @SerializedName("handymanId")
    @Expose
    private Integer handymanId;
    @SerializedName("accountId")
    @Expose
    private Integer accountId;
    @SerializedName("status")
    @Expose
    private String status;

    public Job(Date createTime, String title, Integer budgetMin, Integer budgetMax, Date deadline, String location) {
        this.createTime = createTime;
        this.title = title;
        this.budgetMin = budgetMin;
        this.budgetMax = budgetMax;
        this.deadline = deadline;
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getBudgetMin() {
        return budgetMin;
    }

    public void setBudgetMin(Integer budgetMin) {
        this.budgetMin = budgetMin;
    }

    public Integer getBudgetMax() {
        return budgetMax;
    }

    public void setBudgetMax(Integer budgetMax) {
        this.budgetMax = budgetMax;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Integer getBidMin() {
        return bidMin;
    }

    public void setBidMin(Integer bidMin) {
        this.bidMin = bidMin;
    }

    public Integer getBidMax() {
        return bidMax;
    }

    public void setBidMax(Integer bidMax) {
        this.bidMax = bidMax;
    }

    public Integer getInterviewing() {
        return interviewing;
    }

    public void setInterviewing(Integer interviewing) {
        this.interviewing = interviewing;
    }

    public Integer getHandymanId() {
        return handymanId;
    }

    public void setHandymanId(Integer handymanId) {
        this.handymanId = handymanId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
