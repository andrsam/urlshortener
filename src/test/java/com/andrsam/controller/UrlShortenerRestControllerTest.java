package com.andrsam.controller;

import com.andrsam.dao.AccountDao;
import com.andrsam.dao.AccountDaoMemoryImpl;
import com.andrsam.dao.UrlDao;
import com.andrsam.dao.UrlDaoMemoryImpl;
import com.andrsam.request.OpenAccountRequest;
import com.andrsam.service.account.AccountService;
import com.andrsam.service.account.AccountServiceImpl;
import com.andrsam.service.url.UrlService;
import com.andrsam.service.url.UrlServiceImpl;
import org.junit.Before;
import org.junit.Test;

public class UrlShortenerRestControllerTest {
    private AccountDao accountDao = new AccountDaoMemoryImpl();
    private UrlDao urlDao = new UrlDaoMemoryImpl();
    private AccountService accountService = new AccountServiceImpl(accountDao);
    private UrlService urlService = new UrlServiceImpl(urlDao);
    private UrlShortenerRestController urlShortenerRestController = new UrlShortenerRestController(accountService, urlService);
    private OpenAccountRequest openAccountRequest = new OpenAccountRequest();
    private static final String ACCOUNT_ID = "test";


    @Before
    public void setUp() throws Exception {
        openAccountRequest.setAccountId(ACCOUNT_ID);


    }

    @Test
    public void account() throws Exception {

    }

    @Test
    public void register() throws Exception {
    }

    @Test
    public void redirectToUrl() throws Exception {
    }

    @Test
    public void retrieveStatistics() throws Exception {
    }

}