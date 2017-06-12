package com.andrsam.service;

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

@Service
public class AccountServiceImpl implements AccountService {
    public static final List<CharacterRule> RULES = Arrays.asList(
            // at least one upper-case character
            new CharacterRule(EnglishCharacterData.UpperCase, 1),

            // at least one lower-case character
            new CharacterRule(EnglishCharacterData.LowerCase, 1),

            // at least one digit character
            new CharacterRule(EnglishCharacterData.Digit, 1));

    @Autowired
    AccountDao accountDao;

    @Override
    public OpenAccountResponse save(String accountId) {
        String description;
        String password = "";
        Account account = new Account(accountId, password);
        boolean success = accountDao.save(accountId, account);
        if (success) {
            description = "Your account is opened";
            password = generatePassword();
        } else {
            description = "Account already exists";
        }
        return new OpenAccountResponse(success, description, password);
    }

    private String generatePassword() {
        PasswordGenerator generator = new PasswordGenerator();
        String password = generator.generatePassword(8, RULES);
        return password;
    }
}
