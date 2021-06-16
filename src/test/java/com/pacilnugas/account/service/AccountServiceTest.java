package com.pacilnugas.account.service;

import com.pacilnugas.account.core.Account;
import com.pacilnugas.account.core.Lecturer;
import com.pacilnugas.account.core.Student;
import com.pacilnugas.account.core.TeachingAssistant;
import com.pacilnugas.account.repository.AccountRepository;
import com.pacilnugas.account.security.PasswordCrypter;
import com.pacilnugas.activities.model.Matkul;
import com.pacilnugas.activities.repository.MatkulRepository;
import com.pacilnugas.template.core.Pesan;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
    @Mock
    private AccountRepository accountRepository;

    @Mock
    private MatkulRepository matkulRepository;

    @Mock
    private PasswordCrypter passwordCrypter;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    private void setUp() {
        Student student = new Student("syabib", "8mzCNJUNtvSJJSpQl9fUJg==");
        TeachingAssistant teachingAssistant = new TeachingAssistant("jc", "8mzCNJUNtvSJJSpQl9fUJg==");
        Lecturer lecturer = new Lecturer("bapak", "8mzCNJUNtvSJJSpQl9fUJg==");

        List<Account> listAccount = new ArrayList<>();
        listAccount.add(student);
        listAccount.add(teachingAssistant);
        listAccount.add(lecturer);

        lenient().when(accountRepository.findAll()).thenReturn(listAccount);
        lenient().when(accountRepository.findByUsername("sasfort")).thenReturn(student);
        lenient().when(accountRepository.findByUsername("jc")).thenReturn(teachingAssistant);
        lenient().when(accountRepository.findByUsername("bapak")).thenReturn(lecturer);
        lenient().when(passwordCrypter.encrypt("12345678")).thenReturn("8mzCNJUNtvSJJSpQl9fUJg==");
    }

    @Test
    void testRegistrationMethod() {
        String redirect = accountService.registration("sasfort", "12345678", "12345678",
                "Student");
        assertEquals("existingAccount", redirect);
        redirect = accountService.registration("syabib", "12345678", "12345678", "Teaching Assistant");
        assertEquals("registrationUsed", redirect);
        redirect = accountService.registration("sasfort", "12345678", "12345679", "Lecturer");
        assertEquals("registrationError", redirect);
    }

    @Test
    void testCreateAccountMethod() {
        Account newAccount;
        newAccount = accountService.createAccount("user1", "12345678", "Student");
        assertEquals("Student", newAccount.getType());
        newAccount = accountService.createAccount("user1", "12345678", "Teaching Assistant");
        assertEquals("Teaching Assistant", newAccount.getType());
        newAccount = accountService.createAccount("user1", "12345678", "Lecturer");
        assertEquals("Lecturer", newAccount.getType());
    }

    @Test
    void testGetAllAccount() {
        List<Account> listAccount = accountRepository.findAll();
        lenient().when(accountService.getAllAccount()).thenReturn(listAccount);
        List<Account> accountList = accountService.getAllAccount();
        Assertions.assertIterableEquals(listAccount, accountList);
    }

    @Test
    void testAccountDisplayMessageMethods() {
        List<Account> listAccount = accountRepository.findAll();
        List<String> listDisplay = accountService.getAllDisplayMessage();
        for (int i = 0; i < listAccount.size(); i++) {
            Account account = listAccount.get(i);
            assertEquals(listDisplay.get(i), account.getType() + " with username " + account.getUsername());
        }
    }

    @Test
    void testAuthenticatePersonalMethod() {
        String redirect = accountService.authenticatePersonal("sasfort", "12345678");
        assertEquals("personal?username=sasfort", redirect);
        redirect = accountService.authenticatePersonal("jc", "12345679");
        assertEquals("loginPersonalError", redirect);
        redirect = accountService.authenticatePersonal("bapak", "12345678");
        assertEquals("loginPersonalFail", redirect);
    }

    @Test
    void testAuthenticateCourseMethod() {
        String redirect = accountService.authenticateCourse("bapak", "12345678");
        assertEquals("matkul/input-matkul", redirect);
        redirect = accountService.authenticateCourse("jc", "12345679");
        assertEquals("loginCourseError", redirect);
        redirect = accountService.authenticateCourse("sasfort", "12345678");
        assertEquals("loginCourseFail", redirect);
    }

    @Test
    void testSaveCheckedMatkulMethods() {
        Matkul adpro = new Matkul();
        LocalDate date = LocalDate.now();
        adpro.setPengajar("Budi Budiman");
        adpro.setTitle("Advanced Programming");
        adpro.setDescription("description");
        adpro.setMulai(date);
        adpro.setTahun(date.getYear());
        adpro.setSemester("Genap");
        adpro.setMajor("Ilmu Komputer");
        adpro.setAngkatan(2019);
        lenient().when(matkulRepository.findByTitle("Advanced Programming")).thenReturn(adpro);
        List<String> listMatkul = new ArrayList<>();
        listMatkul.add("Advanced Programming");
        accountService.decodeCheckedMatkul("sasfort", listMatkul);
        for (int i = 0; i < listMatkul.size(); i++) {
            List<Matkul> listPersonalized = accountRepository.findByUsername("sasfort").getPersonalizedMatkul();
            assertEquals(listPersonalized.get(i).getTitle(), listMatkul.get(i));
        }
    }

    @Test
    void testEncodeMethod() {
        assertEquals(accountService.encode("T&A"), "T%26A");
    }

    @Test
    void testDecodeMethod() {
        assertEquals(accountService.decode("T%26A"), "T&A");
    }
}
