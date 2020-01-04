package com.weTravelTogether.controllers;

import com.weTravelTogether.models.Account;
import com.weTravelTogether.pogos.UserGeoRequest;
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
    AccountRepository accountRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping(path="/account/update/geo") // Map ONLY POST Requests
    public Account postUpdateGeo (HttpServletRequest httpServletRequest,
                                      @ModelAttribute("userGeoRequest") UserGeoRequest userGeoRequest) throws Exception {

        UserDetails userDetails = (UserDetails) jwtTokenUtil.getUserDetailsByToken(httpServletRequest);
        Optional<Account> accountOptional =  accountRepository.findByEmail(userDetails.getUsername());

        Account account = accountOptional.get();
        account.setCityGeo(userGeoRequest.getCity());
        account.setRegionGeo(userGeoRequest.getRegion());
        account.setLatitude(userGeoRequest.getLatitude());
        account.setLongitude(userGeoRequest.getLongitude());

        accountRepository.save(account);

        return account;

    }
}
