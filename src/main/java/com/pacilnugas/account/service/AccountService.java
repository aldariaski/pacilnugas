package com.pacilnugas.account.service;

import com.pacilnugas.account.core.Account;
import com.pacilnugas.account.core.Lecturer;
import com.pacilnugas.account.core.Student;
import com.pacilnugas.account.core.TeachingAssistant;
import com.pacilnugas.account.repository.AccountRepository;
import com.pacilnugas.account.security.PasswordCrypter;
import com.pacilnugas.account.security.URLCoder;
import com.pacilnugas.activities.model.Matkul;
import com.pacilnugas.activities.repository.MatkulRepository;
import com.pacilnugas.activities.service.MatkulService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private MatkulRepository matkulRepository;

    private URLCoder urlCoder;
    private PasswordCrypter passwordCrypter;

    /**
     * Constructur for accountService, used to initialize PasswordCoder.
     */
    public AccountService() {
        try {
            this.passwordCrypter = new PasswordCrypter();
        } catch (Exception e) {
            this.passwordCrypter = null;
        }
        this.urlCoder = new URLCoder();
    }

    /**
     * Signing up process for new account.
     */
    public String registration(String username, String password, String confirmPassword, String type) {
        if (checkUsernameUsed(username)) {
            return "registrationUsed";
        }
        if (!password.equals(confirmPassword)) {
            return "registrationError";
        }
        createAccount(username, password, type);
        return "existingAccount";
    }

    /**
     * Creating account based upon the type that user picks.
     */
    public void createAccount(String username, String password, String type) {
        password = passwordCrypter.encrypt(password);
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
    public String authenticatePersonal(String username, String password) {
        Account foundAccount = accountRepository.findById(username).get();
        password = passwordCrypter.encrypt(password);
        if (foundAccount.getPassword().equalsIgnoreCase(password)) {
            if (!foundAccount.getPersonalizedAccess()) {
                return "loginPersonalFail";
            }
            return "personal?username=" + urlCoder.encode(username);
        }
        return "loginPersonalError";
    }

    /**
     * Authenticating account based on username and password the user inputs.
     */
    public String authenticateCourse(String username, String password) {
        Account foundAccount = accountRepository.findById(username).get();
        password = passwordCrypter.encrypt(password);
        if (foundAccount.getPassword().equalsIgnoreCase(password)) {
            if (!foundAccount.getCourseAccess()) {
                return "loginCourseFail";
            }
            return "matkul/input-matkul";
        }
        return "loginCourseError";
    }

    /**
     * Saving picked courses from personal page to database.
     */
    public void saveMatkul(String username, List<Matkul> listMatkul) {
        Account account = getAccountByUsername(username);
        account.setPersonalizedMatkul(listMatkul);
        accountRepository.save(account);
    }

    public void decodeCheckedMatkul(String username, List<String> listMatkul) {
        List<Matkul> checkedMatkul = new ArrayList<>();
        for (String stringMatkul : listMatkul) {
            String result = decode(stringMatkul);
            Matkul matkul = matkulRepository.findByTitle(result);
            checkedMatkul.add(matkul);
        }
        saveMatkul(decode(username), checkedMatkul);
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

    public String encode(String code) {
        return urlCoder.encode(code);
    }

    public String decode(String code) {
        return urlCoder.decode(code);
    }
}
