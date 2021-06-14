package com.pacilnugas.account.service;

import com.pacilnugas.account.core.Account;
import com.pacilnugas.account.core.Lecturer;
import com.pacilnugas.account.core.Student;
import com.pacilnugas.account.core.TeachingAssistant;
import com.pacilnugas.account.repository.AccountRepository;
import com.pacilnugas.account.security.PasswordCoder;
import com.pacilnugas.activities.model.Matkul;
import com.pacilnugas.activities.repository.MatkulRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private MatkulRepository matkulRepository;

    private PasswordCoder passwordCoder;

    /**
     * Constructur for accountService, used to initialize PasswordCoder.
     */
    public AccountService() {
        try {
            this.passwordCoder = new PasswordCoder();
        } catch (Exception e) {
            this.passwordCoder = null;
        }
    }

    /**
     * Creating account based upon the type that user picks.
     */
    public void createAccount(String username, String password, String type) {
        password = passwordCoder.encrypt(password);
        if (type.equalsIgnoreCase("Student")) {
            Student newAccount = new Student(username, password);
            accountRepository.save(newAccount);
        } else if (type.equalsIgnoreCase("Teaching Assistant")) {
            TeachingAssistant newAccount = new TeachingAssistant(username, password);
            accountRepository.save(newAccount);
        } else if (type.equalsIgnoreCase("Lecturer")) {
            Lecturer newAccount = new Lecturer(username, password);
            accountRepository.save(newAccount);
        }
    }

    public List<Account> getAllAccount() {
        return accountRepository.findAll();
    }

    /**
     * Retrieving display messages of all existing accounts.
     */
    public List<String> getAllDisplayMessage() {
        List<Account> listOfAccount = getAllAccount();
        List<String> listOfDisplayMessage = new ArrayList<>();
        for (Account account : listOfAccount) {
            listOfDisplayMessage.add(displayAccount(account));
        }
        return listOfDisplayMessage;
    }

    public String displayAccount(Account account) {
        return account.getType() + " with username " + account.getUsername();
    }

    /**
     * Authenticating account based on username and password the user inputs.
     */
    public Account authenticate(String username, String password) {
        Account foundAccount = accountRepository.findById(username).get();
        password = passwordCoder.encrypt(password);
        if (foundAccount.getPassword().equalsIgnoreCase(password)) {
            return foundAccount;
        }
        return null;
    }

    /**
     * Saving picked courses from personal page to database.
     */
    public void saveMatkul(String username, List<Matkul> listMatkul) {
        Account account = getAccountByUsername(username);
        account.setPersonalizedMatkul(listMatkul);
        accountRepository.save(account);
    }

    public Account getAccountByUsername(String username) {
        return accountRepository.findById(username).get();
    }

    /**
     * Checking whether username user inputs are already used or not.
     */
    public boolean checkUsernameUsed(String username) {
        for (Account account : getAllAccount()) {
            if (account.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}
