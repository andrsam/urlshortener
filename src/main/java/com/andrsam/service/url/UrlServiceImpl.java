package com.andrsam.service.url;

import com.andrsam.dao.AccountDao;
import com.andrsam.dao.UrlDao;
import com.andrsam.model.Account;
import com.andrsam.request.LongUrl;
import com.andrsam.response.RegisterUrlResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

/**
 * a service implementation for storing, generating urls and retrieving the statistics
 */
@Service
@PropertySource("classpath:urlshortener.properties")
public class UrlServiceImpl implements UrlService {

    public static final int BASE = 62;
    private final String BASE_DIGITS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    String baseUrl;

    private final UrlDao urlDao;

    private final AccountDao accountDao;

    @Autowired
    public UrlServiceImpl(UrlDao urlDao, AccountDao accountDao, @Value("${urlshortener.baseUrl}") String baseUrl) {
        this.urlDao = urlDao;
        this.accountDao = accountDao;
        this.baseUrl = baseUrl;
    }

    @Override
    public RegisterUrlResponse save(LongUrl longUrl) {
        String urlId = generateShortUrl(longUrl.getUrl());
        urlDao.save(urlId, longUrl);
        RegisterUrlResponse response = new RegisterUrlResponse(baseUrl + urlId);
        return response;
    }

    @Override
    public LongUrl getLongUrl(String shortUrl) {
        return (LongUrl) urlDao.get(shortUrl);
    }

    @Override
    public Map<String, Integer> generateStatistics() {
        List<LongUrl> urls = urlDao.getAll();
        Map<String, Integer> statistics = urls.stream().collect(toMap(LongUrl::getUrl, url -> url.getRedirectsCount().intValue()));
        return statistics;
    }

    @Override
    public Map<String, Map<String, Integer>> generateStatByAccounts() {
        List<Account> accounts = accountDao.getAll();
        List<LongUrl> urls = urlDao.getAll();
        Map<String, Map<String, Integer>> result = accounts.stream().collect(toMap(Account::getId, account -> getStatisticsByAccount(urls, account)));
        return result;
    }

    private Map<String, Integer> getStatisticsByAccount(List<LongUrl> urls, Account searchedAccount) {
        return urls.stream().filter(url -> url.getAccount().equals(searchedAccount)).collect(toMap(LongUrl::getUrl, url -> url.getRedirectsCount().intValue()));
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
