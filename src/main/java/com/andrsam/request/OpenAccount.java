package com.andrsam.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * request for opening account
 */
public class OpenAccount {
    /**
     * An user login
     */
    @JsonProperty("AccountId")
    private String accountId;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
