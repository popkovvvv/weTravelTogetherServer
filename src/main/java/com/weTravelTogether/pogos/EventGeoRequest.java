package com.weTravelTogether.pogos;

public class EventGeoRequest {

    private String title;
    private String city;
    private String region;
    private double longitude;
    private double Latitude;


    public EventGeoRequest(String city, String region, double longitude, double latitude) {
        this.city = city;
        this.region = region;
        this.longitude = longitude;
        Latitude = latitude;
    }

    public EventGeoRequest(){ }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }
}
