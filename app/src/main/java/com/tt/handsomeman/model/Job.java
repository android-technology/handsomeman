package com.tt.handsomeman.model;

import com.tt.handsomeman.HandymanApp;
import com.tt.handsomeman.R;
import com.tt.handsomeman.util.TimeCount;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Job implements Serializable {

    private Integer id;
    private Integer categoryId;
    private String title;
    private Date createTime;
    private String detail;
    private Integer budgetMin;
    private Integer budgetMax;
    private Date deadline;
    private String location;
    private Double lat;
    private Double lng;
    private Integer customerId;

    public Integer setCreateTimeBinding() {
        Calendar now = Calendar.getInstance();
        return TimeCount.getHoursBetween(this.createTime, now.getTime());
    }

    public String setBudgetRange() {
        return HandymanApp.getInstance().getString(R.string.budget_range, this.budgetMin, this.budgetMax);
    }

    public Integer setDeadlineBinding() {
        Calendar now = Calendar.getInstance();
        return Math.max(TimeCount.getDaysBetween(now.getTime(), this.deadline), 0);
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

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
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

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
