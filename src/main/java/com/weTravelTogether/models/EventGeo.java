package com.weTravelTogether.models;

import javax.persistence.*;

@Entity
@Table
public class EventGeo {

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
    private Event event;

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
        this.latitude = latitude;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
