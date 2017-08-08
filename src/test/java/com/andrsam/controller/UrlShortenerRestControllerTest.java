package com.andrsam.controller;

import com.andrsam.dao.AccountDao;
import com.andrsam.dao.AccountDaoMemoryImpl;
import com.andrsam.dao.UrlDao;
import com.andrsam.dao.UrlDaoMemoryImpl;
import com.andrsam.service.account.AccountService;
import com.andrsam.service.account.AccountServiceImpl;
import com.andrsam.service.url.UrlService;
import com.andrsam.service.url.UrlServiceImpl;
import org.junit.Before;
import org.junit.Test;

public class UrlShortenerRestControllerTest {
    private AccountService accountService;
    private UrlService urlService;
    private UrlShortenerRestController urlShortenerRestController;
    private AccountDao accountDao;
    private UrlDao urlDao;

    @Before
    public void setUp() throws Exception {
        accountDao = new AccountDaoMemoryImpl();
        accountService = new AccountServiceImpl(accountDao);
        urlDao = new UrlDaoMemoryImpl();
        urlService = new UrlServiceImpl(urlDao);
        urlShortenerRestController = new UrlShortenerRestController(accountService, urlService);
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