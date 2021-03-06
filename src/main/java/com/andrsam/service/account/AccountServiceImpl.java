package com.andrsam.service.account;

import com.andrsam.dao.AccountDao;
import com.andrsam.model.Account;
import com.andrsam.response.OpenAccountResponse;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * a service for saving the accounts
 */
@Service
public class AccountServiceImpl implements AccountService {
    /**
     * a list of password generation rules
     */
    public static final List<CharacterRule> RULES = Arrays.asList(
            // at least one upper-case character
            new CharacterRule(EnglishCharacterData.UpperCase, 1),

            // at least one lower-case character
            new CharacterRule(EnglishCharacterData.LowerCase, 1),

            // at least one digit character
            new CharacterRule(EnglishCharacterData.Digit, 1));

    /**
     * success registration message
     */
    public static final String YOUR_ACCOUNT_IS_OPENED = "Your account is opened";

    /**
     * error registration message
     */
    public static final String ACCOUNT_ALREADY_EXISTS = "Account with that ID already exists";

    /**
     * an account dao
     */
    private final AccountDao accountDao;

    @Autowired
    public AccountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public Account get(String accountId) {
        return (Account) accountDao.get(accountId);
    }

    @Override
    public OpenAccountResponse save(String accountId) {
        String password = "";
        Account account = new Account(accountId, password);
        boolean isAccountNotRegistered = accountDao.save(accountId, account);
        String description;
        if (isAccountNotRegistered) {
            description = YOUR_ACCOUNT_IS_OPENED;
            password = generatePassword();
            account.setPassword(password);
        } else {
            description = ACCOUNT_ALREADY_EXISTS;
        }
        return new OpenAccountResponse(isAccountNotRegistered, description, password);
    }

    /**
     * generates a password
     *
     * @return the password
     */
    private String generatePassword() {
        PasswordGenerator generator = new PasswordGenerator();
        String password = generator.generatePassword(8, RULES);
        return password;
    }
}
