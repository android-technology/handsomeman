package com.tt.handsomeman.response;

public class HandymanResponse {

    private Integer handymanId;
    private String name;
    private String avatar;
    private Float averageReviewPoint;
    private Double distance;

    public Integer getHandymanId() {
        return handymanId;
    }

    public void setHandymanId(Integer handymanId) {
        this.handymanId = handymanId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Float getAverageReviewPoint() {
        return averageReviewPoint;
    }

    public void setAverageReviewPoint(Float averageReviewPoint) {
        this.averageReviewPoint = averageReviewPoint;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
