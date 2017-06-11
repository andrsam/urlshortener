package com.andrsam.dao;


import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class AccountDaoMemoryImpl<K, V> implements AccountDao<K, V> {
    HashMap<K, V> storage = new HashMap<>();

    @Override
    public V get(K accountId) {
        return storage.get(accountId);
    }

    @Override
    public void save(K accountId, V account) {
        storage.put(accountId, account);
    }

}
