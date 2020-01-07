package com.weTravelTogether.controllers;

import com.weTravelTogether.models.entities.User;
import com.weTravelTogether.models.exception.InvalidRefreshTokenException;
import com.weTravelTogether.models.request.RefreshToken;
import com.weTravelTogether.models.request.UserLogin;
import com.weTravelTogether.models.request.UserRegistration;
import com.weTravelTogether.models.response.AccessToken;
import com.weTravelTogether.models.response.TokenPair;
import com.weTravelTogether.repos.UserRepository;
import com.weTravelTogether.service.AccountService;
import com.weTravelTogether.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountService accountService;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Вернет JSON с токеном", response = TokenPair.class)
    @PostMapping(path="/registration") // Map ONLY POST Requests
    public TokenPair postRegistration (@ModelAttribute("registration") UserRegistration userRegistration)  throws Exception {
        User user = userService.registerUser(userRegistration);
        return accountService.doLoginUser(user);
    }

    @ApiOperation(value = "Вернет JSON с токеном", response = TokenPair.class)
    @PostMapping(value = "/login")
    public TokenPair tokenPostLogin(@Valid @ModelAttribute("login") UserLogin userLogin) {
        return accountService.loginUser(userLogin);
    }

    @PostMapping(value = "/refresh-token")
    public AccessToken tokenPostRefresh(@Valid @RequestParam RefreshToken refreshToken) {
        return accountService.refreshAccessToken(refreshToken.getRefreshToken())
                .orElseThrow(InvalidRefreshTokenException::new);
    }

    @PostMapping(value = "/logout")
    public void tokenDeleteLogout(@Valid @ModelAttribute("logout") RefreshToken refreshToken) {
        accountService.logoutUser(refreshToken.getRefreshToken());
    }
}