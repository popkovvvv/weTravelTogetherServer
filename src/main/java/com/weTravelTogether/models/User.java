package com.weTravelTogether.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User{

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

    @NotNull(message = "password cannot be null")
    @JsonIgnore
    @Column(length = 64, nullable = false)
    private String password;

    @JsonIgnore
    @Column(length = 64)
    private String patronymic;

    @JsonIgnore
    @Column
    private int age;

    @NotNull(message = "email cannot be null")
    @Column(length = 64, nullable = false)
    private String email;

    @JsonIgnore
    @Column
    private String city;

    @Column
    private boolean visibleGeo;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Event> events = new ArrayList<>();

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

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public boolean isVisibleGeo() {
        return visibleGeo;
    }

    public void setVisibleGeo(boolean visibleGeo) {
        this.visibleGeo = visibleGeo;
    }


}
