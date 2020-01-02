package com.weTravelTogether.models;

import javax.persistence.*;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column(length = 64, nullable = false)
    private String password;

    @Column(length = 64)
    private String patronymic;

    @Column
    private int age;

    @Column(length = 64, nullable = false)
    private String email;

    @Column
    private String city;

    public Account() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
