package com.pacilnugas.authentication.service;

import com.pacilnugas.activities.model.Matkul;
import com.pacilnugas.activities.repository.MatkulRepository;
import com.pacilnugas.authentication.core.Lecturer;
import com.pacilnugas.authentication.core.Student;
import com.pacilnugas.authentication.core.TeachingAssistant;
import com.pacilnugas.authentication.core.Account;
import com.pacilnugas.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MatkulRepository matkulRepository;

    private Account activeAccount;
    private boolean loginTrial;

    public UserService() {
        this.activeAccount = null;
        this.loginTrial = false;
    }

    public void createUser(String username, String password, String type) {
        if (type.equalsIgnoreCase("Student")) {
            Student newAccount = new Student(username, password);
            userRepository.save(newAccount);
        } else if (type.equalsIgnoreCase("Teaching Assistant")) {
            TeachingAssistant newAccount = new TeachingAssistant(username, password);
            userRepository.save(newAccount);
        } else if (type.equalsIgnoreCase("Lecturer")) {
            Lecturer newAccount = new Lecturer(username, password);
            userRepository.save(newAccount);
        }
    }

    public List<Account> getAllUser() {
        return userRepository.findAll();
    }

    public List<String> getAllDisplayMessage() {
        List<Account> listOfAccount = getAllUser();
        List<String> listOfDisplayMessage = new ArrayList<>();
        for (Account account : listOfAccount) {
            listOfDisplayMessage.add(displayUser(account));
        }
        return listOfDisplayMessage;
    }

    public String displayUser(Account account) {
        return account.getType() + " with username " + account.getUsername();
    }

    public Account getUser(String username, String password) {
//        Account foundAccount = userRepository.findById(username);
//        if (foundAccount.getPassword().equalsIgnoreCase(password)) {
//            return foundAccount;
//        } else {
//            return null;
//        }
        return null;
    }

    public void saveMatkul(String username, List<Matkul> listMatkul) {
        Account account = getUserByUsername(username);
        account.setPersonalizedMatkul(listMatkul);
        userRepository.save(account);
    }

    public Account getUserByUsername(String username) {
        return userRepository.findById(username).get();
    }

    public void login(String username, String password) {
        this.loginTrial = true;
        this.activeAccount = getUser(username, password);
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
