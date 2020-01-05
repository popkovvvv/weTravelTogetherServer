package com.weTravelTogether.models;

import javax.persistence.*;

import javax.persistence.Entity;

@Entity
@Table(name = "user_geos")
public class UserGeo {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String city;

    @Column
    private String region;

    @Column
    private double longitude;

    @Column
    private double latitude;

    @ManyToOne(optional = false)
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        return latitude;
    }

    public void setLatitude(double latitude) {
        latitude = latitude;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
