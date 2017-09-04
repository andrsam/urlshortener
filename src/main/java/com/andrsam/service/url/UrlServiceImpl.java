package com.andrsam.service.url;

import com.andrsam.dao.UrlDao;
import com.andrsam.request.LongUrl;
import com.andrsam.response.RegisterUrlResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * a service implementation for storing, generating urls and retrieving the statistics
 */
@Service
@PropertySource("classpath:urlshortener.properties")
public class UrlServiceImpl implements UrlService {
    /**
     * th
     */
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
    public RegisterUrlResponse save(LongUrl longUrl) {
        String urlId = generateShortUrl(longUrl.getUrl());
        urlDao.save(urlId, longUrl);
        RegisterUrlResponse response = new RegisterUrlResponse(environment.getProperty("urlshortener.baseUrl") + urlId);
        return response;
    }

    @Override
    public LongUrl getLongUrl(String shortUrl) {
        return (LongUrl) urlDao.get(shortUrl);
    }

    @Override
    public Map<String, Integer> getStatistics() {
        List<LongUrl> urls = urlDao.getAll();
        Map<String, Integer> statistics = new HashMap<>();
        urls.forEach(url -> statistics.put(url.getUrl(), url.getRedirectsCount()));
        return statistics;
    }

    /**
     * generates a short url
     *
     * @param input
     * @return
     */
    private String generateShortUrl(String input) {
        int hashNumber;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            hashNumber = number.intValue();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return hash2ShortUrl(hashNumber);
    }

    /**
     * converts a hash number into a Base62 encoded string
     *
     * @param number
     * @return
     */
    private String hash2ShortUrl(int number) {
        StringBuilder url = new StringBuilder();
        while (number != 0) {
            url.append(BASE_DIGITS.charAt(number % BASE));
            number = Math.floorDiv(number, BASE);
        }
        return url.toString();
    }
}
