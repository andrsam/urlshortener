package com.andrsam.service.register;

import com.andrsam.request.RegisterUrlRequest;
import com.andrsam.response.RegisterUrlResponse;

public class RegisterUrlServiceImpl implements RegisterUrlService {

    @Override
    public RegisterUrlResponse save(RegisterUrlRequest request) {
        String longUrl = request.getUrl();

        return null;
    }
}
