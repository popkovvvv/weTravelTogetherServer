package com.weTravelTogether.pogos;

import org.springframework.http.HttpStatus;

import java.io.Serializable;
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;

    private final String jwtToken;

    private final HttpStatus status;

    private final String message;

    public JwtResponse(String jwtToken, HttpStatus status, String message) {
        this.jwtToken = jwtToken;
        this.status = status;
        this.message = message;
    }

    public String getToken() {
        return this.jwtToken;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}