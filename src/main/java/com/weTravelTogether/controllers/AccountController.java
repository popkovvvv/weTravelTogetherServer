package com.weTravelTogether.controllers;

import com.weTravelTogether.FormData.ErrorRequest;
import com.weTravelTogether.models.Account;
import com.weTravelTogether.repos.AccountRepository;
import com.weTravelTogether.FormData.JwtRequest;
import com.weTravelTogether.FormData.JwtResponse;
import com.weTravelTogether.security.JwtTokenUtil;
import com.weTravelTogether.security.JwtUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    public AccountController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @PostMapping(path="/registration") // Map ONLY POST Requests
    public Object registration (@RequestParam String username, @RequestParam String password) throws Exception {

        Account account = new Account();
        account.setUsername(username);
        account.setPassword(passwordEncoder.encode(password));
        accountRepository.save(account);

        return jwtTokenUtil.authenticateJwt(username, password, "Registration");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object createAuthenticationToken(@ModelAttribute("formLogin") JwtRequest authenticationRequest,
                                                 HttpServletRequest request) throws Exception {

        boolean checkAuth = jwtTokenUtil.checkAuthenticate(request, authenticationRequest.getUsername());
        if (checkAuth) {
            return new ErrorRequest("Auth is true", HttpStatus.BAD_REQUEST);
        }

        return jwtTokenUtil.authenticateJwt(authenticationRequest.getUsername(), authenticationRequest.getPassword(), "Login");
    }


}
