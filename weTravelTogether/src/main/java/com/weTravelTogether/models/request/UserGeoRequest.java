package com.weTravelTogether.models.request;

public class UserGeoRequest {

    private String city;
    private String region;
    private double longitude;
    private double latitude;

    public UserGeoRequest(String city, String region, double longitude, double latitude) {
        this.city = city;
        this.region = region;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public UserGeoRequest(){ }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
