package com.weTravelTogether.controllers;

import com.weTravelTogether.models.Account;
import com.weTravelTogether.repos.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/account")
public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public Account addNewUser (@RequestParam String email, @RequestParam String password) {

        Account account = new Account();
        account.setEmail(email);
        account.setPassword(password);
        accountRepository.save(account);

        return account;
    }
}
