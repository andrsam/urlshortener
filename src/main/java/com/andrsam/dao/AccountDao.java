package com.andrsam.dao;

import java.util.List;

/**
 * An interface for an account storage
 *
 * @param <K> An account identifier
 * @param <V> An account instance
 */
public interface AccountDao<K, V> {

    /**
     * Returns an account instance
     *
     * @param accountId
     * @return account instance
     */
    V get(K accountId);

    /**
     * Saves an account
     *
     * @param accountId user id
     * @param account
     * @return
     */

    boolean save(K accountId, V account);

    /**
     * returns a list if accounts
     */
    List<V> getAll();
}
