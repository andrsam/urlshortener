package com.andrsam.dao;

import java.util.concurrent.ConcurrentHashMap;

public class UrlDaoMemoryImpl<K, V> implements UrlDao<K, V> {
    private ConcurrentHashMap<K, V> storage = new ConcurrentHashMap<>();

    @Override
    public V get(K shortUrl) {
        return storage.get(shortUrl);
    }

    @Override
    public boolean save(K shortUrl, V url) {
        return (storage.putIfAbsent(shortUrl, url) == null);
    }
}
