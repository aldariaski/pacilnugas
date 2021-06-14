package com.pacilnugas.authentication.service;

import com.pacilnugas.activities.model.Matkul;
import com.pacilnugas.activities.repository.MatkulRepository;
import com.pacilnugas.authentication.core.Lecturer;
import com.pacilnugas.authentication.core.Student;
import com.pacilnugas.authentication.core.TeachingAssistant;
import com.pacilnugas.authentication.core.Account;
import com.pacilnugas.authentication.repository.AccountRepository;
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

    private Account activeAccount;
    private boolean loginTrial;

    public AccountService() {
        this.activeAccount = null;
        this.loginTrial = false;
    }

    public void createAccount(String username, String password, String type) {
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

    public void login(String username, String password) {
        this.loginTrial = true;
        this.activeAccount = getAccount(username, password);
    }

    public String getLoginStatus() {
        if (!this.loginTrial) {
            return "";
        }
        else if (this.activeAccount == null) {
            return "Login failed";
        } else {
            return loginMessage();
        }
    }

    public String loginMessage() {
        String type = this.activeAccount.getType().toLowerCase();
        String username = this.activeAccount.getUsername();
        return "You logged in as a " + type + " with username " + username;
    }

    public void logout() {
        this.activeAccount = null;
        this.loginTrial = false;
    }
}
