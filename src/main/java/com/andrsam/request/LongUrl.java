package com.andrsam.request;

public class LongUrl {
    String url;
    int redirectType;
    int redirectsCount;

    public LongUrl(String url, int redirectType) {
        this.url = url;
        this.redirectType = redirectType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getRedirectType() {
        return redirectType;
    }

    public void setRedirectType(int redirectType) {
        this.redirectType = redirectType;
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
