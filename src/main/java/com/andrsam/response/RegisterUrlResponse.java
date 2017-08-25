package com.andrsam.response;

/**
 * a response that returns in case of register url
 */
public class RegisterUrlResponse {
    /**
     *
     */
    private String shortUrl;

    public RegisterUrlResponse(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegisterUrlResponse that = (RegisterUrlResponse) o;

        return shortUrl.equals(that.shortUrl);
    }

    @Override
    public int hashCode() {
        return shortUrl.hashCode();
    }

    @Override
    public String toString() {
        return "RegisterUrlResponse{" +
                "shortUrl='" + shortUrl + '\'' +
                '}';
    }
}
