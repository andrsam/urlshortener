package com.andrsam.controller;

import com.andrsam.request.OpenAccountRequest;
import com.andrsam.request.UrlDescription;
import com.andrsam.response.OpenAccountResponse;
import com.andrsam.response.RegisterUrlResponse;
import com.andrsam.service.account.AccountService;
import com.andrsam.service.url.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class UrlShortenerRestController {
    private final AccountService accountService;
    private final UrlService urlService;

    @Autowired
    public UrlShortenerRestController(AccountService accountService, UrlService urlService) {
        this.accountService = accountService;
        this.urlService = urlService;
    }

    @RequestMapping(value = "/{urlId}")
    public RedirectView redirectToUrl(@PathVariable("urlId") String urlId) {
        UrlDescription urlDescription = urlService.getUrlDescription(urlId);
        urlDescription.setRedirectsCount(urlDescription.getRedirectsCount() + 1);
        RedirectView redirectView = new RedirectView(urlDescription.getUrl());
        redirectView.setStatusCode(HttpStatus.valueOf(urlDescription.getRedirectType()));
        return redirectView;
    }

    @PostMapping(value = "/account", produces = "application/json;UTF-8")
    public OpenAccountResponse account(@RequestBody OpenAccountRequest request) {
        String accountId = request.getAccountId();
        return accountService.save(accountId);
    }

    @PostMapping(value = "/register", produces = "application/json;UTF-8")
    public RegisterUrlResponse register(@RequestBody UrlDescription request) {
        return urlService.save(request);
    }

}
