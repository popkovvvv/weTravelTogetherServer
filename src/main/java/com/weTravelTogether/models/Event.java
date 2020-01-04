package com.weTravelTogether.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String title;

    @OneToMany(mappedBy = "event")
    private List<Account> accountsList = new ArrayList<>();

    @Column
    private String city;

    @Column
    private String region;

    @Column
    private double longitude;

    @Column
    private double latitude;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Account> getAccountsList() {
        return accountsList;
    }

    public void setAccountsList(List<Account> accountsList) {
        this.accountsList = accountsList;
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
        this.latitude = latitude;
    }
}