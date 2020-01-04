package com.weTravelTogether.controllers;

import com.weTravelTogether.pogos.ErrorRequest;
import com.weTravelTogether.models.Account;
import com.weTravelTogether.pogos.UserProfile;
import com.weTravelTogether.repos.AccountRepository;
import com.weTravelTogether.pogos.JwtRequest;
import com.weTravelTogether.security.JwtTokenUtil;
import com.weTravelTogether.security.JwtUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


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
    public Object registration (@RequestParam String email, @RequestParam String password) throws Exception {

        Account account = new Account();
        account.setEmail(email);
        account.setPassword(passwordEncoder.encode(password));
        accountRepository.save(account);

        return jwtTokenUtil.authenticateJwt(email, password, "Registration");
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

    @RequestMapping(value = "/account/profile/update", method = RequestMethod.POST)
    public ErrorRequest postUpdateProfile(@ModelAttribute("formUpdate") UserProfile profile,
                                          HttpServletRequest request) throws Exception {

        UserDetails userDetails = (UserDetails) jwtTokenUtil.getUserDetailsByToken(request);
        Optional<Account> accountOptional =  accountRepository.findByEmail(userDetails.getUsername());
        Account account = accountOptional.get();

        account.setName(profile.getName());
        account.setSurname(profile.getSurname());
        account.setUsername(profile.getUsername());
        account.setCity(profile.getCity());
        account.setPatronymic(profile.getPatronymic());
        account.setAge(profile.getAge());

        accountRepository.save(account);

        return new ErrorRequest("update ok", HttpStatus.OK);
    }

        @GetMapping(value = "/account/profile")
        public UserProfile getAccountProfile(HttpServletRequest request) throws Exception {

            UserDetails userDetails = (UserDetails) jwtTokenUtil.getUserDetailsByToken(request);
            Optional<Account> accountOptional =  accountRepository.findByEmail(userDetails.getUsername());
            Account account = accountOptional.get();

            UserProfile userProfile = new UserProfile();
            userProfile.setId(account.getId());
            userProfile.setName(account.getName());
            userProfile.setSurname(account.getSurname());
            userProfile.setUsername(account.getUsername());
            userProfile.setEmail(account.getEmail());
            userProfile.setCity(account.getCity());
            userProfile.setPatronymic(account.getPatronymic());
            userProfile.setAge(account.getAge());


            return userProfile;

    }


}
