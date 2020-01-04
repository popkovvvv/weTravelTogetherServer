package com.weTravelTogether.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue
    private long id;

    @JsonIgnore
    @Column
    private String name;

    @Column()
    private String username;

    @JsonIgnore
    @Column
    private String surname;

    @JsonIgnore
    @Column(length = 64, nullable = false)
    private String password;

    @JsonIgnore
    @Column(length = 64)
    private String patronymic;

    @JsonIgnore
    @Column
    private int age;

    @Column(length = 64, nullable = false)
    private String email;

    @JsonIgnore
    @Column
    private String city;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Event event;

    @Column
    private String cityGeo;

    @Column
    private String regionGeo;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getCityGeo() {
        return cityGeo;
    }

    public void setCityGeo(String cityGeo) {
        this.cityGeo = cityGeo;
    }

    public String getRegionGeo() {
        return regionGeo;
    }

    public void setRegionGeo(String regionGeo) {
        this.regionGeo = regionGeo;
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
