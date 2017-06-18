package com.andrsam.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@Service
public class UrlShortenerService {

    @Autowired
    private Environment environment;

    private final String BASE_DIGITS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private String getTinyURL(String input) {
        int hashNumber;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            hashNumber = number.intValue();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return hash2URL(hashNumber);
    }

    public String hash2URL(int number) {
        StringBuilder url = new StringBuilder(environment.getProperty("urlshortener.baseUrl"));
        while (number != 0) {
            url.append(BASE_DIGITS.charAt(number % 62));
            number = Math.floorDiv(number, 62);
        }
        return url.toString();
    }

}
