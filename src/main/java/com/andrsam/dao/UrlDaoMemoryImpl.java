package com.andrsam.dao;

import java.util.ArrayList;

public class UrlDaoMemoryImpl<T> implements UrlDao<T> {
    private ArrayList<T> storage = new ArrayList<>();

    @Override
    public boolean save(T url) {
        return storage.add(url);
    }
}
