package com.andrsam.service.url;

import com.andrsam.dao.UrlDao;
import com.andrsam.request.UrlDescription;
import com.andrsam.response.RegisterUrlResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
@PropertySource("classpath:urlshortener.properties")
public class UrlServiceImpl implements UrlService {

    public static final int BASE = 62;
    private final String BASE_DIGITS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    @Autowired
    private Environment environment;

    private final UrlDao urlDao;

    @Autowired
    public UrlServiceImpl(UrlDao urlDao) {
        this.urlDao = urlDao;
    }

    @Override
    public RegisterUrlResponse save(UrlDescription description) {
        String urlId = generateUrlId(description.getUrl());
        urlDao.save(urlId, description);
        RegisterUrlResponse response = new RegisterUrlResponse(environment.getProperty("urlshortener.baseUrl") + urlId);
        return response;
    }

    @Override
    public UrlDescription getUrlDescription(String shortUrl) {
        return (UrlDescription) urlDao.get(shortUrl);
    }

    @Override
    public List<UrlDescription> getAll() {
        return urlDao.getAll();
    }


    private String generateUrlId(String input) {
        int hashNumber;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            hashNumber = number.intValue();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return hash2UrlId(hashNumber);
    }

    private String hash2UrlId(int number) {
        StringBuilder url = new StringBuilder();
        while (number != 0) {
            url.append(BASE_DIGITS.charAt(number % BASE));
            number = Math.floorDiv(number, BASE);
        }
        return url.toString();
    }


}
