package com.andrsam.service.register;

import com.andrsam.dao.UrlDao;
import com.andrsam.model.Url;
import com.andrsam.request.RegisterUrlRequest;
import com.andrsam.response.RegisterUrlResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@PropertySource("classpath:urlshortener.properties")
public class RegisterUrlServiceImpl implements RegisterUrlService {

    public static final int BASE = 62;
    private final String BASE_DIGITS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    @Autowired
    private Environment environment;

    private final UrlDao urlDao;

    @Autowired
    public RegisterUrlServiceImpl(UrlDao urlDao) {
        this.urlDao = urlDao;
    }

    @Override
    public RegisterUrlResponse save(RegisterUrlRequest request) {
        String shortUrl = getShortURL(request.getUrl());
        Url url = new Url(request);
        urlDao.save(shortUrl, url);
        RegisterUrlResponse response = new RegisterUrlResponse(shortUrl);
        return response;
    }

    private String getShortURL(String input) {
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

        String baseUrl = environment.getProperty("urlshortener.baseUrl");
        StringBuilder url = new StringBuilder(baseUrl);
        while (number != 0) {
            url.append(BASE_DIGITS.charAt(number % BASE));
            number = Math.floorDiv(number, BASE);
        }
        return url.toString();
    }
}
