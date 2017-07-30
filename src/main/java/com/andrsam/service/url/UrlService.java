package com.andrsam.service.url;

import com.andrsam.request.LongUrl;
import com.andrsam.response.RegisterUrlResponse;

import java.util.Map;

public interface UrlService {

    RegisterUrlResponse save(LongUrl request);

    LongUrl getLongUrl(String shortUrl);

    Map<String, Integer> getStatistics();
}
