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
    @Autowired
    AccountDao accountDao;

    @Override
    public OpenAccountResponse save(String accountId) {
        boolean success = accountDao.get(accountId) == null;
        String description;
        String password = "";
        if (success) {
            description = "Your account is opened";
            password = generatePassword();
            Account account = new Account(accountId, password);
            accountDao.save(accountId, account);
        } else {
            description = "Account already exists";
        }

        return new OpenAccountResponse(success, description, password);
    }

    private String generatePassword() {
        List<CharacterRule> rules = Arrays.asList(
                // at least one upper-case character
                new CharacterRule(EnglishCharacterData.UpperCase, 1),

                // at least one lower-case character
                new CharacterRule(EnglishCharacterData.LowerCase, 1),

                // at least one digit character
                new CharacterRule(EnglishCharacterData.Digit, 1));

        PasswordGenerator generator = new PasswordGenerator();

        // Generated password is 12 characters long, which complies with policy
        String password = generator.generatePassword(8, rules);
        return password;
    }
}
