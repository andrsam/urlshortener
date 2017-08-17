package com.andrsam.dao;

import com.andrsam.request.LongUrl;
import org.junit.Test;

public class UrlDaoMemoryImplTest {
    @Test
    public void save() throws Exception {

    }

    @Test
    public void testAlreadySaved() throws Exception {
        UrlDao urlDao = new UrlDaoMemoryImpl();
        urlDao.save("test", new LongUrl("test", 301));
    }

    @Test
    public void getAll() throws Exception {
    }

}