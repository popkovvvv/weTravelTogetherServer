package com.weTravelTogether.FormData;

import org.springframework.http.HttpStatus;

public class ErrorRequest {

    private  String message;

    private final HttpStatus status;

    public ErrorRequest(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }

}
