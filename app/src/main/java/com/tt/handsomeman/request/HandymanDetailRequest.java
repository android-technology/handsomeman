package com.tt.handsomeman.request;

public class HandymanDetailRequest {
    private double lat;
    private double lng;
    private Integer handymanId;

    public HandymanDetailRequest(double lat, double lng, Integer handymanId) {
        this.lat = lat;
        this.lng = lng;
        this.handymanId = handymanId;
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

    public Integer getHandymanId() {
        return handymanId;
    }

    public void setHandymanId(Integer handymanId) {
        this.handymanId = handymanId;
    }
}
