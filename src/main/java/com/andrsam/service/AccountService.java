package com.andrsam.service;

import com.andrsam.response.OpenAccountResponse;

public interface AccountService {
    OpenAccountResponse save(String accountId);
}
