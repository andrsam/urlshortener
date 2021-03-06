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

import java.security.Principal;
import java.util.Map;

import static org.easymock.EasyMock.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * a test for the controller
 */
public class UrlShortenerRestControllerTest {
    private static final String ACCOUNT_ID = "test";
    private static final String URL = "http://stackoverflow.com/questions/1567929/website-safe-data-access-architecture-question?rq=1";
    private static final int REDIRECT_TYPE = 301;
    private static final String BASE_URL = "http://lvh.me/";
    private static final String URL_ID = "bvZSU";

    private AccountDao accountDao = new AccountDaoMemoryImpl();

    private AccountService accountService = new AccountServiceImpl(accountDao);
    private UrlDao urlDao = new UrlDaoMemoryImpl();

    private UrlService urlService = new UrlServiceImpl(urlDao, accountDao, BASE_URL);
    private UrlShortenerRestController urlShortenerRestController = new UrlShortenerRestController(accountService, urlService);

    private OpenAccount openAccount = new OpenAccount();
    private Principal principalMock = createMock(Principal.class);

    @Before
    public void setUp() throws Exception {
        openAccount.setAccountId(ACCOUNT_ID);
        expect(principalMock.getName()).andReturn(ACCOUNT_ID);
        replay(principalMock);
    }

    @Test
    public void account() throws Exception {
        OpenAccountResponse openAccountResponse = urlShortenerRestController.account(openAccount);
        assertThat(openAccountResponse.isSuccess(), is(true));
        assertThat(openAccountResponse.getDescription(), is("Your account is opened"));
        assertNotNull(openAccountResponse.getPassword());
    }

    @Test
    public void testAccountAlreadyOpened() {
        urlShortenerRestController.account(openAccount);
        OpenAccountResponse openAccountWrongResponse = urlShortenerRestController.account(openAccount);
        assertThat(openAccountWrongResponse.isSuccess(), is(false));
        assertThat(openAccountWrongResponse.getDescription(), is("Account with that ID already exists"));
        assertThat(openAccountWrongResponse.getPassword(), is(""));
    }

    @Test
    public void registerByAccount() throws Exception {
        urlShortenerRestController.account(openAccount);
        LongUrl longUrl = new LongUrl(URL, REDIRECT_TYPE);
        RegisterUrlResponse response = urlShortenerRestController.registerByAccount(longUrl, principalMock);
        assertThat(response.getShortUrl(), is(BASE_URL + URL_ID));
        assertThat(longUrl.getAccount().getId(), is(ACCOUNT_ID));
    }

    @Test
    public void register() throws Exception {
        LongUrl longUrl = new LongUrl(URL, REDIRECT_TYPE);
        RegisterUrlResponse response = urlShortenerRestController.register(longUrl);
        assertThat(response.getShortUrl(), is(BASE_URL + URL_ID));
    }

    @Test
    public void retrieveStatistics() throws Exception {
        LongUrl longUrl = new LongUrl(URL, REDIRECT_TYPE);
        RegisterUrlResponse response = urlShortenerRestController.register(longUrl);
        urlShortenerRestController.redirectToUrl(response.getShortUrl().replace(BASE_URL, ""));
        Map<String, Integer> statistics = urlShortenerRestController.retrieveStatistics();
        assertThat(statistics.get(URL), is(1));
    }

    @Test
    public void retrieveStatisticsByAccounts() throws Exception {
        urlShortenerRestController.account(openAccount);
        LongUrl longUrl = new LongUrl(URL, REDIRECT_TYPE);
        RegisterUrlResponse response = urlShortenerRestController.registerByAccount(longUrl, principalMock);
        urlShortenerRestController.redirectToUrl(response.getShortUrl().replace(BASE_URL, ""));
        Map<String, Map<String, Integer>> statisticsByAccounts = urlShortenerRestController.retrieveStatisticsByAccounts();
        assertThat(statisticsByAccounts.get(ACCOUNT_ID).get(URL), is(1));
    }
}