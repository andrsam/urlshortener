package com.andrsam.controller;

import com.andrsam.response.OpenAccountResponse;
import com.andrsam.service.password.PasswordServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlShortenerRestController {

    @Autowired
    PasswordServiceImpl passwordService;

    @PostMapping(value = "", produces = "application/json;UTF-8")
    public OpenAccountResponse account(@RequestParam("AccountId") String accountId) {

        return null;
    }
}
