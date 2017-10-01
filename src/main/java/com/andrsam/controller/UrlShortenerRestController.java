package com.andrsam.controller;

import com.andrsam.request.LongUrl;
import com.andrsam.request.OpenAccount;
import com.andrsam.response.OpenAccountResponse;
import com.andrsam.response.RegisterUrlResponse;
import com.andrsam.service.account.AccountService;
import com.andrsam.service.url.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

/**
 * a rest controller
 */
@RestController
public class UrlShortenerRestController {
    private final AccountService accountService;
    private final UrlService urlService;

    @Autowired
    public UrlShortenerRestController(final AccountService accountService, final UrlService urlService) {
        this.accountService = accountService;
        this.urlService = urlService;
    }

    /**
     * Opens an user account
     *
     * @param request JSON object for opening account
     * @return OpenAccountResponse
     */
    @PostMapping(value = "/account", produces = "application/json;UTF-8")
    public OpenAccountResponse account(@RequestBody OpenAccount request) {
        String accountId = request.getAccountId();
        return accountService.save(accountId);
    }

    /**
     * Registers a url with account
     *
     * @param longUrl - contains long url and redirect type
     * @return response with shortened url
     */
    @PostMapping(value = "/registerByAccount", produces = "application/json;UTF-8")
    public RegisterUrlResponse registerByAccount(@RequestBody LongUrl longUrl) {
        String accountId = SecurityContextHolder.getContext().getAuthentication().getName();
        longUrl.setAccount(accountService.get(accountId));
        return urlService.save(longUrl);
    }

    /**
     * Registers a url
     *
     * @param longUrl - contains long url and redirect type
     * @return response with shortened url
     */
    @PostMapping(value = "/register", produces = "application/json;UTF-8")
    public RegisterUrlResponse register(@RequestBody LongUrl longUrl) {
        return urlService.save(longUrl);
    }

    /**
     * Redirects the client to the configured address with the configured http status
     *
     * @param shortUrl a shortened url to
     * @return RedirectView that redirects to the configured address with the configured http status
     */
    @RequestMapping(value = "/{shortUrl}")
    public RedirectView redirectToUrl(@PathVariable("shortUrl") String shortUrl) {
        LongUrl longUrl = urlService.getLongUrl(shortUrl);
        longUrl.setRedirectsCount(longUrl.getRedirectsCount().incrementAndGet());
        RedirectView redirectView = new RedirectView(longUrl.getUrl());
        redirectView.setStatusCode(HttpStatus.valueOf(longUrl.getRedirectType()));
        return redirectView;
    }

    /**
     * Retrieves a statistics
     *
     * @return map with long url and redirects count
     */
    @GetMapping(value = "/statistic", produces = "application/json;UTF-8")
    @ResponseBody
    public Map<String, Integer> retrieveStatistics() {
        Map<String, Integer> response = urlService.generateStatistics();
        return response;
    }

    /**
     * Retrieves a statistics
     *
     * @return map with long url and redirects count
     */
    @GetMapping(value = "/statByAccounts", produces = "application/json;UTF-8")
    @ResponseBody
    public Map<String, Map<String, Integer>> retrieveStatisticsByAccounts() {
        Map<String, Map<String, Integer>> response = urlService.generateStatByAccounts();
        return response;
    }


}
