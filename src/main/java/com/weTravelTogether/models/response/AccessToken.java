package com.weTravelTogether.models.response;

public class AccessToken {

    private final String jwt;

    public AccessToken(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

}
