package com.andrsam.controller;

import com.andrsam.model.Account;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlShortenerRestController {

    @PostMapping(value = "", produces = "application/json;UTF-8")
    public Account account(@RequestParam("AccountId") String accountId) {

        return null;
    }
}
