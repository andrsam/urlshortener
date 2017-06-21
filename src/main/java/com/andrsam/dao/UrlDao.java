package com.andrsam.dao;

public interface UrlDao<K, V> {
    V get(K shortUrl);

    boolean save(K shortUrl, V url);
}
