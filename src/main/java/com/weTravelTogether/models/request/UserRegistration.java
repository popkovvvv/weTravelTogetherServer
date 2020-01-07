package com.weTravelTogether.models.request;

import com.weTravelTogether.models.validation.ValidPassword;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserRegistration {

    @Email
    private final String email;

    @NotBlank
    @ValidPassword
    private final String password;

    public UserRegistration(@Email String email, @NotBlank String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
