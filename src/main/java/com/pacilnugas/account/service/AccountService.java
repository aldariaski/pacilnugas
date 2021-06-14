package com.pacilnugas.account.service;

import com.pacilnugas.account.security.PasswordCoder;
import com.pacilnugas.activities.model.Matkul;
import com.pacilnugas.activities.repository.MatkulRepository;
import com.pacilnugas.account.core.Lecturer;
import com.pacilnugas.account.core.Student;
import com.pacilnugas.account.core.TeachingAssistant;
import com.pacilnugas.account.core.Account;
import com.pacilnugas.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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

    public AccountService() {
        try {
            this.passwordCoder = new PasswordCoder();
        } catch (Exception e) {

        }
    }

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

    public Account getAccount(String username, String password) {
        Account foundAccount = accountRepository.findById(username).get();
        password = passwordCoder.encrypt(password);
        if (foundAccount.getPassword().equalsIgnoreCase(password)) {
            return foundAccount;
        }
        return null;
    }

    public void saveMatkul(String username, List<Matkul> listMatkul) {
        Account account = getAccountByUsername(username);
        account.setPersonalizedMatkul(listMatkul);
        accountRepository.save(account);
    }

    public Account getAccountByUsername(String username) {
        return accountRepository.findById(username).get();
    }

    public boolean checkUsernameUsed(String username) {
        for (Account account: getAllAccount()) {
            if (account.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}
