package com.pacilnugas.account.service;

import com.pacilnugas.account.core.Account;
import com.pacilnugas.account.core.Lecturer;
import com.pacilnugas.account.core.Student;
import com.pacilnugas.account.core.TeachingAssistant;
import com.pacilnugas.account.repository.AccountRepository;
import com.pacilnugas.account.security.LinkCoder;
import com.pacilnugas.account.security.PasswordCrypter;
import com.pacilnugas.activities.model.Matkul;
import com.pacilnugas.activities.repository.MatkulRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private MatkulRepository matkulRepository;

    private LinkCoder linkCoder = new LinkCoder();
    private PasswordCrypter passwordCrypter;

    public AccountService() {
        setUp("DESede");
    }

    /**
     * Setting up passwordCrypter and linkCoder for password security and passing parameters.
     */
    public void setUp(String desede) {
        try {
            this.passwordCrypter = new PasswordCrypter(desede);
        } catch (Exception e) {
            this.passwordCrypter = null;
        }
        this.linkCoder = new LinkCoder();
    }

    public PasswordCrypter getPasswordCrypter() {
        return passwordCrypter;
    }

    /**
     * Signing up process for new account.
     */
    public String registration(String username, String password, String confirmPassword,
                               String type) {
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
    public Account createAccount(String username, String password, String type) {
        password = passwordCrypter.encrypt(password, false);
        Account newAccount = null;
        if (type.equalsIgnoreCase("Student")) {
            newAccount = new Student(username, password);
            accountRepository.save(newAccount);
        } else if (type.equalsIgnoreCase("Teaching Assistant")) {
            newAccount = new TeachingAssistant(username, password);
            accountRepository.save(newAccount);
        } else if (type.equalsIgnoreCase("Lecturer")) {
            newAccount = new Lecturer(username, password);
            accountRepository.save(newAccount);
        }
        return newAccount;
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
     * Authenticating account based on username and password
     * the user inputs for access to personal page.
     */
    public String authenticatePersonal(String username, String password) {
        Account foundAccount = accountRepository.findByUsername(username);
        password = passwordCrypter.encrypt(password, false);
        if (foundAccount.getPassword().equals(password)) {
            if (!foundAccount.getPersonalizedAccess()) {
                return "loginPersonalFail";
            }
            return "personal?username=" + encode(username);
        }
        return "loginPersonalError";
    }

    /**
     * Authenticating account based on username and password
     * the user inputs for access to course creator page.
     */
    public String authenticateCourse(String username, String password) {
        Account foundAccount = accountRepository.findByUsername(username);
        password = passwordCrypter.encrypt(password, false);
        if (foundAccount.getPassword().equals(password)) {
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

    /**
     * Decoding passed checked courses to be saved at user's personalized page.
     */
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
        return accountRepository.findByUsername(username);
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
        return linkCoder.encode(code, StandardCharsets.UTF_8.name());
    }

    public String decode(String code) {
        return linkCoder.decode(code, StandardCharsets.UTF_8.name());
    }
}
