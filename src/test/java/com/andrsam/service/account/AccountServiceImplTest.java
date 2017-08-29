package com.andrsam.service.account;

import com.andrsam.dao.AccountDao;
import com.andrsam.dao.AccountDaoMemoryImpl;
import com.andrsam.response.OpenAccountResponse;
import org.junit.Assert;
import org.junit.Test;

/**
 * a test for account service
 */
public class AccountServiceImplTest {
    private static final String ACCOUNT_ID = "test";

    @Test
    public void save() throws Exception {
        AccountDao accountDao = new AccountDaoMemoryImpl();
        AccountService accountService = new AccountServiceImpl(accountDao);
        OpenAccountResponse openAccountResponse = accountService.save(ACCOUNT_ID);
        Assert.assertTrue(openAccountResponse.isSuccess());
        Assert.assertEquals(openAccountResponse.getDescription(), "Your account is opened");
        Assert.assertNotNull(openAccountResponse.getPassword());
    }

}