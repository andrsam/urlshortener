package com.andrsam.dao;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Repository
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

    @Override
    public List<V> getAll() {
        return new ArrayList<>(storage.values());
    }
}
