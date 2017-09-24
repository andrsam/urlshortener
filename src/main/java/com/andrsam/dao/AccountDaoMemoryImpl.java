package com.andrsam.dao;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Memory implementation of the account DAO
 *
 * @param <K> An account identifier
 * @param <V> An account instance
 */
@Repository
public class AccountDaoMemoryImpl<K, V> implements AccountDao<K, V> {
    /**
     * the account storage
     */
    private ConcurrentHashMap<K, V> storage = new ConcurrentHashMap<>();

    @Override
    public V get(K accountId) {
        return storage.get(accountId);
    }

    @Override
    public boolean save(K accountId, V account) {
        return (storage.putIfAbsent(accountId, account) == null);
    }

    @Override
    public List<V> getAll() {
        return new ArrayList<>(storage.values());
    }
}
