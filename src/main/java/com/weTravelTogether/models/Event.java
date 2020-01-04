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
}
