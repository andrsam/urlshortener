package com.andrsam.dao;

public interface UrlDao<T> {
    boolean save(T url);
}
