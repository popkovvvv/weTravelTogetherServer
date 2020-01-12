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

    @NotBlank
    @ValidPassword
    private final String passwordConfirmation;

    public UserRegistration(@Email String email, @NotBlank String password, @NotBlank String passwordConfirmation) {
        this.email = email;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }
}
