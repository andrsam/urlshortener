package com.andrsam.request;

public class RegisterUrlRequest {
    String url;
    int redirectType;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegisterUrlRequest that = (RegisterUrlRequest) o;

        if (redirectType != that.redirectType) return false;
        return url.equals(that.url);
    }

    @Override
    public int hashCode() {
        int result = url.hashCode();
        result = 31 * result + redirectType;
        return result;
    }
}
