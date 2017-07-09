package com.andrsam.dao;

import java.util.List;

public interface UrlDao<K, V> {
    V get(K shortUrl);

    boolean save(K shortUrl, V url);

    List<V> getAll();
}
