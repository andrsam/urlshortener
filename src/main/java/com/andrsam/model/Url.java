package com.andrsam.model;

import com.andrsam.request.RegisterUrlRequest;

public class Url {
    RegisterUrlRequest longUrl;

    int redirectType;

    int redirectsCount;

    public Url(RegisterUrlRequest longUrl) {
        this.longUrl = longUrl;
    }

    public RegisterUrlRequest getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(RegisterUrlRequest longUrl) {
        this.longUrl = longUrl;
    }

    public int getRedirectsCount() {
        return redirectsCount;
    }

    public void setRedirectsCount(int redirectsCount) {
        this.redirectsCount = redirectsCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Url url = (Url) o;

        return longUrl.equals(url.longUrl);
    }

    @Override
    public int hashCode() {
        return longUrl.hashCode();
    }

    @Override
    public String toString() {
        return "Url{" +
                "longUrl=" + longUrl +
                ", redirectsCount=" + redirectsCount +
                '}';
    }
}
