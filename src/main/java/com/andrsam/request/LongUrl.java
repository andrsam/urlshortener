package com.andrsam.request;

/**
 * contains information about the URL that needs shortening
 */
public class LongUrl {
    /**
     * the URL that needs shortening
     */
    String url;

    /**
     * redirect type:  301 | 302 (not mandatory, default 302)
     */
    int redirectType;

    /**
     * the number of the URL redirects
     */
    int redirectsCount;

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

        LongUrl that = (LongUrl) o;

        if (redirectType != that.redirectType) return false;
        if (redirectsCount != that.redirectsCount) return false;
        return url.equals(that.url);
    }

    @Override
    public int hashCode() {
        int result = url.hashCode();
        result = 31 * result + redirectType;
        result = 31 * result + redirectsCount;
        return result;
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
