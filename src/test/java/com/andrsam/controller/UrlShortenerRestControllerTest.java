package com.andrsam.controller;

import com.andrsam.dao.AccountDao;
import com.andrsam.dao.AccountDaoMemoryImpl;
import com.andrsam.dao.UrlDao;
import com.andrsam.dao.UrlDaoMemoryImpl;
import com.andrsam.request.LongUrl;
import com.andrsam.request.OpenAccount;
import com.andrsam.response.OpenAccountResponse;
import com.andrsam.response.RegisterUrlResponse;
import com.andrsam.service.account.AccountService;
import com.andrsam.service.account.AccountServiceImpl;
import com.andrsam.service.url.UrlService;
import com.andrsam.service.url.UrlServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * a test for the controller
 */
public class UrlShortenerRestControllerTest {
    private AccountDao accountDao = new AccountDaoMemoryImpl();
    private AccountService accountService = new AccountServiceImpl(accountDao);

    private UrlDao urlDao = new UrlDaoMemoryImpl();
    private UrlService urlService = new UrlServiceImpl(urlDao);

    private UrlShortenerRestController urlShortenerRestController = new UrlShortenerRestController(accountService, urlService);
    private OpenAccount openAccount = new OpenAccount();

    private static final String ACCOUNT_ID = "test";
    private static final String URL = "http://stackoverflow.com/questions/1567929/website-safe-data-access-architecture-question?rq=1";
    private static final int REDIRECT_TYPE = 301;

    @Before
    public void setUp() throws Exception {
        openAccount.setAccountId(ACCOUNT_ID);
    }

    @Test
    public void account() throws Exception {
        OpenAccountResponse openAccountResponse = urlShortenerRestController.account(openAccount);
        Assert.assertTrue(openAccountResponse.isSuccess());
        Assert.assertEquals(openAccountResponse.getDescription(), "Your account is opened");
        Assert.assertNotNull(openAccountResponse.getPassword());
    }

    @Test
    public void testAccountAlreadyOpened() {
        urlShortenerRestController.account(openAccount);
        OpenAccountResponse openAccountWrongResponse = urlShortenerRestController.account(openAccount);
        Assert.assertFalse(openAccountWrongResponse.isSuccess());
        Assert.assertEquals(openAccountWrongResponse.getDescription(), "Account already exists");
        Assert.assertEquals(openAccountWrongResponse.getPassword(), "");
    }

    @Test
    public void register() throws Exception {
        LongUrl longUrl = new LongUrl(URL, REDIRECT_TYPE);
        RegisterUrlResponse response = urlShortenerRestController.register(longUrl);
        System.out.println(response.getShortUrl());
        Assert.assertNotNull(response.getShortUrl());
    }

    @Test
    public void redirectToUrl() throws Exception {
    }

    @Test
    public void retrieveStatistics() throws Exception {
    }

}