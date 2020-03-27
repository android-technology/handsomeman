package com.tt.handsomeman.request;

public class JobFilterRequest {
    private double lat;

    private double lng;

    private double radius;

    private double budgetMin;

    private double budgetMax;

    private String createTime;

    private Integer categoryId;

    public JobFilterRequest(double lat, double lng, double radius, double budgetMin, double budgetMax, String createTime, Integer categoryId) {
        this.lat = lat;
        this.lng = lng;
        this.radius = radius;
        this.budgetMin = budgetMin;
        this.budgetMax = budgetMax;
        this.createTime = createTime;
        this.categoryId = categoryId;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getBudgetMin() {
        return budgetMin;
    }

    public void setBudgetMin(double budgetMin) {
        this.budgetMin = budgetMin;
    }

    public double getBudgetMax() {
        return budgetMax;
    }

    public void setBudgetMax(double budgetMax) {
        this.budgetMax = budgetMax;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
