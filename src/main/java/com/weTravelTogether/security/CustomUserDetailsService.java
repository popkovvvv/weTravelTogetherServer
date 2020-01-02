package com.weTravelTogether.security;

import com.weTravelTogether.models.Account;
import com.weTravelTogether.repos.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        // Let people login with either username or email
        Account user;
        try {
            user = accountRepository.findByEmail(email);
        } catch (UsernameNotFoundException e) {
            throw e;

        }

        return UserPrincipal.create(user);
    }

    // This method is used by JWTAuthenticationFilter
    @Transactional
    public UserDetails loadUserById(Long id) {
        long userId = id;
        Account user;
        try {
            user =  accountRepository.findById(userId);
        } catch (UsernameNotFoundException e) {
            throw e;
        }

        return UserPrincipal.create(user);
    }
}

