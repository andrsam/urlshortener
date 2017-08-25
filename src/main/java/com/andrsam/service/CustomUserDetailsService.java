package com.andrsam.service;


import com.andrsam.dao.AccountDao;
import com.andrsam.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * an authentication service
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    /**
     * an account dao
     */
    @Autowired
    private AccountDao accountDao;

    /**
     * looks for an user account
     *
     * @param accountId a user account id
     * @return the user details
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String accountId) throws UsernameNotFoundException {
        Account account = (Account) accountDao.get(accountId);
        if (account == null) {
            throw new UsernameNotFoundException("Account not found for this id: " + accountId);
        }
        List<SimpleGrantedAuthority> authList = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
        return new User(accountId, account.getPassword(), authList);
    }
}
