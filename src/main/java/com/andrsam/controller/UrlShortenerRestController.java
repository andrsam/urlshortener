package com.andrsam.controller;

import com.andrsam.request.OpenAccountRequest;
import com.andrsam.response.OpenAccountResponse;
import com.andrsam.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlShortenerRestController {

    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/")
    public String welcome() {
        return "Hello";
    }

    @PostMapping(value = "/account", produces = "application/json;UTF-8")
    public OpenAccountResponse account(@RequestBody OpenAccountRequest accountRequest) {
        String accountId = accountRequest.getAccountId();
        return accountService.save(accountId);
    }
}
