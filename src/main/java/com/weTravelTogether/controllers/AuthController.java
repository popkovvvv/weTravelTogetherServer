package com.weTravelTogether.controllers;

import com.weTravelTogether.models.entities.User;
import com.weTravelTogether.models.exception.InvalidRefreshTokenException;
import com.weTravelTogether.models.request.RefreshToken;
import com.weTravelTogether.models.request.UserLogin;
import com.weTravelTogether.models.request.UserRegistration;
import com.weTravelTogether.models.response.AccessToken;
import com.weTravelTogether.models.response.TokenPair;
import com.weTravelTogether.pogos.MessageRequest;
import com.weTravelTogether.repos.UserRepository;
import com.weTravelTogether.service.AccountService;
import com.weTravelTogether.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public Object postRegistration (@ModelAttribute("registration") UserRegistration userRegistration)  throws Exception {
        if (!userRegistration.getPassword().equals(userRegistration.getPasswordConfirmation())){
            return new MessageRequest("password and password confim dont equals", HttpStatus.BAD_REQUEST.value());
        }
        User user = userService.registerUser(userRegistration);
        return accountService.doLoginUser(user);
    }

    @ApiOperation(value = "Вернет JSON с токеном", response = TokenPair.class)
    @PostMapping(value = "/login")
    public TokenPair tokenPostLogin(@Valid @ModelAttribute("login") UserLogin userLogin) {
        return accountService.loginUser(userLogin);
    }

    @ApiOperation(value = "Вернет JSON с токеном, использовать если токен устарел", response = AccessToken.class)
    @PostMapping(value = "/refresh-token")
    public AccessToken tokenPostRefresh(@Valid @RequestParam RefreshToken refreshToken) {
        return accountService.refreshAccessToken(refreshToken.getRefreshToken())
                .orElseThrow(InvalidRefreshTokenException::new);
    }

    @ApiOperation(value = "Выход пользавателя осущесвтляется удалением рефреш токена," +
            " следовательно нужно удалить на своей стороне access token")
    @PostMapping(value = "/logout")
    public void tokenDeleteLogout(@Valid @ModelAttribute("logout") RefreshToken refreshToken) {
        accountService.logoutUser(refreshToken.getRefreshToken());
    }
}
