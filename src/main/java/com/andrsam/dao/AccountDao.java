package com.andrsam.dao;

public interface AccountDao<K, V> {
  V get(K accountId);

  boolean save(K key, V account);
}
