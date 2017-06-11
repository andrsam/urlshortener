package com.andrsam.dao;

public interface AccountDao<K, V> {
  V get(K accountId);

  void save(K key, V account);
}
