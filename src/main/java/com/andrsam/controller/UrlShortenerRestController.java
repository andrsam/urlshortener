package com.andrsam.controller;

import com.andrsam.request.OpenAccountRequest;
import com.andrsam.request.RegisterUrlRequest;
import com.andrsam.response.OpenAccountResponse;
import com.andrsam.response.RegisterUrlResponse;
import com.andrsam.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlShortenerRestController {
    private final AccountService accountService;

    @Autowired
    public UrlShortenerRestController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(value = "/account", produces = "application/json;UTF-8")
    public OpenAccountResponse account(@RequestBody OpenAccountRequest accountRequest) {
        String accountId = accountRequest.getAccountId();
        return accountService.save(accountId);
    }

    @PostMapping(value = "/register", produces = "application/json;UTF-8")
    public RegisterUrlResponse register(@RequestBody RegisterUrlRequest registerUrlRequest) {
        return null;
    }


}
