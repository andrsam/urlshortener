package com.andrsam.dao;

import java.util.List;

/**
 * An interface for url storage
 *
 * @param <K> an identifier of the url
 * @param <V> a url description
 */
public interface UrlDao<K, V> {
    /**
     * retrieves a long url description
     *
     * @param shortUrl an identifier of the url
     * @return a url description
     */
    V get(K shortUrl);

    /**
     * saves an information about the url
     *
     * @param shortUrl
     * @param url
     * @return true, if operation success
     */
    boolean save(K shortUrl, V url);

    /**
     * returns a list of the urls
     *
     * @return a list of the urls
     */
    List<V> getAll();
}
