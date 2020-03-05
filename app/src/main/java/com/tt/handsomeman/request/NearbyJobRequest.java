package com.tt.handsomeman.request;

public class NearbyJobRequest {
    private Double lat;

    private Double lng;

    private double radius;

    private String dateRequest;

    public NearbyJobRequest(Double lat, Double lng, double radius) {
        this.lat = lat;
        this.lng = lng;
        this.radius = radius;
    }

    public NearbyJobRequest(Double lat, Double lng, double radius, String dateRequest) {
        this.lat = lat;
        this.lng = lng;
        this.radius = radius;
        this.dateRequest = dateRequest;
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

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
