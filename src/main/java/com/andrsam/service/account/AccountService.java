package com.andrsam.service.account;

import com.andrsam.response.OpenAccountResponse;

public interface AccountService {
    OpenAccountResponse save(String accountId);
}
