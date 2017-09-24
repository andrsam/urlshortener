package com.andrsam.service.url;

import com.andrsam.request.LongUrl;
import com.andrsam.response.RegisterUrlResponse;

import java.util.Map;

/**
 * a service for storing, generating urls and retrieving the statistics
 */
public interface UrlService {
    /**
     * saves the url
     *
     * @param request
     * @return
     */
    RegisterUrlResponse save(LongUrl request);

    /**
     * returns a long url object
     *
     * @param shortUrl the shortened url
     * @return a long url object
     */
    LongUrl getLongUrl(String shortUrl);

    /**
     * returns the statistics
     *
     * @return a map with the url id and the redurects count
     */
    Map<String, Integer> generateStatistics();

    /**
     * returns the statistics by accounts
     *
     * @return a map with the url id and the redurects count
     */
    Map<String, Map<String, Integer>> generateStatByAccounts();
}
