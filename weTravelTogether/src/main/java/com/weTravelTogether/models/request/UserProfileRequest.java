package com.weTravelTogether.models.request;

public class UserProfileRequest{

    private long id;

    private String name;

    private String username;

    private String surname;

    private String patronymic;

    private int age;

    private String email;

    private String city;

    private boolean visibleGeo;

    public UserProfileRequest() { }

    public UserProfileRequest(long id, String email, String username, boolean visibleGeo) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.visibleGeo = visibleGeo;
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
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

    public boolean isVisibleGeo() {
        return visibleGeo;
    }

    public void setVisibleGeo(boolean visibleGeo) {
        this.visibleGeo = visibleGeo;
    }

}
