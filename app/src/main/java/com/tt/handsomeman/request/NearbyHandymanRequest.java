package com.tt.handsomeman.request;

public class NearbyHandymanRequest {
    private double lat;

    private double lng;

    private double radius;

    private String dateRequest;

    public NearbyHandymanRequest(double lat, double lng, double radius, String dateRequest) {
        this.lat = lat;
        this.lng = lng;
        this.radius = radius;
        this.dateRequest = dateRequest;
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

    public String getDateRequest() {
        return dateRequest;
    }

    public void setDateRequest(String dateRequest) {
        this.dateRequest = dateRequest;
    }
}