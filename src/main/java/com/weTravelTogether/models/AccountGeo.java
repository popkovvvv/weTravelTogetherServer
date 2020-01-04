package com.weTravelTogether.models;

import javax.persistence.*;

import javax.persistence.Entity;

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
    private double latitude;

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
        return latitude;
    }

    public void setLatitude(double latitude) {
        latitude = latitude;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
