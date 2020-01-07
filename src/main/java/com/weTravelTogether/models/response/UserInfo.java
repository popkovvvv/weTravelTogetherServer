package com.weTravelTogether.models.response;

public class UserInfo {

    private final String email;
    private final String name;

    public UserInfo(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }


}
