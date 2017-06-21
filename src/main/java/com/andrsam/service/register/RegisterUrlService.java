package com.andrsam.service.register;

import com.andrsam.request.RegisterUrlRequest;
import com.andrsam.response.RegisterUrlResponse;

public interface RegisterUrlService {
    RegisterUrlResponse save(RegisterUrlRequest request);
}
