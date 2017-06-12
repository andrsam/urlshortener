package com.andrsam.dao;


import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccountDaoMemoryImpl<K, V> implements AccountDao<K, V> {
    private ConcurrentHashMap<K, V> storage = new ConcurrentHashMap<>();

    @Override
    public V get(K accountId) {
        return storage.get(accountId);
    }

    @Override
    public boolean save(K accountId, V account) {
        return (storage.putIfAbsent(accountId, account) == null);
    }
}
