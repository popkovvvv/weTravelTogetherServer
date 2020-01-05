package com.weTravelTogether.pogos;

import com.weTravelTogether.models.User;
import com.weTravelTogether.pogos.interfaces.PojoInt;

public class UserProfile implements PojoInt<UserProfile,User> {

    private long id;

    private String name;

    private String username;

    private String surname;

    private String patronymic;

    private int age;

    private String email;

    private String city;

    private boolean visibleGeo;

    public UserProfile() { }

    public UserProfile(long id, String name, String surname, String patronymic, String email, String username, String city, int age, boolean visibleGeo) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.email = email;
        this.username = username;
        this.city = city;
        this.age = age;
        this.visibleGeo = visibleGeo;
    }

    @Override
    public UserProfile create(User user) {
        return new UserProfile(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getPatronymic(),
                user.getEmail(),
                user.getUsername(),
                user.getCity(),
                user.getAge(),
                user.isVisibleGeo()
        );    }

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
