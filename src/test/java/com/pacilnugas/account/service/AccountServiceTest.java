package com.pacilnugas.account.service;

import com.pacilnugas.account.core.Account;
import com.pacilnugas.account.core.Lecturer;
import com.pacilnugas.account.core.Student;
import com.pacilnugas.account.core.TeachingAssistant;
import com.pacilnugas.account.repository.AccountRepository;
import com.pacilnugas.activities.repository.MatkulRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    private void setUp() {
        Student student = new Student();
        student.setUsername("syabib");
        student.setPassword("12345678");

        TeachingAssistant teachingAssistant = new TeachingAssistant();
        teachingAssistant.setUsername("jc");
        teachingAssistant.setPassword("12345678");

        Lecturer lecturer = new Lecturer();
        lecturer.setUsername("bapak");
        lecturer.setPassword("12345678");

        List<Account> listAccount = new ArrayList<>();
        listAccount.add(student);
        listAccount.add(teachingAssistant);
        listAccount.add(lecturer);

        lenient().when(accountRepository.findAll()).thenReturn(listAccount);
    }

    @Test
    void testRegistrationAndCreateAccountMethods() {
        String redirect = accountService.registration("sasfort", "12345678", "12345678",
                "Student");
        assertEquals("existingAccount", redirect);
        redirect = accountService.registration("syabib", "12345678", "12345678", "Teaching Assistant");
        assertEquals("registrationUsed", redirect);
        redirect = accountService.registration("sasfort", "12345678", "12345679", "Lecturer");
        assertEquals("registrationError", redirect);
    }
}
