package com.weTravelTogether.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class AccountGeo {

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
    private double Latitude;

    @ManyToOne(optional = false)
    private Account account;

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
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }
}
