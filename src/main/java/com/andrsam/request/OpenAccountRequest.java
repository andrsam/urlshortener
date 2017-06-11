package com.andrsam.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OpenAccountRequest {

    @JsonProperty("AccountId")
    String accountId;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
