package com.andrsam.dao;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A memory implementation of the UrlDao
 *
 * @param <K>
 * @param <V>
 */
@Repository
public class UrlDaoMemoryImpl<K, V> implements UrlDao<K, V> {
    /**
     * the urls storage
     */
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
