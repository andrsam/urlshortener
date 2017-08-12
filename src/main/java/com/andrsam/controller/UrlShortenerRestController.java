package com.andrsam.controller;

import com.andrsam.request.LongUrl;
import com.andrsam.request.OpenAccountRequest;
import com.andrsam.response.OpenAccountResponse;
import com.andrsam.response.RegisterUrlResponse;
import com.andrsam.service.account.AccountService;
import com.andrsam.service.url.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@RestController
public class UrlShortenerRestController {
    private final AccountService accountService;
    private final UrlService urlService;

    @Autowired
    public UrlShortenerRestController(AccountService accountService, UrlService urlService) {
        this.accountService = accountService;
        this.urlService = urlService;
    }

    @PostMapping(value = "/account", produces = "application/json;UTF-8")
    public OpenAccountResponse account(@RequestBody OpenAccountRequest request) {
        String accountId = request.getAccountId();
        return accountService.save(accountId);
    }

    @PostMapping(value = "/register", produces = "application/json;UTF-8")
    public RegisterUrlResponse register(@RequestBody LongUrl longUrl) {
        return urlService.save(longUrl);
    }

    @RequestMapping(value = "/{shortUrl}")
    public RedirectView redirectToUrl(@PathVariable("shortUrl") String shortUrl) {
        LongUrl longUrl = urlService.getLongUrl(shortUrl);
        longUrl.setRedirectsCount(longUrl.getRedirectsCount() + 1);
        RedirectView redirectView = new RedirectView(longUrl.getUrl());
        redirectView.setStatusCode(HttpStatus.valueOf(longUrl.getRedirectType()));
        return redirectView;
    }

    @GetMapping(value = "/statistic", produces = "application/json;UTF-8")
    @ResponseBody
    public Map<String, Integer> retrieveStatistics() {
        Map<String, Integer> response = urlService.getStatistics();
        return response;
    }
}
