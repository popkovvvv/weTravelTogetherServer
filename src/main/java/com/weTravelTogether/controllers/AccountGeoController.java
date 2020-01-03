package com.weTravelTogether.controllers;

import com.weTravelTogether.models.Account;
import com.weTravelTogether.models.AccountGeo;
import com.weTravelTogether.repos.AccountGeoRepository;
import com.weTravelTogether.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(name = "/account")
public class AccountGeoController {

    @Autowired
    AccountGeoRepository accountGeoRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping(path="/geo") // Map ONLY POST Requests
    public AccountGeo getCurrentGeo (HttpServletRequest httpServletRequest) throws Exception {

        UserDetails userDetails = (UserDetails) jwtTokenUtil.getUserDetailsByToken(httpServletRequest);

        return accountGeoRepository.getAccountGeoByAccount_Username(userDetails.getUsername());

    }
}
