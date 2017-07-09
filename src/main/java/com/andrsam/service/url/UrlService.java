package com.andrsam.service.url;

import com.andrsam.request.UrlDescription;
import com.andrsam.response.RegisterUrlResponse;

import java.util.List;

public interface UrlService {

    RegisterUrlResponse save(UrlDescription request);

    UrlDescription getUrlDescription(String shortUrl);

    List<UrlDescription> getAll();
}
