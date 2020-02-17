package com.tt.handsomeman.request;

public class NearbyJobRequest {
    private double lat;

    private double lng;

    private double radius;

    public NearbyJobRequest(double lat, double lng, double radius) {
        this.lat = lat;
        this.lng = lng;
        this.radius = radius;
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
}
