package com.weTravelTogether.service;

import com.weTravelTogether.models.entities.User;
import com.weTravelTogether.models.entities.UserRefreshToken;
import com.weTravelTogether.models.exception.UserNotFoundException;
import com.weTravelTogether.models.exception.WrongPasswordException;
import com.weTravelTogether.models.request.UserLogin;
import com.weTravelTogether.models.response.AccessToken;
import com.weTravelTogether.models.response.TokenPair;
import com.weTravelTogether.repos.UserRefreshTokenRepository;
import com.weTravelTogether.repos.UserRepository;
import com.weTravelTogether.security.JWTAuthorizationFilter;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {


    @Autowired
    UserRefreshTokenRepository userRefreshTokenRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JWTAuthorizationFilter jwtAuthorizationFilter;

    @Autowired
    public AccountService(UserRefreshTokenRepository userRefreshTokenRepository,
                          UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          JWTAuthorizationFilter jwtAuthorizationFilter) {
        this.userRefreshTokenRepository = userRefreshTokenRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
    }

    public TokenPair doLoginUser(User user) {
        String jwt = jwtAuthorizationFilter.generateAccessToken(user.getId());
        String refreshToken = createRefreshToken(user);
        return new TokenPair(jwt, refreshToken);
    }

    /**
     * @return newly generated access token or nothing, if the refresh token is not valid
     */
    public Optional<AccessToken> refreshAccessToken(String refreshToken) {
        return userRefreshTokenRepository.findByToken(refreshToken)
                .map(userRefreshToken -> new AccessToken(
                        jwtAuthorizationFilter.generateAccessToken(userRefreshToken.getUser().getId())
                ));
    }

    private String createRefreshToken(User user) {
        String token = RandomStringUtils.randomAlphanumeric(128);
        userRefreshTokenRepository.save(new UserRefreshToken(token, user));
        return token;

    }

    public TokenPair loginUser(UserLogin userLogin) {
        return userRepository.findByEmail(userLogin.getEmail())
                .map(user -> {
                    if (passwordEncoder.matches(userLogin.getPassword(), user.getPassword())) {
                        return doLoginUser(user);
                    } else {
                        throw new WrongPasswordException();
                    }
                })
                .orElseThrow(UserNotFoundException::new);
    }

    public void logoutUser(String refreshToken) {
        userRefreshTokenRepository.findByToken(refreshToken)
                .ifPresent(userRefreshTokenRepository::delete);
    }
}