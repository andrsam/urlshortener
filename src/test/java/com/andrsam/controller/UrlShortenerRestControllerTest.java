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
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * a test for the controller
 */
public class UrlShortenerRestControllerTest {
    private static final String ACCOUNT_ID = "test";
    private static final String URL = "http://stackoverflow.com/questions/1567929/website-safe-data-access-architecture-question?rq=1";
    private static final int REDIRECT_TYPE = 301;

    private AccountDao accountDao = new AccountDaoMemoryImpl();
    private AccountService accountService = new AccountServiceImpl(accountDao);

    private UrlDao urlDao = new UrlDaoMemoryImpl();
    private UrlService urlService = new UrlServiceImpl(urlDao);

    private UrlShortenerRestController urlShortenerRestController = new UrlShortenerRestController(accountService, urlService);
    private OpenAccount openAccount = new OpenAccount();

    @Before
    public void setUp() throws Exception {
        openAccount.setAccountId(ACCOUNT_ID);
    }

    @Test
    public void account() throws Exception {
        openAccount.setAccountId(ACCOUNT_ID);
        OpenAccountResponse openAccountResponse = urlShortenerRestController.account(openAccount);
        assertThat(openAccountResponse.isSuccess(), is(true));
        assertThat(openAccountResponse.getDescription(), is("Your account is opened"));
        assertThat(openAccountResponse.getPassword(), not(null));
    }

    @Test
    public void testAccountAlreadyOpened() {
        openAccount.setAccountId(ACCOUNT_ID);
        urlShortenerRestController.account(openAccount);
        OpenAccountResponse openAccountWrongResponse = urlShortenerRestController.account(openAccount);
        assertThat(openAccountWrongResponse.isSuccess(), is(false));
        assertThat(openAccountWrongResponse.getDescription(), is("Account with that ID already exists"));
        assertThat(openAccountWrongResponse.getPassword(), is(""));
    }

    @Test
    public void register() throws Exception {
        LongUrl longUrl = new LongUrl(URL, REDIRECT_TYPE);
        RegisterUrlResponse response = urlShortenerRestController.register(longUrl);
        assertNotNull(response.getShortUrl());
        System.out.println(response.getShortUrl());
    }

    @Test
    public void retrieveStatistics() throws Exception {
        //urlShortenerRestController.redirectToUrl();
    }

}