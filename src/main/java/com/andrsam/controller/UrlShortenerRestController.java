package com.andrsam.controller;

import com.andrsam.request.OpenAccountRequest;
import com.andrsam.request.RegisterUrlRequest;
import com.andrsam.response.OpenAccountResponse;
import com.andrsam.response.RegisterUrlResponse;
import com.andrsam.service.account.AccountService;
import com.andrsam.service.register.RegisterUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UrlShortenerRestController {
    private final AccountService accountService;
    private final RegisterUrlService registerUrlService;

    @Autowired
    public UrlShortenerRestController(AccountService accountService, RegisterUrlService registerUrlService) {
        this.accountService = accountService;
        this.registerUrlService = registerUrlService;
    }

    @RequestMapping(value = "/{urlId}")
    public String redirectToUrl(@PathVariable("urlId") String urlId) {

        return urlId;
    }

    @PostMapping(value = "/account", produces = "application/json;UTF-8")
    public OpenAccountResponse account(@RequestBody OpenAccountRequest request) {
        String accountId = request.getAccountId();
        return accountService.save(accountId);
    }

    @PostMapping(value = "/register", produces = "application/json;UTF-8")
    public RegisterUrlResponse register(@RequestBody RegisterUrlRequest request) {
        return registerUrlService.save(request);
    }


}
