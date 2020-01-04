package com.weTravelTogether.controllers;

import com.weTravelTogether.models.Account;
import com.weTravelTogether.models.AccountGeo;
import com.weTravelTogether.pogos.UserGeoRequest;
import com.weTravelTogether.repos.AccountGeoRepository;
import com.weTravelTogether.repos.AccountRepository;
import com.weTravelTogether.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
public class AccountGeoController {

    @Autowired
    AccountGeoRepository accountGeoRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping(path="/account/geo") // Map ONLY POST Requests
    public AccountGeo getCurrentGeo (HttpServletRequest httpServletRequest) throws Exception {

        UserDetails userDetails = (UserDetails) jwtTokenUtil.getUserDetailsByToken(httpServletRequest);

        return accountGeoRepository.getAccountGeoByAccount_EmailOrderByIdDesc(userDetails.getUsername());

    }

    @PostMapping(path="/account/geo") // Map ONLY POST Requests
    public AccountGeo postCurrentGeo (HttpServletRequest httpServletRequest,
                                      @ModelAttribute("userGeoRequest") UserGeoRequest userGeoRequest) throws Exception {

        UserDetails userDetails = (UserDetails) jwtTokenUtil.getUserDetailsByToken(httpServletRequest);

        Optional<Account> account =  accountRepository.findByEmail(userDetails.getUsername());

        AccountGeo accountGeo = new AccountGeo();
        accountGeo.setAccount(account.get());
        accountGeo.setCity(userGeoRequest.getCity());
        accountGeo.setRegion(userGeoRequest.getRegion());
        accountGeo.setLatitude(userGeoRequest.getLatitude());
        accountGeo.setLongitude(userGeoRequest.getLongitude());

        accountGeoRepository.save(accountGeo);

        return accountGeo;

    }
}
