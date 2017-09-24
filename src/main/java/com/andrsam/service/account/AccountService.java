package com.andrsam.service.account;

import com.andrsam.model.Account;
import com.andrsam.response.OpenAccountResponse;

/**
 * used for saving accounts
 */
public interface AccountService {

    /**
     * Looks up an account by account id
     *
     * @param accountId
     * @return
     */
    Account get(String accountId);

    /**
     * creates and saves an account object into the storage
     *
     * @param accountId an account id
     * @return a response, that contains information about result of registration
     */
    OpenAccountResponse save(String accountId);
}
