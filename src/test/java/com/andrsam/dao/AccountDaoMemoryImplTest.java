package com.andrsam.dao;

import com.andrsam.model.Account;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AccountDaoMemoryImplTest {
    private final AccountDao accountDao = new AccountDaoMemoryImpl();
    public static final String ACCOUNT_ID = "test";
    private Account account;


    @Before
    public void setUp() throws Exception {
        account = new Account(ACCOUNT_ID, "testpassword");
    }

    @Test
    public void save() throws Exception {
        boolean isAccountNotRegistered = accountDao.save(ACCOUNT_ID, account);
        Assert.assertTrue(isAccountNotRegistered);
    }

    @Test
    public void testSavedAlready() throws Exception {
        accountDao.save(ACCOUNT_ID, account);
        boolean isAccountNotRegistered = accountDao.save(ACCOUNT_ID, account);
        Assert.assertFalse(isAccountNotRegistered);
    }

}