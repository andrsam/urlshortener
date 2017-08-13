package com.andrsam.dao;

import com.andrsam.model.Account;
import org.junit.Before;
import org.junit.Test;

public class AccountDaoMemoryImplTest {
    private final AccountDao accountDao = new AccountDaoMemoryImpl();

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void save() throws Exception {
        String accountId = "test";
        Account account = new Account(accountId, accountId);
        boolean isAccountNotRegistered = accountDao.save(accountId, account);
    }

}