package com.weTravelTogether.pogos;

import org.springframework.http.HttpStatus;

public class MessageRequest {

    private  String message;

    private int status;

    public MessageRequest(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

}
