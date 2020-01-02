package com.weTravelTogether.controllers;

import com.weTravelTogether.models.Account;
import com.weTravelTogether.repos.AccountRepository;
import com.weTravelTogether.security.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public AccountController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @PostMapping(path="/registration") // Map ONLY POST Requests
    public Account addNewUser (@RequestParam String username,
                               @RequestParam String password) {

        Account account = new Account();
        account.setUsername(username);
        account.setPassword(passwordEncoder.encode(password));
        accountRepository.save(account);

        return account;
    }

    @PostMapping("/login")
    public  String authenticateUser(@RequestParam String username, @RequestParam String password) {
        logger.info("Logging a user with username: " + username);

        Authentication authenticate;
        try {
            authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            SecurityContextHolder.getContext().setAuthentication(authenticate);
        }
        catch (AuthenticationException e) {
            throw e;
        }

        return tokenProvider.generateToken(authenticate);
    }

}
