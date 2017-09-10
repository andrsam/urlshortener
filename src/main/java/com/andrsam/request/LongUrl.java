package com.andrsam.request;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * contains information about the URL that needs shortening
 */
public class LongUrl {
    /**
     * the URL that needs shortening
     */
    private String url;

    /**
     * redirect type:  301 | 302 (not mandatory, default 302)
     */
    private int redirectType;

    /**
     * the number of the URL redirects
     */
    private AtomicInteger redirectsCount = new AtomicInteger();

    public LongUrl() {
    }

    public LongUrl(String url, int redirectType) {
        this.url = url;
        this.redirectType = redirectType;
    }

    public String getUrl() {
        return url;
    }

    public int getRedirectType() {
        return redirectType;
    }

    public AtomicInteger getRedirectsCount() {
        return redirectsCount;
    }

    public void setRedirectsCount(int redirectsCount) {
        this.redirectsCount.set(redirectsCount);
    }

    @Override
    public String toString() {
        return "LongUrl{" +
                "url='" + url + '\'' +
                ", redirectType=" + redirectType +
                ", redirectsCount=" + redirectsCount +
                '}';
    }
}
